package com.example.productservicerestapi;

import com.example.productservicerestapi.Contracts.ProductService;
import com.example.productservicerestapi.Exeptions.PatientBussinessExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ProductServiceController {
    @Autowired
    ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id")int id, @RequestBody Product product) {
        Product currentId = this.productService.getProduct(id);
        if (currentId == null){
            return new ResponseEntity<>("Product is not modified", HttpStatus.NOT_MODIFIED);
        }

        this.productService.updateProduct(id, product);
        return new ResponseEntity<>(this.productService.getProduct(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProduct(@PathVariable("id") int id){
        if (this.productService.getProduct(id) == null){
            return  new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.productService.getProduct(id), HttpStatus.OK);
    }


    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        this.productService.createProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") int id) {

        List<Product> products = this.productService.getProducts();
        for (Product product : products) {
            if (product.getId() != id){
                return new ResponseEntity<>("Id not found", HttpStatus.NOT_FOUND);
            }else {
                this.productService.deleteProduct(product.getId());
            }
        }


        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }


}
