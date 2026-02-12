@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "forgot_password";
    }

    @PostMapping("/forgot-password")
    public String processForgot(@RequestParam String email) {

        User user = userRepository.findByEmail(email);

        if (user != null) {
            String token = UUID.randomUUID().toString();
            user.setResetToken(token);
            userRepository.save(user);

            emailService.sendResetEmail(email, token);
        }

        return "redirect:/login";
    }
}
