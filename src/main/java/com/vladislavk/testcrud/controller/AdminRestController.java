package com.vladislavk.testcrud.controller;

import com.vladislavk.testcrud.dao.ProductDaoImpl;
import com.vladislavk.testcrud.entity.Product;
import com.vladislavk.testcrud.model_ex.ResultOfSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-05
 */

// Используем контроллер, при обращение на страницы /api/v2/...
// будут вызваны соответсвующие методы
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController {
    private static Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    private ProductDaoImpl productDaoImpl;
    @Autowired
    private ResultOfSearch resultOfSearch;

    @PostMapping(value = "/admin/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        logger.debug("API: createProduct - request: {}", product.toString());
        return productDaoImpl.createObject(product);
    }

    @PutMapping(value = "/admin/update/{id}")
    public int updateProduct(@PathVariable int id,
                                 @RequestBody Product product) {
        logger.debug("API: updateProduct - request: {}, {}", id, product.toString());
        return productDaoImpl.updateObject(id, product, Product.class);
    }

    @DeleteMapping(value = "/admin/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        logger.debug("API: deleteProduct - request: {}", id);
        productDaoImpl.deleteProduct(id, Product.class);
    }
}
