package com.beki7.ecommerce.model;

import jakarta.persistence.*;

@Entity(name="CATEGORY")
public class Category {
    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;
}
