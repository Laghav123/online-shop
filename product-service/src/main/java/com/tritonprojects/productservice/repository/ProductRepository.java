package com.tritonprojects.productservice.repository;

import com.tritonprojects.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


// this file will be interacting with database (MongoDB)
public interface ProductRepository extends MongoRepository<Product, String> {

}
