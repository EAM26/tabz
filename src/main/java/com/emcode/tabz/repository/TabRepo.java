package com.emcode.tabz.repository;

import com.emcode.tabz.model.Tab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabRepo extends JpaRepository<Tab, Long> {
    List<Tab> findAllByUserId(Long userId);
}
