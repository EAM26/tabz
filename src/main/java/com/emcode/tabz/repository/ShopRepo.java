package com.emcode.tabz.repository;

import com.emcode.tabz.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepo extends JpaRepository<Shop, Long> {
}
