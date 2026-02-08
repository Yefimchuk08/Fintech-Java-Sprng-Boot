@Controller
public class AuthController {

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
}
