package com.vladislavk.testcrud.dao;

import java.util.List;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-06
 */

public interface ProductDao {
    List getAllProduct();

    <T> List getByName(String t, Class<T> clazz);

    <T> List getByBrand(String t, Class<T> clazz);

    <T> T createObject(T object);

    <T> int updateObject(int id, T object, Class<T> clazz);

    List getListOvers();

    <T> void deleteProduct(int id, Class<T> clazz);
}
