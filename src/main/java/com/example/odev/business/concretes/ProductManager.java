package com.example.odev.business.concretes;

import com.example.odev.Entity.Product;
import com.example.odev.Repository.ProductRepository;
import com.example.odev.business.abstracts.ProductService;
import com.example.odev.business.responses.GetAllProducts;
import com.example.odev.business.responses.GetProductsDetails;
import com.example.odev.mappers.ModelMapperService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductManager implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllProducts> getAll() {
        List<Product> products = productRepository.findAll();
        List<GetAllProducts> getAllProducts = products.stream()
                .map(product -> this.modelMapperService.forResponse()
                        .map(product, GetAllProducts.class)).collect(Collectors.toList());
        return getAllProducts;
    }

    @Override
    public List<GetProductsDetails> findAll() {
        List<Product> products = productRepository.findAll();
        List<GetProductsDetails> getProductsDetails = products.stream()
                .map(product -> this.modelMapperService.forResponse()
                        .map(product, GetProductsDetails.class)).collect(Collectors.toList());
        return getProductsDetails;
    }
}
