package mk.ukim.finki.wp_aud.web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp_aud.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @GetMapping
    public String logout(HttpServletRequest req, Model model){

        req.getSession().invalidate();

        return "redirect:/login";
    }

}
