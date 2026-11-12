package org.example.appapi.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map<String, String> home() {
        return Map.of(
                "application", "AppApi",
                "status", "running",
                "message", "API is running. See /api/allcategories for an available endpoint.");
    }
}
