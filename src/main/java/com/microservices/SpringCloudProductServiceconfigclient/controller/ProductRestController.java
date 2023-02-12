package com.microservices.SpringCloudProductServiceconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductRestController {

    @Value("${app.title}")
    private String title;

    @GetMapping("/data")
    public ResponseEntity<String> showProductMsg() {
        return new ResponseEntity<String>("Value of title from Config Server: "+title, HttpStatus.OK);
    }
}