package com.emcode.tabz.service.imp;

import com.emcode.tabz.service.TabService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TabServiceBasic implements TabService {

    @Override
    public String createTab(MultipartFile multipartFile, Long merchantId) {
        String originalFileName = multipartFile.getOriginalFilename();
        return "Service running: " + originalFileName;
    }
}
