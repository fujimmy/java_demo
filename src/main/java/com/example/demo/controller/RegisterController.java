package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        return "register"; // 對應 register.hmtl
    }

    @PostMapping("/register")
    public String register(@RequestParam String username,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {
        if (userRepository.existsByEmail(email)) {
            model.addAttribute("message", "Email 已被註冊！");
            return "register";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        model.addAttribute("username", username);
        return "success"; // 對應 success.html
    }
}
