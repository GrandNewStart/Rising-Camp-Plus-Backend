package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    public Object home() {
        return new Response("Home");
    }

    @GetMapping("/test")
    public Object test() {
        return new Response("Hello?");
    }

    @GetMapping("/hello-world")
    public String hello() {
        return "Hello World";
    }
   
}

record Response(String message) {}
