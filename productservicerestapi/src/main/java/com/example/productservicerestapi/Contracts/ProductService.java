package com.example.productservicerestapi.Contracts;

import com.example.productservicerestapi.Exeptions.PatientBussinessExeption;
import com.example.productservicerestapi.Product;

import java.util.List;

public interface ProductService {
    void createProduct(Product product);
    void updateProduct(int id, Product product);
    void deleteProduct(int id);
    List<Product> getProducts();
    Product getProduct(int id);
}
