package com.jb.couponSystem.clr;

import com.jb.couponSystem.beans.Category;
import com.jb.couponSystem.beans.Company;
import com.jb.couponSystem.beans.Coupon;
import com.jb.couponSystem.beans.Customer;
import com.jb.couponSystem.repoos.CompanyRepository;
import com.jb.couponSystem.repoos.CouponRepository;
import com.jb.couponSystem.repoos.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.Arrays;

@Component
@Order(1)
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final CouponRepository couponRepository;
    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("=======================Company=======================");
        Company company1 = Company.builder()
                .email("company1@gmail.com")
                .name("company1")
                .password("123456")
                .build();

        Company company2 = Company.builder()
                .email("company2@gmail.com")
                .name("company2")
                .password("123456")
                .build();

        Company company3 = Company.builder()
                .email("company3@gmail.com")
                .name("company3")
                .password("123456")
                .build();

        companyRepository.saveAll(Arrays.asList(company1, company2, company3));

        System.out.println("=======================Customer=======================");
        Customer cust1 = Customer.builder()
                .firstName("FirstName1")
                .lastName("LastName1")
                .email("customer1@gmail.com")
                .password("123456")
                .build();


        Customer cust2 = Customer.builder()
                .firstName("FirstName2")
                .lastName("LastName2")
                .email("customer2@gmail.com")
                .password("123456")
                .build();

        Customer cust3 = Customer.builder()
                .firstName("FirstName3")
                .lastName("LastName3")
                .email("customer3@gmail.com")
                .password("123456")
                .build();

        Customer cust4 = Customer.builder()
                .firstName("FirstName4")
                .lastName("LastName4")
                .email("customer4@gmail.com")
                .password("123456")
                .build();


        Customer cust5 = Customer.builder()
                .firstName("FirstName5")
                .lastName("LastName5")
                .email("customer5@gmail.com")
                .password("123456")
                .build();

        customerRepository.saveAll(Arrays.asList(cust1, cust2, cust3, cust4, cust5));

        System.out.println("=======================Coupon=======================");
        Coupon coupon1 = Coupon.builder()
                .category(Category.ELECTRICITY)
                .title("CouponTitle_1")
                .description("SameTitleForCoupon_OtherCompany")
                .startDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-1)))
                .endDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(+1)))
                .amount(10)
                .price(10)
                .company(company1)
                .image("CouponImage_1").build();

        Coupon coupon2 = Coupon.builder()
                .category(Category.FOOD)
                .title("CouponTitle_2")
                .description("CouponDescription_2")
                .startDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-1)))
                .endDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(+1)))
                .amount(5)
                .price(20)
                .company(company1)
                .image("CouponImage_2").build();

        Coupon coupon3 = Coupon.builder()
                .category(Category.RESTAURANT)
                .title("CouponTitle_3")
                .description("CouponDescription_3")
                .startDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-1)))
                .endDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(+1)))
                .amount(5)
                .price(30)
                .company(company2)
                .image("CouponImage_3").build();

        Coupon coupon4 = Coupon.builder()
                .category(Category.VACATION)
                .title("CouponTitle_4")
                .description("CouponDescription_4")
                .startDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-2)))
                .endDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-1)))
                .amount(5)
                .price(40)
                .company(company3)
                .image("CouponImage_4").build();

        Coupon coupon5 = Coupon.builder()
                .category(Category.VACATION)
                .title("CouponAmountZero")
                .description("CouponAmountZero")
                .startDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-2)))
                .endDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-1)))
                .amount(0)
                .price(40)
                .company(company3)
                .image("CouponImage_5").build();

        Coupon coupon6 = Coupon.builder()
                .category(Category.VACATION)
                .title("CouponExpiryDate")
                .description("CouponExpiryDate")
                .startDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-3)))
                .endDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-2)))
                .amount(10)
                .price(40)
                .company(company1)
                .image("CouponImage_6").build();

        Coupon coupon7 = Coupon.builder()
                .category(Category.VACATION)
                .title("CouponForCompany1BuyByCust1")
                .description("CouponForCompany1BuyByCust1")
                .startDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(-3)))
                .endDate(java.sql.Date.valueOf(LocalDate.now().plusWeeks(5)))
                .amount(10)
                .price(40)
                .company(company1)
                .image("CouponImage_7").build();
        couponRepository.saveAll(Arrays.asList(coupon1, coupon2, coupon3, coupon4, coupon5, coupon6, coupon7));

        System.out.println("=======================CouponPurchase=======================");
        couponRepository.addCouponPurchase(1, 1);
        couponRepository.addCouponPurchase(1, 7);
        couponRepository.addCouponPurchase(2, 1);
        couponRepository.addCouponPurchase(2, 3);
        couponRepository.addCouponPurchase(3, 7);
        couponRepository.addCouponPurchase(3, 6);

        couponRepository.deleteById(6);
    }
}
