package com.freeefly.userservice2.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("users")
public class User {

    @Id
    private Integer id;
    private String name;
    private Integer balance;

}
