package com.example.odev.business.responses;

import com.example.odev.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class GetProductsDetails {
    private int id;
    private String name;
    private Double price;
    private String explanation;
    private String categoryName;
}
