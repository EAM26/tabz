package com.emcode.tabz.service;

import com.emcode.tabz.dto.ClaimRequest;
import com.emcode.tabz.dto.TabResponse;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TabService {

    byte[] createTab(MultipartFile multipartFile, Shop shop);

    String claim(Long tabId, User user, ClaimRequest request);

    List<TabResponse> getTabsByUserId(Long id);

    List<TabResponse> getTabsByShopId(Long id);
}
