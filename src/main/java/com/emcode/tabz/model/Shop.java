package com.emcode.tabz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shop
{

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    @Column(name = "token_hash", unique = true)
    private String tokenHash;
    private boolean active;

    @OneToMany(mappedBy = "shop")
    private List<Tab> tabs;
}
