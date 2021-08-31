package com.jb.couponSystem.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;


@Entity
//@Table(catalog = "Coupons")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private Category category;
    @ManyToOne
    @ToString.Exclude
    @JsonIgnoreProperties("coupons")
    private Company company;
    private String title;
    private String description;
    private int amount;
    private Date startDate;
    private Date endDate;
    private double price;
    private String image;

}
