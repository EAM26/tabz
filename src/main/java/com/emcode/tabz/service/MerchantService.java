package com.emcode.tabz.service;

import com.emcode.tabz.dto.MerchantRequest;
import com.emcode.tabz.dto.MerchantResponse;

public interface MerchantService {

    MerchantResponse getMerchantById(Long id);

    Long createMerchant(MerchantRequest merchantRequest);


}
