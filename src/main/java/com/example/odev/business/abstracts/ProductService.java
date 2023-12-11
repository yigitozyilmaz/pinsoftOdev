package com.example.odev.business.abstracts;

import com.example.odev.business.responses.GetAllProducts;
import com.example.odev.business.responses.GetProductsDetails;

import java.util.List;

public interface ProductService {
    List<GetAllProducts> getAll();

    GetProductsDetails getProductsDetails();
}
