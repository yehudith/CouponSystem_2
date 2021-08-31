package com.jb.couponSystem.repoos;

import com.jb.couponSystem.beans.Category;
import com.jb.couponSystem.beans.Company;
import com.jb.couponSystem.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


public interface CouponRepository  extends JpaRepository<Coupon,Integer> {

    List<Coupon> findByCompanyId(int companyId);
    List<Coupon> findByCategory(Category category);
    List<Coupon> findByAmount(double amount);
    List<Coupon> findByPriceLessThanAndCompanyId(double price,int companyId);


    List<Coupon> findByCompany(Optional<Company> company);
    List<Coupon> findByCompanyIdAndTitle(int companyId,String title);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `customer_coupons` (`customer_id`, `coupons_id`) VALUES (:customerID,:couponID)",nativeQuery = true)
    void addCouponPurchase(int customerID, int couponID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `customer_coupons` where `customer_id` = :customerID and `coupons_id` =:couponID",nativeQuery = true)
    void deleteCouponPurchase(int customerID, int couponID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `coupon` where `id` = :couponID",nativeQuery = true)
    void deleteCoupon(int couponID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM `customer_coupons` where `coupons_id` =:couponID",nativeQuery = true)
    void deleteCouponsPurchase(int couponID);

    @Transactional
    @Modifying
    @Query(value =  "SELECT `coupon`.* FROM `customer_coupons` " +
            "inner join `coupon` " +
            " on `customer_coupons`.`coupons_id` = `coupon`.`id` " +
            "where `customer_coupons`.`customer_id` = :customerId",nativeQuery = true)
    List<Coupon> findByCustomerId(int customerId);

    @Modifying
    @Query(value ="SELECT `coupon`.* " +
            "  FROM `customer_coupons` inner join " +
            "       `coupon` " +
            "  on `customer_coupons`.`coupons_id` = `coupon`.`id` " +
            "  where `customer_id` = :customerId and `category` = :category",nativeQuery = true)
    List<Coupon> findCouponsByCustomerIdAndCategory(int customerId,Category category);

    @Modifying
    @Query(value ="SELECT `coupon`.* " +
            "  FROM `customer_coupons` inner join " +
            "       `coupon` " +
            "  on `customer_coupons`.`coupons_id` = `coupon`.`id` " +
            "  where `customer_id` = :customerId and `price` <  :maxPrice",nativeQuery = true)
    List<Coupon> findCouponsByCustomerIdAndMaxPrice(int customerId,double maxPrice);

    List<Coupon> findByEndDateIsBefore(Date date);
    List<Coupon> findByEndDateLessThan(Date date);


}
