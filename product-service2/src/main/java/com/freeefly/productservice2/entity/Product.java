package com.freeefly.productservice2.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@EqualsAndHashCode
public class Product {
    @Id
    private String id;
    private String description;
    private Integer price;


}
