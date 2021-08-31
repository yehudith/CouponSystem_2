package com.jb.couponSystem.Services;

import com.jb.couponSystem.beans.Company;
import com.jb.couponSystem.beans.Customer;
import com.jb.couponSystem.excep.CouponSystemException;

import java.util.List;

public interface AdminService {
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(int companyId, Company company) throws CouponSystemException;
    void deleteCompany(int companyId)throws CouponSystemException;
    List<Company> getAllCompanies();
    Company getSignalCompany(int companyId) throws CouponSystemException;


    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(int customerId,Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerId) throws CouponSystemException;
    List<Customer> getAllCustomers();
    Customer getSignalCustomer(int customerId) throws CouponSystemException;

}

