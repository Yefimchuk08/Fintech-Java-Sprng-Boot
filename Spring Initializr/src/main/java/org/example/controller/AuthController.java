package org.example.crudapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.crudapp.entity.User;
import org.example.crudapp.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;

    // =========================
    // REGISTER
    // =========================
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // =========================
    // FORGOT PASSWORD
    // =========================
    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot_password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam String email) {

        User user = userRepository.findByEmail(email);

        if (user != null) {
            String token = UUID.randomUUID().toString();
            user.setResetToken(token);
            userRepository.save(user);

            // тут буде EmailService.send(...)
            System.out.println("Reset link: http://localhost:8080/reset-password?token=" + token);
        }

        return "redirect:/";
    }

    // =========================
    // RESET PASSWORD PAGE
    // =========================
    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "reset_password";
    }

    // =========================
    // RESET PASSWORD SAVE
    // =========================
    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String token,
            @RequestParam String password) {

        User user = userRepository.findByResetToken(token);

        if (user != null) {
            user.setPassword(password);
            user.setResetToken(null);
            userRepository.save(user);
        }

        return "redirect:/login";
    }
}
