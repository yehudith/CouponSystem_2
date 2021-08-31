package com.jb.couponSystem.repoos;

import com.jb.couponSystem.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    boolean existsByEmail(String email);
    Customer findByEmailAndPassword(String email, String password);
    void deleteById(int id);
    boolean existsByEmailAndPassword(String email, String password);
}
