package com.jb.couponSystem.controllers;

import com.jb.couponSystem.Services.CompanyService;
import com.jb.couponSystem.beans.Category;
import com.jb.couponSystem.beans.Company;
import com.jb.couponSystem.beans.Coupon;
import com.jb.couponSystem.excep.CouponSystemException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//
//@RestController
//@RequestMapping("companies")
//@RequiredArgsConstructor
//public class CompaniesController {
//
//    private final CompanyService companyService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getSingleCompany(@PathVariable int id) throws CouponSystemException {
//        return new ResponseEntity<>(companyService.(id), HttpStatus.OK); //200
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getSingleCompany(@PathVariable int id) throws CouponSystemException {
//        return new ResponseEntity<>(companyService.getSingleCat(id), HttpStatus.OK); //200
//    }
//
//    @GetMapping
//    public ResponseEntity<?> geAllCats() {
//        return new ResponseEntity<>(companyService.getCompanyCoupons(), HttpStatus.OK); //200
//    }
//
//    @GetMapping("/{categoryId}")
//    public ResponseEntity<?> geAllCompanyCouponsForCategory(@PathVariable string categoryId) {
//        return new ResponseEntity<>(companyService.getCompanyCoupons(Category.valueOf(categoryId)), HttpStatus.OK); //200
//    }
//
//    @GetMapping("/{maxPrice}")
//    public ResponseEntity<?> geAllCompanyCouponsForCategory(@PathVariable string maxPrice) {
//        return new ResponseEntity<>(companyService.getCompanyCoupons(maxPrice), HttpStatus.OK); //200
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addCoupon(@RequestBody Coupon coupon) throws CatSystemException {
//        companyService.addCoupon(coupon);
//    }
//
//    @DeleteMapping("/{couponId}")
//    @ResponseStatus(HttpStatus.NO_CONTENT) //204
//    public void deleteCoupon(@PathVariable int couponId) throws CatSystemException {
//        companyService.deleteCoupon(id);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT) //204
//    public void updateCoupon(@PathVariable int couponId, @RequestBody Coupon coupon) throws CatSystemException {
//        companyService.updateCoupon(couponId, coupon);
//    }
//}
