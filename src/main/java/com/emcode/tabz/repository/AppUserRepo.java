package com.emcode.tabz.repository;

import com.emcode.tabz.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {
}
