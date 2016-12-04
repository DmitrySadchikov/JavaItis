package ru.itis.chat.secure;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.itis.chat.validators.UserValidator.verifyUserExistByToken;


public class TokenCheckFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException{

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        try {
            String path = request.getRequestURI();
            if (path.equals("/login") || path.equals("/logout") || path.equals("/registration")
                    || path.equals("/favicon.ico")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                String token = request.getHeader("token");
                if(token != null && !token.equals("")) {
                    verifyUserExistByToken(token);
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                else
                    response.sendRedirect("/login");
            }
        } catch (IllegalArgumentException e) {
            response.sendRedirect("/login");
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void destroy() {

    }
}
