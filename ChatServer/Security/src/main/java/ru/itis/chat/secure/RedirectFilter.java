package ru.itis.chat.secure;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.itis.chat.validators.UserValidator.verifyUserExistByToken;


public class RedirectFilter implements Filter{


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            String token = request.getHeader("token");
            if(token != null && !token.equals("")) {
                verifyUserExistByToken(token);
                response.sendRedirect("/profile");
            }
            else
                filterChain.doFilter(servletRequest, servletResponse);
        } catch (IllegalArgumentException e) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    public void destroy() {

    }
}
