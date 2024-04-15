package com.fallinnadim.productservice.service;

import com.fallinnadim.productservice.dto.ProductRequest;
import com.fallinnadim.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
}
