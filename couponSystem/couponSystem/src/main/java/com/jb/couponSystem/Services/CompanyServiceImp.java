package com.jb.couponSystem.Services;

import com.jb.couponSystem.beans.Category;
import com.jb.couponSystem.beans.Company;
import com.jb.couponSystem.beans.Coupon;
import com.jb.couponSystem.excep.CouponSystemException;
import com.jb.couponSystem.excep.ErrorMessage;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CompanyServiceImp extends ClientService implements CompanyService{
    private int companyId;

    @Override
    public boolean login(String email, String password) {
        return companyRepository.existsByEmailAndPassword(email,password);
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        if (coupon.getCompany().getId() != companyId) {
            throw new CouponSystemException(ErrorMessage.COUPON_OF_OTHER_COMPANY);
        }
        if(this.couponRepository.findByCompanyIdAndTitle(companyId,coupon.getTitle()).stream().count()>0) {
            throw new CouponSystemException(ErrorMessage.COMPANY_WITH_SAME_TITLE);
        }
        this.couponRepository.save(coupon);
    }

    @Override
    public void updateCoupon(int couponId,Coupon coupon) throws CouponSystemException {
        if (coupon.getCompany().getId() != companyId) {
            throw new CouponSystemException(ErrorMessage.COMPANY_ID_NOT_EXIST);
        }
        coupon.setId(couponId); //??
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) {
        //1. delete customersCoupons
        this.couponRepository.deleteCouponsPurchase(couponId);
        //2. delete coupon
        //this.couponRepository.deleteById(couponId);
        this.couponRepository.deleteCoupon(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons() {
        return couponRepository.findByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(Category category) {
        return couponRepository.findByCategory(category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(double maxPrice) {
        return couponRepository.findByPriceLessThanAndCompanyId(maxPrice,companyId);
    }

    @Override
    public Company getCompanyDetails() throws CouponSystemException {
        return companyRepository.findById(companyId).orElseThrow(()->new CouponSystemException(ErrorMessage.COMPANY_ID_NOT_EXIST));
    }

    @Override
    public  Company getCompanyByEmailAndPassword(String email, String password){
        return companyRepository.findByEmailAndPassword(email,password);
    }
}
