package com.example.odev.webApiControllers;

import com.example.odev.Entity.Category;
import com.example.odev.Entity.Product;
import com.example.odev.business.abstracts.ProductService;
import com.example.odev.business.abstracts.UserService;
import com.example.odev.business.concretes.ProductManager;
import com.example.odev.business.responses.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ProductManager productManager;

    @Autowired
    private UserService userService;

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

    @PostMapping("/update")
    public void updateProduct(@RequestBody ProductUpdateReq request){
productService.updateProduct(request.getId(),request.getAddress(), request.getName(), request.getExplanation(), request.getPrice(), request.getCategory_id());
    }


    @PostMapping("/delete")
    public void deleteProduct(@RequestBody long id){
        productService.deleteProduct(id);

    }

    @PostMapping("/register")
    public String showRegistrationForm(@RequestBody UserRequest request){
        // create model object to store form data
        userService.saveUserToDB(request.getUsername(),request.getEmail(),request.getPassword());
        return "basarili" ;
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetProductsDetails>> searchProducts(@RequestParam String name) {
        List<GetProductsDetails> searchResults = productManager.searchProducts(name);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @GetMapping("/searchById")
    public ResponseEntity<List<GetProductsDetails>> searchById (@RequestParam long id) {
        List<GetProductsDetails> searchResults = productManager.searchById(id);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }
}
