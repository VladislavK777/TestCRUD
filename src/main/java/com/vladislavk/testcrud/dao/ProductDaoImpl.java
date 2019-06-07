package com.vladislavk.testcrud.dao;

import com.vladislavk.testcrud.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-05
 */

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List getAllProduct() {
        return entityManager.createQuery("from Product")
                .getResultList();
    }

    @Override
    public <T> List getByName(String t, Class<T> clazz) {
        return entityManager.createQuery("from Product p where p.name = :name")
                .setParameter("name", t).getResultList();
    }

    @Override
    public <T> List getByBrand(String t, Class<T> clazz) {
        return entityManager.createQuery("from Product p where p.brand = :brand")
                .setParameter("brand", t).getResultList();
    }

    @Override
    public <T> T createObject(T object) {
        entityManager.persist(object);
        return object;
    }

    @Override
    public <T> int updateObject(int id, T object, Class<T> clazz) {
        Product obj = (Product) object;
        return entityManager.createQuery("update Product p set p.name = :name, p.brand = :brand, p.price = :price, p.quantity = :quantity where p.id = :id")
                .setParameter("name", obj.getName())
                .setParameter("brand", obj.getBrand())
                .setParameter("price", obj.getPrice())
                .setParameter("quantity", obj.getQuantity())
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public List getListOvers() {
        return entityManager.createQuery("from Product p where p.quantity < 5")
                .getResultList();
    }

    @Override
    public <T> void deleteProduct(int id, Class<T> clazz) {
        T object = entityManager.find(clazz, id);
        if (object != null) {
            entityManager.remove(object);
        }
    }
}
