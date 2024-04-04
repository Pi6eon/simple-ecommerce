package com.fallinnadim.productservice.controller;

import com.fallinnadim.productservice.dto.GenericResponse;
import com.fallinnadim.productservice.dto.ProductRequest;
import com.fallinnadim.productservice.dto.ProductResponse;
import com.fallinnadim.productservice.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private final ProductServiceImpl productServiceImpl;

    @PostMapping
    public ResponseEntity<GenericResponse> createProduct(@RequestBody ProductRequest productRequest) {
        String message = productServiceImpl.createProduct(productRequest);
        return new ResponseEntity<>(new GenericResponse(message), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productServiceImpl.getAllProducts();
    }
}
