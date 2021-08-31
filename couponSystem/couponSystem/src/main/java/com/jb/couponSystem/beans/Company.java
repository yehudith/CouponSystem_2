package com.jb.couponSystem.beans;

//import javax.annotation.processing.Generated;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(catalog = "Companies")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "company",fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)//(cascade = )
    @Singular
    @ToString.Exclude
    private List<Coupon> coupons;
}
