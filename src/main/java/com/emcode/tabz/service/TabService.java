package com.emcode.tabz.service;

import com.emcode.tabz.dto.ClaimRequest;
import com.emcode.tabz.dto.ShopTabResponse;
import com.emcode.tabz.dto.UserTabResponse;
import com.emcode.tabz.model.Shop;
import com.emcode.tabz.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

public interface TabService {

    byte[] createTab(MultipartFile multipartFile, BigDecimal totalAmount, Shop shop);

    String claim(Long tabId, User user, ClaimRequest request);

    List<UserTabResponse> getTabsByUserId(Long id);

    List<ShopTabResponse> getTabsByShopId(Long id);
}
