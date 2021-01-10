package com.jediap.client.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/actuator/hello")
    public String clientService(){
        return "Hello to Client Service with port "+port;
    }
}
