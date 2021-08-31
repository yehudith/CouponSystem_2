package com.jb.couponSystem.Services;

import com.jb.couponSystem.repoos.CompanyRepository;
import com.jb.couponSystem.repoos.CouponRepository;
import com.jb.couponSystem.repoos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {

    @Autowired
    protected CouponRepository couponRepository;
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;

    public abstract boolean login(String email, String password);
}
