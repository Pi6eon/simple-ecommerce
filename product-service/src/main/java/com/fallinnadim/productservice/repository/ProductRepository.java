package com.fallinnadim.productservice.repository;

import com.fallinnadim.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
