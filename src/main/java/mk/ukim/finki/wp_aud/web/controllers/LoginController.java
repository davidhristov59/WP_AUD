package mk.ukim.finki.wp_aud.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String getLoginPage(){

        return "login";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model){

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;

        try {
            user = authService.login(username, password);
            request.getSession().setAttribute("user", user);

            return "redirect:/home"; //koga ke se logiras, odi kon home
        } catch(RuntimeException e){
            model.addAttribute("hasError", true);
            model.addAttribute("error", e.getMessage());

            return "login";
        }

    }


}
