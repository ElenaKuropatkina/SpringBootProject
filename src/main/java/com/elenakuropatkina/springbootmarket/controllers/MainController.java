package com.elenakuropatkina.springbootmarket.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login_form";
    }

    @GetMapping("/products/forCustomers")
    public String productForUser() {
        return "products_for_customers";
    }

    @GetMapping("/users")
    public String userList() {
        return "users";
    }
}


