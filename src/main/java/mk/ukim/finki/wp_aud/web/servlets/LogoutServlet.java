package mk.ukim.finki.wp_aud.web.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud.service.AuthService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "logout-servlet", urlPatterns = "/servlet/logout")
public class LogoutServlet extends HttpServlet {

    private final AuthService authService;
    private final SpringTemplateEngine springTemplateEngine;

    public LogoutServlet(AuthService authService, SpringTemplateEngine springTemplateEngine) {
        this.authService = authService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().invalidate();
        resp.sendRedirect("/login");
    }
}
