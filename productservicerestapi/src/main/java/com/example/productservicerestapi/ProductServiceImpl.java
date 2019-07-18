package com.example.productservicerestapi;

import com.example.productservicerestapi.Contracts.ProductService;
import com.example.productservicerestapi.Exeptions.PatientBussinessExeption;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    Map<Integer, Product> productMap;
    private int currentId = 1;

    public ProductServiceImpl() {
        this.productMap = new HashMap<>();
        init();

    }

    private void init() {
        Product product = new Product();
        product.setId(currentId);
        product.setName("Mustard");
        this.productMap.put(product.getId(), product);

    }

    @Override
    public void createProduct(Product product) {
        product.setId(++currentId);
        this.productMap.put(product.getId(), product);
    }

    @Override
    public void updateProduct(int id, Product product) {
        this.productMap.remove(id);
        product.setId(id);
        productMap.put(id, product);

    }


    @Override
    public void deleteProduct(int id) {
        this.productMap.remove(id);
    }


    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(this.productMap.values());
    }

    @Override
    public Product getProduct(int id) {
        return this.productMap.get(id);
    }
}
