package com.emcode.tabz.service;

import com.emcode.tabz.dto.ShopRequest;
import com.emcode.tabz.dto.ShopResponse;
import com.emcode.tabz.dto.ShopResponseCreate;

public interface ShopService {

    ShopResponse getShopById(Long id);

    ShopResponseCreate createShop(ShopRequest shopRequest);


}
