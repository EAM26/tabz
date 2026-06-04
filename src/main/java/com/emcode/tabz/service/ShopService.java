package com.emcode.tabz.service;

import com.emcode.tabz.dto.ShopRequest;
import com.emcode.tabz.dto.ShopResponse;

public interface ShopService {

    ShopResponse getShopById(Long id);

    ShopResponse createShop(ShopRequest shopRequest);


}
