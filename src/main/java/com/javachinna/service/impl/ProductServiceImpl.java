package com.javachinna.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import com.javachinna.model.Product;
import com.javachinna.repo.ProductRepository;
import com.javachinna.service.ProductService;
import io.keploy.ksql.KDriver;

public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

//    static {
//        // Print statement
//        System.out.print(
//                "Static block can be printed without main method");
//        try {
//            KDriver.WrapDriver();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Inject
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        return productRepository.update(product);
    }

}
