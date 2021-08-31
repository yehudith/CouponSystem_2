package com.jb.couponSystem.security;

import com.jb.couponSystem.Services.*;
import com.jb.couponSystem.excep.CouponSystemException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginManager {

    @Autowired
    private ApplicationContext ctx;

    //FactoryMethod
    public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException {
        switch (clientType) {
            case Admin:
                AdminService adminService = ctx.getBean(AdminService.class);
                if (((AdminServiceImp)adminService).login(email, password))
                    return (ClientService)adminService;
                break;
            case Company:
                CompanyService companyService = ctx.getBean(CompanyService.class);
                if (((CompanyServiceImp)companyService).login(email, password)) {
                    int companyId = companyService.getCompanyByEmailAndPassword(email, password).getId();
                    ((CompanyServiceImp) companyService).setCompanyId(companyId);
                    return (ClientService)companyService;
                }
                break;
            case Customer:
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (((CustomerServiceImp)customerService).login(email, password)) {
                    int customerId = customerService.getCustomerByEmailAndPassword(email, password).getId();
                    ((CustomerServiceImp) customerService).setCustomerId(customerId);
                    return (ClientService) customerService;
                }
        }
        return null;
    }
}
