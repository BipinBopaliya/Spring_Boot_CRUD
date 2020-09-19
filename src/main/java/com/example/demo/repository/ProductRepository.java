package com.example.demo.repository;


import com.example.demo.model.Product;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

    @Query("select * from product")
    Flux<Product> findAllProducts();

    
}
