package com.emcode.tabz.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Tab> tabs;
}
