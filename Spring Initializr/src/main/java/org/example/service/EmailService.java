@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendResetEmail(String to, String token) {

        String link = "http://localhost:8080/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset");
        message.setText("Click link to reset password:\n" + link);

        mailSender.send(message);
    }
}
