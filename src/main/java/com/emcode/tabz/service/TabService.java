package com.emcode.tabz.service;

import org.springframework.web.multipart.MultipartFile;

public interface TabService {

    String createTab(MultipartFile multipartFile, Long merchantId);
}
