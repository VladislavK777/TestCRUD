package com.vladislavk.testcrud.controller;

import com.vladislavk.testcrud.dao.ProductDaoImpl;
import com.vladislavk.testcrud.entity.Product;
import com.vladislavk.testcrud.model_ex.ResultOfSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-07
 */

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController {
    private static Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private ProductDaoImpl productDaoImpl;
    @Autowired
    private ResultOfSearch resultOfSearch;

    @GetMapping(value = "/all")
    public List getAllProduct() {
        List<Product> listResult = productDaoImpl.getAllProduct();
        resultOfSearch.setProductList(listResult);
        logger.debug("API: getAllProduct - result: {}", listResult);
        return listResult;
    }

    @GetMapping(value = "/search/name")
    public List getProductByName(@RequestParam("name") String name) {
        List<Product> listResult = productDaoImpl.getByName(name, Product.class);
        resultOfSearch.setProductList(listResult);
        logger.debug("API: getProductByName - request: {}; result: {}", name, listResult);
        return listResult;
    }

    @GetMapping(value = "/search/brand")
    public List getProductByBrand(@RequestParam("brand") String brand) {
        List<Product> listResult = productDaoImpl.getByBrand(brand, Product.class);
        resultOfSearch.setProductList(listResult);
        logger.debug("API: getProductByBrand - request: {}; result: {}", brand, listResult);
        return listResult;
    }

    @GetMapping(value = "/leftovers")
    public List getLeftOvers() {
        List<Product> listResult = productDaoImpl.getListOvers();
        logger.debug("API: getLeftOvers - result: {}", listResult);
        return listResult;
    }
}
