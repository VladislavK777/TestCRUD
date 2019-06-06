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
    private int id;

    private String name;

    private String brand;

    private float price;

    private int quantity;
}
