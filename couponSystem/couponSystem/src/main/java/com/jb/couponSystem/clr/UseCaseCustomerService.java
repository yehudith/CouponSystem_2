package com.jb.couponSystem.clr;

import com.jb.couponSystem.Services.CustomerService;
import com.jb.couponSystem.beans.Category;
import com.jb.couponSystem.beans.Coupon;
import com.jb.couponSystem.excep.CouponSystemException;
import com.jb.couponSystem.repoos.CouponRepository;
import com.jb.couponSystem.security.ClientType;
import com.jb.couponSystem.security.LoginManager;
import com.jb.couponSystem.utils.PrintUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Optional;


@Component
@Order(3)
@RequiredArgsConstructor
public class UseCaseCustomerService implements CommandLineRunner {


    private final LoginManager loginManager;

    private final CouponRepository couponRepository;

    private CustomerService customerService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Test - CustomerService");

        System.out.println("\nTEST: ============= CustomerLogin =============");
        customerLogin("customer1@gmail.com", "1234");
        customerLogin("customer1@gmail.com", "123456");

        System.out.println("\nTEST: ============= CustomerBuyCoupon =============");
        System.out.println("\n============= GetAllCoupons: =============");
        PrintUtils.print(customerService.getCustomerCoupons());

        System.out.println("\nTEST CASE COUPON AMOUNT = 0: ============= CustomerBuyCoupon (Try to buy coupon5 that amount = 0)=============");

        Optional<Coupon> couponZeroAmount = couponRepository.findById(5);
        if(!couponZeroAmount.isPresent())
            System.out.println("All amount of coupon is bigger then zero");
        else {

            System.out.println("{Coupon id = " + couponZeroAmount.get().getId() + "}");
            customerPurchaseCoupon(couponZeroAmount.get());
        }


        System.out.println("\nTEST CASE COUPON EXPIRY DATE: ============= CustomerBuyCoupon (Try to buy coupon6 that is expiry)=============");
        Coupon couponExpiryDate = this.couponRepository.findByEndDateLessThan(new Date(System.currentTimeMillis())).stream().findAny().orElse(null);

        if (couponExpiryDate != null) {
            customerPurchaseCoupon(couponExpiryDate);
            System.out.println("{Coupon id = " + couponExpiryDate.getEndDate() + "}");
        } else {
            System.out.println("There is not coupon that ExpiryDate");
        }

        System.out.println("\nTEST CASE CUSTOMER BY COUPON TWICE: ============= CustomerBuyCoupon (customer1 try to but again couponId = 1) =============");
        Optional<Coupon> couponTwice = this.couponRepository.findById(1);//customerService.//getCustomerCoupons().stream().filter(p ->p.getAmount()>0).findFirst();
        if (couponTwice.isPresent()) {
            customerPurchaseCoupon(couponTwice.get());
        } else {
            System.out.println("There is not coupon { Id = 1 })");
        }

        System.out.println("\nTEST CASE COUPON EXPIRY DATE: ============= CustomerBuyCoupon (customer1 try to buy coupon3) =============");
        Optional<Coupon> coupon3 = this.couponRepository.findById(3);
        if (coupon3.isPresent()) {
            customerPurchaseCoupon(coupon3.get());
        } else {
            System.out.println("There is not coupon { Id = 3 })");
        }

        System.out.println("\nTEST ============= PrintCouponAfterCustomerBuy (amount = amount - 1) =============");
        PrintUtils.print(couponRepository.findAll());

        System.out.println("\nTEST: ============= CustomerCoupons =============");
        PrintUtils.print(customerService.getCustomerCoupons());

        System.out.println("\nTEST: ============= CustomerCouponsForCategory - RESTAURANT =============");
        PrintUtils.print(customerService.getCustomerCoupons(Category.RESTAURANT));

        System.out.println("\nTEST: ============= CustomerCouponsMaxPrice = 20 =============");
        PrintUtils.print(customerService.getCustomerCoupons(20));

        System.out.println("\nTEST: ============= CustomerDetails =============");
        PrintUtils.print(customerService.getCustomerDetails());
    }


    public void customerLogin(String email, String password) {
        try {
            this.customerService = (CustomerService) loginManager.login(email, password, ClientType.Customer);
            if (customerService == null) {
                System.out.println("Email: " + email + " + Password: " + password + ", can not login");
            } else {
                System.out.println("Email: " + email + " + Password: " + password + ", can login");
            }
        } catch (CouponSystemException e) {
            System.out.print("Email: " + email + " + Password: " + password + ", can not login ");
            System.out.println("the exception is: " + e.getMessage());
        }
    }

    public void customerPurchaseCoupon(Coupon coupon) {
        try {
            this.customerService.purchaseCoupon(coupon);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }

}
