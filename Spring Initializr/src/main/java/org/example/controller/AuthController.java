@Controller
public class AuthController {

    // Відкриває сторінку відновлення пароля (GET запит)
    // Викликається коли користувач переходить по посиланню /reset-password
    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam String token, Model model) {

        // Передаємо token у HTML сторінку,
        // щоб він відправився назад при зміні пароля
        model.addAttribute("token", token);

        // Повертаємо thymeleaf шаблон reset-password.html
        return "reset-password";
    }

    // Обробка форми зміни пароля (POST запит)
    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String token,
            @RequestParam String password) {

        // Шукаємо користувача по токену відновлення
        User user = userRepository.findByResetToken(token);

        // Якщо користувач знайдений
        if (user != null) {

            // Встановлюємо новий пароль
            // (у реальному проекті пароль потрібно шифрувати)
            user.setPassword(password);

            // Видаляємо токен, щоб його не можна було використати повторно
            user.setResetToken(null);

            // Зберігаємо зміни в базу даних
            userRepository.save(user);
        }

        // Після успішної зміни пароля перенаправляємо на сторінку входу
        return "redirect:/login";
    }
}
