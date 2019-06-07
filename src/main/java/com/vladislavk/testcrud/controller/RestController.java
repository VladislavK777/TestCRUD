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

import java.util.List;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-05
 */

// Используем контроллер, при обращение на страницы /api/v2/...
// будут вызваны соответсвующие методы
@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestController {
    private static Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    private ProductDaoImpl productDaoImpl;
    @Autowired
    private ResultOfSearch resultOfSearch;

    @GetMapping(value = "/all")
    public List getAllProduct() {
        List<Product> listResult = productDaoImpl.getAllProduct();
        resultOfSearch.setProductList(listResult);
        return listResult;
    }

    @GetMapping(value = "/search/name")
    public List getProductByName(@RequestParam("name") String name) {
        List<Product> listResult = productDaoImpl.getByName(name, Product.class);
        resultOfSearch.setProductList(listResult);
        return listResult;
    }

    @GetMapping(value = "/search/brand")
    public List getProductByBrand(@RequestParam("brand") String brand) {
        List<Product> listResult = productDaoImpl.getByBrand(brand, Product.class);
        resultOfSearch.setProductList(listResult);
        return listResult;
    }

    @GetMapping(value = "/leftovers")
    public List getLeftOvers() {
        return productDaoImpl.getListOvers();
    }

    @PostMapping(value = "/admin/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productDaoImpl.createObject(product);
    }

    @PutMapping(value = "/admin/update/{id}")
    public int updateProduct(@PathVariable int id,
                                 @RequestBody Product product) {
        return productDaoImpl.updateObject(id, product, Product.class);
    }

    @DeleteMapping(value = "/admin/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productDaoImpl.deleteProduct(id, Product.class);
    }
}
