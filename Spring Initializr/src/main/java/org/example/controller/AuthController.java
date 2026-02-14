package org.example.crudapp.controller;

import org.example.crudapp.model.User;
import org.example.crudapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // =========================
    // СТОРІНКА FORGOT PASSWORD
    // =========================

    // Відкриває сторінку введення email
    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot-password";
    }

    // Обробка email і генерація токена
    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {

        // Шукаємо користувача по email
        User user = userRepository.findByEmail(email);

        if (user != null) {

            // Генеруємо унікальний токен
            String token = UUID.randomUUID().toString();

            // Зберігаємо токен у базі
            user.setResetToken(token);
            userRepository.save(user);

            // Тут буде відправка email (поки просто посилання)
            System.out.println(
                    "Reset link: http://localhost:8080/reset-password?token=" + token
            );
        }

        return "redirect:/login";
    }

    // =========================
    // RESET PASSWORD
    // =========================

    // Відкриває сторінку зміни пароля
    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam String token, Model model) {

        // Передаємо токен у форму
        model.addAttribute("token", token);

        return "reset-password";
    }

    // Обробка нового пароля
    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String token,
            @RequestParam String password) {

        // Шукаємо користувача по токену
        User user = userRepository.findByResetToken(token);

        if (user != null) {

            // Встановлюємо новий пароль
            user.setPassword(password);

            // Видаляємо токен
            user.setResetToken(null);

            userRepository.save(user);
        }

        return "redirect:/login";
    }
}
