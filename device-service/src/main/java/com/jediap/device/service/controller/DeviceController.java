package com.jediap.device.service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeviceController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/actuator/hello")
    public String deviceService(){
        return "Hello to Device Service with port "+port;
    }
}
