package com.jb.couponSystem.clr;

import com.jb.couponSystem.Services.AdminService;
import com.jb.couponSystem.Services.AdminServiceImp;
import com.jb.couponSystem.beans.Company;
import com.jb.couponSystem.beans.Customer;
import com.jb.couponSystem.excep.CouponSystemException;
import com.jb.couponSystem.security.ClientType;
import com.jb.couponSystem.security.LoginManager;
import com.jb.couponSystem.utils.PrintUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
@RequiredArgsConstructor
public class UseCaseAdminService implements CommandLineRunner {

    private final LoginManager loginManager;
    private AdminServiceImp adminServiceImp;

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Test - AdminService");

        System.out.println("\nTEST: ============= AdminLogin =============");
        adminLogin("adminFail@admin.com", "adminFail");
        adminLogin("admin@admin.com", "admin");


        System.out.println("\nTEST: ============= Admin GetAllCompanies =============");
        PrintUtils.print(adminServiceImp.getAllCompanies());

        System.out.println("\nTEST: ============= Admin InsertNewCompany =============");
        System.out.println("\nTEST CASE COMPANY WITH SAME NAME ============= Admin InsertNewCompany =============");
        Company company = adminServiceImp.getSignalCompany(1);
        company.setEmail("NewEmail");
        adminInsertCompany(company);

        System.out.println("\nTEST CASE COMPANY WITH SAME EMAIL ============= Admin InsertNewCompany =============");
        company = adminServiceImp.getSignalCompany(1);
        company.setName("NewName");
        adminInsertCompany(company);

        System.out.println("\nTEST CASE NEW COMPANY ============= Admin InsertNewCompany =============");
        Company company4 = Company.builder()
                .email("company4@gmail.com")
                .name("company4")
                .password("123456")
                .build();
        Company company5 = Company.builder()
                .email("company5@gmail.com")
                .name("company5")
                .password("123456")
                .build();
        adminInsertCompany(company4);
        adminInsertCompany(company5);

        System.out.println("\nTEST: ============= Admin GetAllCompaniesAfterInsert=============");
        PrintUtils.print(adminServiceImp.getAllCompanies());

        System.out.println("\nTEST: ============= Admin - UpdateCompany { Id = 3 } - Fail =============");
        Company companyToUpdate = adminServiceImp.getSignalCompany(3);
        companyToUpdate.setName("CompanyToUpdate");
        adminUpdateCompany(companyToUpdate);
        PrintUtils.print(adminServiceImp.getSignalCompany(3));

        System.out.println("\nTEST: ============= Admin - UpdateCompany { Id = 3 } - Succeeded =============");
        companyToUpdate = adminServiceImp.getSignalCompany(3);
        companyToUpdate.setEmail("CompanyToUpadte@gmail.com");
        adminUpdateCompany(companyToUpdate);
        //System.out.println(adminGetOneCompany(3).toString());
        PrintUtils.print(adminServiceImp.getSignalCompany(3));

        System.out.println("\nTEST: ============= Admin - GetAllCompaniesAfterUpdate =============");
        PrintUtils.print(adminServiceImp.getAllCompanies());

        System.out.println("\nTEST: ============= Admin - DeleteCompany { Id = 3 } =============");
        try {
            adminServiceImp.deleteCompany(3);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\nTEST: ============= Admin - GetAllCompaniesAfterDelete =============");
        PrintUtils.print(adminServiceImp.getAllCompanies());

        System.out.println("\nTEST: ============= Admin - GetSingleCompany =============");
        PrintUtils.print(adminServiceImp.getSignalCompany(1));


        System.out.println("\nTEST: ============= Admin - GetAllCustomers =============");
        PrintUtils.print(adminServiceImp.getAllCustomers());

        System.out.println("\nTEST: ============= Admin - InsertCustomer - Failed=============");
        System.out.println("\nTEST CASE INSERT CUSTOMER WITH SAME EMAIL  ============= Admin - InsertCustomer =============");
        Customer customer = adminServiceImp.getSignalCustomer(1);
        adminInsertCustomer(customer);

        System.out.println("\nTEST: ============= Admin - InsertCustomer - Succeeded =============");
        adminInsertCustomer(Customer.builder()
                .firstName("FirstNameAddByAdmin")
                .lastName("LastNameAddByAdmin")
                .email("customerAddByAdmin@.email@gmail.com")
                .password("123456")
                .build());

        System.out.println("\nTEST: ============= Admin - GetAllCustomersAfterInsert =============");
        PrintUtils.print(adminServiceImp.getAllCustomers());

        System.out.println("\nTEST: ============= Admin - UpdateCustomer (Try to setId) =============");
        customer = adminServiceImp.getSignalCustomer(1);
        customer.setId(100);
        adminUpdateCustomer(customer);
        System.out.println("\nTEST: ============= Admin - GetAllAfterUpdateCustomer (Try to setId(100) to customer 1) =============");
        PrintUtils.print(adminServiceImp.getAllCustomers());

        System.out.println("\nTEST: ============= Admin - UpdateCustomer (setFirstName) =============");
        Customer custToUpdate = adminServiceImp.getSignalCustomer(1);
        custToUpdate.setFirstName("CustomerUpdateByAdmin");
        adminUpdateCustomer(custToUpdate);

        System.out.println("\nTEST: ============= Admin - GetAllAfterUpdateCustomer (Try to setId(100) to customer 1) =============");
        PrintUtils.print(adminServiceImp.getAllCustomers());

        System.out.println("\nTEST: ============= Admin - DeleteCustomer (3) =============");
        adminDeleteCustomer(3);

        System.out.println("\nTEST: ============= Admin - GetAllAfterDeleteCustomer (Id = 3) =============");
        PrintUtils.print(adminServiceImp.getAllCustomers());

        System.out.println("\nTEST: ============= Admin - GetSignalCustomer (Id = 2) =============");
        PrintUtils.print(adminServiceImp.getSignalCustomer(2));
    }

    public void adminLogin(String email, String password) {
        try {
            adminServiceImp = (AdminServiceImp) loginManager.login(email, password, ClientType.Admin);
            if (adminServiceImp == null) {
                System.out.println("Email: " + email + " + Password: " + password + ", can not login");
            } else {
                System.out.println("Email: " + email + " + Password: " + password + ", can login");
            }
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adminUpdateCompany(Company companyToUpdate) {
        try {
            adminServiceImp.updateCompany(companyToUpdate.getId(), companyToUpdate);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adminInsertCompany(Company company) {
        try {
            adminServiceImp.addCompany(company);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adminInsertCustomer(Customer customer) {
        try {
            adminServiceImp.addCustomer(customer);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adminUpdateCustomer(Customer customer) {
        try {
            adminServiceImp.updateCustomer(customer.getId(), customer);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adminDeleteCustomer(int customerId) {
        try {
            adminServiceImp.deleteCustomer(customerId);
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
    }
}

