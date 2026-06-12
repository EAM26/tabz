package com.emcode.tabz.service;

import com.emcode.tabz.dto.ClaimRequest;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.model.User;
import org.springframework.web.multipart.MultipartFile;

public interface TabService {

    byte[] createTab(MultipartFile multipartFile, Shop shop);

    String claim(Long tabId, User user, ClaimRequest request);
}
