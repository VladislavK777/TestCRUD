package com.vladislavk.testcrud.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-05
 */

// Используем аннотацию Data от Lombok.
// Позволяет заменить сеттеры, геттеры, equals и hash
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price")
    private float price;

    @Column(name = "quantity")
    private int quantity;
}
