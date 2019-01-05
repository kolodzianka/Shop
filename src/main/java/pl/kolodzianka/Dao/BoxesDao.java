package pl.kolodzianka.Dao;

import pl.kolodzianka.entities.Product;

import java.io.IOException;
import java.util.List;

public interface BoxesDao {

    List findAll();
    List <Product> findByUser(String userName);
    void clearBox();
    void saveOrUpdate(String userName, List<Product> products) throws IOException;

}
