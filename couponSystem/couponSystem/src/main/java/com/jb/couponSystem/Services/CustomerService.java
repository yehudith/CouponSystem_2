package com.jb.couponSystem.Services;

import com.jb.couponSystem.beans.Category;
import com.jb.couponSystem.beans.Coupon;
import com.jb.couponSystem.beans.Customer;
import com.jb.couponSystem.excep.CouponSystemException;

import java.util.List;

public interface CustomerService {

    List<Coupon> getCustomerCoupons();

    List<Coupon> getCustomerCoupons(Category category);

    List<Coupon> getCustomerCoupons(double maxPrice);

    Customer getCustomerDetails() throws CouponSystemException;

    Customer getCustomerByEmailAndPassword(String email, String password);

    void purchaseCoupon(Coupon coupon) throws CouponSystemException;
}
