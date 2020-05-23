package kg.attractor.chatgram.frontcontroller;

import kg.attractor.chatgram.model.CustomerRegisterForm;
import kg.attractor.chatgram.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping
    public String chatPage(){
        return "chat";
    }

    @GetMapping("/profile")
    public String pageCustomerProfile(Model model, Principal principal)
    {
        var user = userService.getByEmail(principal.getName());
        model.addAttribute("dto", user);
        return "profile";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {

        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new CustomerRegisterForm());
        }

        return "register";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }
}
