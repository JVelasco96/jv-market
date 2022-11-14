package com.jv.market.domain.repository;

import com.jv.market.domain.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Product> getAll();
    Optional<List<Product>> getByCategory(int categoryId);
    Optional<List<Product>> getScarseProduct(int quantity);
    Optional<Product> getProduct( int productId);
    Product save(Product product);
    void delete(int productId);
}
