package com.vladislavk.testcrud.controller;

import com.vladislavk.testcrud.dao.ProductDaoImpl;
import com.vladislavk.testcrud.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-05
 */

// Используем контроллер, при обращение на страницы /api/v2/...
// будут вызваны соответсвующие методы
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "api/v2", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestController {

    @Autowired
    private ProductDaoImpl productDaoImpl;

    @GetMapping(value = "")
    public List getAllProduct() {
        return productDaoImpl.getAllProduct();
    }

    @GetMapping(value = "/search/name")
    public List getProductByName(@RequestParam("name") String name) {
        return productDaoImpl.getByName(name, Product.class);
    }

    @GetMapping(value = "/search/brand")
    public List getProductByBrand(@RequestParam("brand") String brand) {
        return productDaoImpl.getByBrand(brand, Product.class);
    }

    @GetMapping(value = "/leftovers")
    public List getLeftOvers() {
        return productDaoImpl.getListOvers();
    }

    @PostMapping(value = "/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productDaoImpl.createObject(product);
    }

    @PutMapping(value = "/update/{id}")
    public int updateProduct(@PathVariable int id,
                                 @RequestBody Product product) {
        return productDaoImpl.updateObject(id, product, Product.class);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productDaoImpl.deleteProduct(id, Product.class);
    }
}
