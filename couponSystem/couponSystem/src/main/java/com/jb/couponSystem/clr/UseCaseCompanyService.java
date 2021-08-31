package com.jb.couponSystem.clr;

import com.jb.couponSystem.Services.CompanyService;
import com.jb.couponSystem.beans.Category;
import com.jb.couponSystem.beans.Company;
import com.jb.couponSystem.beans.Coupon;
import com.jb.couponSystem.excep.CouponSystemException;
import com.jb.couponSystem.security.ClientType;
import com.jb.couponSystem.security.LoginManager;
import com.jb.couponSystem.utils.PrintUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(4)
@RequiredArgsConstructor
public class UseCaseCompanyService implements CommandLineRunner {

    private final LoginManager loginManager;

    private CompanyService companyService;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Test - CompanyService");

        System.out.println("\nTEST: ============= CompanyLogin =============");
        companyLogin("company1@gmail.com", "123");
        companyLogin("company1@gmail.com", "123456");

        System.out.println("\nTEST: ============= CompanyCoupons =============");
        PrintUtils.print(companyService.getCompanyCoupons());

        //int companyId = ((CompanyServiceImp) companyService).getCompanyId();
        Company company = companyService.getCompanyDetails();

        System.out.println("\nTEST: ============= AddCompanyCoupon =============");

        Coupon couponFirst = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("SameTitleForCoupon")
                .description("CouponDescription")
                .startDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-1)))
                .endDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(+1)))
                .amount(0)
                .price(10)
                .company(company)
                .image("CouponImage_1").build();

        Coupon couponSecond = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("SameTitleForCoupon")
                .description("CouponDescription")
                .startDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-1)))
                .endDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(+1)))
                .amount(0)
                .price(10)
                .company(company)
                .image("CouponImage_1").build();

        Coupon couponThird = Coupon.builder()
                .category(Category.FOOD)
                .title("SameTitleForCoupon_OtherCompany")
                .description("Price lessThen 100")
                .startDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-1)))
                .endDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(+1)))
                .amount(0)
                .price(200)
                .company(company)
                .image("CouponImage_1").build();

        //Company1 has SameTitleForCoupon_OtherCompany coupon

        companyAddCoupon(couponFirst);
        System.out.println("\n============= TEST CASE INSERT TWO COUPONS WITH SAME TITLE =============");
        companyAddCoupon(couponSecond);
        System.out.println("\n============= TEST CASE INSERT COUPONS WITH SAME TITLE TO OTHER COMPANY COUPON =============");
        companyAddCoupon(couponThird);

        System.out.println("\nTEST: ============= PrintCompanyCoupons =============");
        PrintUtils.print(companyService.getCompanyCoupons());

        System.out.println("\nTEST: ============= UpdateCompanyCoupons (set price)=============");
        Coupon coupon = companyService.getCompanyCoupons().get(0);
        coupon.setPrice(coupon.getPrice() + 1);
        companyService.updateCoupon(coupon.getId(), coupon);
        System.out.println("\nTEST: ============= GetAllCompanyCouponsAfterUpdate =============");
        PrintUtils.print(companyService.getCompanyCoupons());

        System.out.println("\nTEST: ============= DeleteCompanyCoupons (coupon 2 for company 1) =============");
        companyService.deleteCoupon(2);

        System.out.println("\nTEST: ============= GetAllCompanyCouponsAfterDelete =============");
        PrintUtils.print(companyService.getCompanyCoupons());

        System.out.println("\nTEST: ============= GetAllCompanyCouponsForSpecificCategory {ELECTRICITY} =============");
        PrintUtils.print(companyService.getCompanyCoupons(Category.ELECTRICITY));

        System.out.println("\nTEST: ============= GetAllCompanyCouponsLimitByMaxPrice {100} =============");
        PrintUtils.print(companyService.getCompanyCoupons(100));

        System.out.println("\nTEST: ============= CompanyDetails =============");
        PrintUtils.print(companyService.getCompanyDetails());
    }

    public void companyLogin(String email, String password) {
        try {
            companyService = (CompanyService) loginManager.login(email, password, ClientType.Company);
            if (companyService == null) {
                System.out.println("Email: " + email + " + Password: " + password + ", can not login");
            } else {
                System.out.println("Email: " + email + " + Password: " + password + ", can login");
            }
        } catch (CouponSystemException e) {
            System.out.print("Email: " + email + " + Password: " + password + ", can not login ");
            System.out.println("The exception is:" + e.getMessage());
        }
    }

    public void companyAddCoupon(Coupon coupon) {
        try {
            companyService.addCoupon(coupon);
            System.out.println("Company add coupon successfully");
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }
}
