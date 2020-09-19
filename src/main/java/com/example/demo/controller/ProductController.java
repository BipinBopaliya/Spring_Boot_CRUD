package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        //return new ResponseEntity<Flux<Product>>((Flux<Product>) this.productService.getAllProducts(), HttpStatus.OK);
        System.out.println("Hello");
        Producer p = new Producer();
        p.produce2();
        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PostMapping("/save")
    public Mono<Product> updateProduct(@RequestBody Product product) {
        return Objects.isNull(product.getId()) ?
                this.productService.createProduct(product) :
                this.productService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable int id) {
        return this.productService.deleteProduct(id);
    }
}
