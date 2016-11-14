package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.itis.utils.Verifier.verifyUserExistByToken;

public class RedirectFilter implements Filter{


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            String token = ((HttpServletRequest) servletRequest).getHeader("token");
            if(token != null) {
                verifyUserExistByToken(token);
                ((HttpServletResponse) servletResponse).sendRedirect("/profile");
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
