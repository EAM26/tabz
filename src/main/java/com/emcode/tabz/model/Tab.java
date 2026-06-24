package com.emcode.tabz.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class Tab {

    @Id
    @GeneratedValue
    private Long id;

    private String fileName;
    private LocalDateTime createdAt;
    private boolean claimed = false;
    private BigDecimal totalAmount;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private User user;
}
