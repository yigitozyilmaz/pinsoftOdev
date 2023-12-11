package com.example.odev.webApiControllers;

import com.example.odev.business.abstracts.ProductService;
import com.example.odev.business.responses.GetAllProducts;
import com.example.odev.business.responses.GetProductsDetails;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductsControllers {

    private ProductService productService;

    @GetMapping("/getAll")
    public List<GetAllProducts> getAll() {
        return productService.getAll();
    }

    @GetMapping("/getProductDetails")
    public GetProductsDetails getProductsDetails() {
        return productService.getProductsDetails();
    }


}
