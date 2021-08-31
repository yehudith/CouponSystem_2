package com.jb.couponSystem.Services;

import com.jb.couponSystem.beans.Category;
import com.jb.couponSystem.beans.Coupon;
import com.jb.couponSystem.beans.Customer;
import com.jb.couponSystem.excep.CouponSystemException;
import com.jb.couponSystem.excep.ErrorMessage;
import com.jb.couponSystem.utils.DateUtils;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Data
@Service
public class CustomerServiceImp extends ClientService implements CustomerService {
    private int customerId;

    @Override
    public boolean login(String email, String password) {
        return this.customerRepository.existsByEmailAndPassword(email,password);
    }

    @Override
    public List<Coupon> getCustomerCoupons() {
        return this.couponRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Coupon> getCustomerCoupons(Category category) {
        return this.couponRepository.findCouponsByCustomerIdAndCategory(customerId, category);
    }

    @Override
    public List<Coupon> getCustomerCoupons(double maxPrice) {
        return this.couponRepository.findCouponsByCustomerIdAndMaxPrice(customerId, maxPrice);
    }

    @Override
    public Customer getCustomerDetails() throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(() -> new CouponSystemException(ErrorMessage.CUSTOMER_IS_NOT_EXIST));
    }

    @Override
    public Customer getCustomerByEmailAndPassword(String email, String password) {
        return customerRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {

        List<Coupon> allCustomerCoupons = this.couponRepository.findByCustomerId(customerId);

        if (allCustomerCoupons.stream().filter(p -> p.getId() == coupon.getId()).count() > 0) {
            throw new CouponSystemException(ErrorMessage.CUSTOMER_CAN_BUY_ONLY_ONE_COUPON);
        }
        if (coupon.getAmount() == 0) {
            throw new CouponSystemException(ErrorMessage.COUPON_OUT_OF_THE_STOCK);
        }
        if (DateUtils.convertFromSQLDateToJAVADate(coupon.getEndDate()).compareTo(Calendar.getInstance().getTime()) < 0) {
            throw new CouponSystemException(ErrorMessage.COUPON_EXPIRY_DATE);
        }

        coupon.setAmount(coupon.getAmount() - 1);
        this.couponRepository.save(coupon);

        this.couponRepository.addCouponPurchase(customerId, coupon.getId());

    }
}
