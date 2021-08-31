package com.jb.couponSystem.Services;

import com.jb.couponSystem.beans.Category;
import com.jb.couponSystem.beans.Company;
import com.jb.couponSystem.beans.Coupon;
import com.jb.couponSystem.excep.CouponSystemException;

import java.util.List;

public interface CompanyService {

    void addCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int couponId);

    List<Coupon> getCompanyCoupons();

    List<Coupon> getCompanyCoupons(Category category);

    List<Coupon> getCompanyCoupons(double maxPrice);

    Company getCompanyDetails() throws CouponSystemException;

    Company getCompanyByEmailAndPassword(String email, String password);
}
