package com.emcode.tabz.service;

import com.emcode.tabz.dto.ClaimRequest;
import org.springframework.web.multipart.MultipartFile;

public interface TabService {

    String createTab(MultipartFile multipartFile, Long shopId);

    String claim(Long tabId, ClaimRequest request);
}
