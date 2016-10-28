package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.itis.utils.Verifier.verifyUserExistByToken;

public class RedirectFilter implements Filter{


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();
            if(cookies != null) {
                boolean flag = false;
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals("token")) {
                        String token = cookie.getValue();
                        verifyUserExistByToken(token);
                        ((HttpServletResponse) servletResponse).sendRedirect("/profile");
                        flag = true;
                    }
                }
                if(!flag)
                    filterChain.doFilter(servletRequest, servletResponse);
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
