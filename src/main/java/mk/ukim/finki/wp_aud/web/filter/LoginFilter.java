package mk.ukim.finki.wp_aud.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud.model.User;

import java.io.IOException;

@WebFilter
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // Check if the user is logged in
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute("user");

        String path = ((HttpServletRequest) servletRequest).getServletPath();

        if (!path.equals("/login") && user == null) {
            // If the user is not logged in and the requested path is not the login page, redirect the user to the login page
            resp.sendRedirect("/login");
        } else {
            // If the user is logged in, continue with the request
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
