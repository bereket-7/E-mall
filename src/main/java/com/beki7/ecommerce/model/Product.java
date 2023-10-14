package com.beki7.ecommerce.model;

import jakarta.persistence.*;

@Entity(name="PRODUCT")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    private String image;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "category_id")
    private Category category;
    private int quantity;
    private int price;
    private int weight;
    private String description;
}
