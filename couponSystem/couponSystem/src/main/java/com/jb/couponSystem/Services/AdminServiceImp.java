package com.jb.couponSystem.Services;

import com.jb.couponSystem.beans.Company;
import com.jb.couponSystem.beans.Customer;
import com.jb.couponSystem.excep.CouponSystemException;
import com.jb.couponSystem.excep.ErrorMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp extends ClientService implements AdminService {
    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrorMessage.COMPANY_EMAIL_EXIST);
        }
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrorMessage.COMPANY_NAME_EXIST);
        }
        this.companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemException {
        if(!companyRepository.existsById(companyId)){
            throw new CouponSystemException(ErrorMessage.COMPANY_ID_NOT_EXIST);
        }
        Company company1 = companyRepository.getCompany(companyId);

        if(!company1.getName().equals(company.getName())){
            throw  new CouponSystemException(ErrorMessage.COMPANY_NAME_IS_NOT_VALID);
        }
        company.setId(companyId);
        companyRepository.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        if(!companyRepository.existsById(companyId)){
            throw new CouponSystemException(ErrorMessage.COMPANY_ID_NOT_EXIST);
        }
        companyRepository.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSignalCompany(int companyId) throws CouponSystemException {
        return companyRepository.findById(Integer.valueOf(companyId)).orElseThrow(()->new CouponSystemException(ErrorMessage.CUSTOMER_IS_NOT_EXIST));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if(customerRepository.existsByEmail(customer.getEmail())){
            throw new CouponSystemException(ErrorMessage.CUSTOMER_EXIST);
        }
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId,Customer customer) throws CouponSystemException {
        if(!customerRepository.existsById(customerId)){
            throw new CouponSystemException(ErrorMessage.COMPANY_ID_NOT_EXIST);
        }
        customer.setId(customerId);
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSystemException {
        if(!customerRepository.existsById(customerId)){
            throw new CouponSystemException(ErrorMessage.CUSTOMER_IS_NOT_EXIST);
        }
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSignalCustomer(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow(()->new CouponSystemException(ErrorMessage.CUSTOMER_IS_NOT_EXIST));
    }

    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }
}
