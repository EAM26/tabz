package com.emcode.tabz.controller;

import com.emcode.tabz.util.QRGenerator;
import com.google.zxing.WriterException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/tab")
public class TabController {

    @PostMapping
    public ResponseEntity<byte[]> createTab(@RequestBody String url) throws IOException, WriterException {
        QRGenerator generator = new QRGenerator();
        URI location = URI.create("fake-location");
        byte[] qrImage =  generator.generateQRCode(url, 300, 300);
        return ResponseEntity
                .created(location)
                .contentType(MediaType.IMAGE_PNG)
                .body(qrImage);
    }


}
