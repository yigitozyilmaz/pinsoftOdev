package com.example.odev.webApiControllers;

import com.example.odev.business.abstracts.ProductService;
import com.example.odev.business.abstracts.UserService;
import com.example.odev.business.responses.GetAllProducts;
import com.example.odev.business.responses.GetProductsDetails;
import com.example.odev.business.responses.ProductRequest;
import com.example.odev.business.responses.UserRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@NoArgsConstructor
public class ProductsControllers {

    @Autowired
    private ProductService productService;


    @GetMapping("/getAll")
    public List<GetAllProducts> getAll() {
        return productService.getAll();
    }

    @GetMapping("/getProductDetails")
    public List<GetProductsDetails> findAll() {
        return productService.findAll();
    }



    @PostMapping("/addP")
    public String saveProduct(@RequestBody ProductRequest request) {
        productService.saveProductToDB(request.getAddress(), request.getName(), request.getExplanation(), request.getPrice(), request.getCategory_id());
        return "redirect:/listProduct.html";
    }

@Autowired
    private UserService userService;
    @PostMapping("/register")
    public String showRegistrationForm(@RequestBody UserRequest request){
        // create model object to store form data
        userService.saveUserToDB(request.getUsername(),request.getEmail(),request.getPassword());
        return "basarili" ;
    }

}
