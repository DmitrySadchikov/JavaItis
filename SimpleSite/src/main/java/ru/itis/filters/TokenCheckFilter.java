package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.itis.utils.Verifier.verifyUserExistByToken;

public class TokenCheckFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException{
        try {
            String path = ((HttpServletRequest)servletRequest).getRequestURI();
            if (path.equals("/") || path.equals("/login") || path.equals("/logout")
                    || path.equals("/registration") || path.equals("/favicon.ico")) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else {
                Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();
                boolean flag = false;
                if(cookies != null) {
                    for(Cookie cookie : cookies) {
                        if(cookie.getName().equals("token")) {
                            String token = cookie.getValue();
                            verifyUserExistByToken(token);
                            filterChain.doFilter(servletRequest, servletResponse);
                            flag = true;
                        }
                    }
                }
                if(!flag)
                    ((HttpServletResponse) servletResponse).sendRedirect("/");

            }
        } catch (IllegalArgumentException e) {
            ((HttpServletResponse) servletResponse).sendRedirect("/");
        } catch (ServletException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void destroy() {

    }
}
