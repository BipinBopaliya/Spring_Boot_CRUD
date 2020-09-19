package com.example.demo.service;

import com.example.demo.repository.ProductRepository;
import com.example.demo.model.Product;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Mono<Product> createProduct(final Product product) {
        return this.repository
                .save(product);
    }

    public Publisher<Product> getAllProducts() {
        return this.repository
                .findAll();
    }

    @Transactional
    public Mono<Product> updateProduct(final Product product) {
//        return this.repository.findById(product.getId())
//                .flatMap(p -> {
//                    p.setDescription(product.getDescription());
//                    p.setPrice(product.getPrice());
//                    return this.repository.save(p);
//                })
//                .switchIfEmpty(this.repository.save(product.setAsNew()));
        return this.repository.findById(product.getId());
    }

    @Transactional
    public Mono<Void> deleteProduct(final int id) {
        return this.repository.findById(id)
                .flatMap(this.repository::delete);
    }

    @Transactional
    public Mono<Product> delete(int id) {
        return this.repository
                .findById(id)
                .flatMap(p -> this.repository.deleteById(p.getId()).thenReturn(p));
    }

}
