package com.jb.couponSystem.repoos;

import com.jb.couponSystem.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    boolean existsByEmail(String email);
    boolean existsByName(String name);
    Company findByEmailAndPassword(String email, String password);
    boolean existsByEmailAndPassword(String email, String password);
    @Query(value = "SELECT `company`.* FROM `company` where `id` = :companyId",nativeQuery = true)
    Company getCompany(int companyId);
}
