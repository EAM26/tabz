package com.emcode.tabz.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;
}
