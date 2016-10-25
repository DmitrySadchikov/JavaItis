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
            String path = ((HttpServletRequest)servletRequest).getRequestURI();
            Cookie[] cookies = ((HttpServletRequest)servletRequest).getCookies();
            boolean flag = false;
            if(cookies != null) {
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals("token") && !flag) {
                        String token = cookie.getValue();
                        verifyUserExistByToken(token);
                        flag = true;
                        if(path.equals("/") || path.equals("/login") || path.equals("/registration")) {
                            ((HttpServletResponse) servletResponse).sendRedirect("/profile");
                            return;
                        }
                    }
                }
                if(!flag && (!path.equals("/") || !path.equals("/login")
                        || !path.equals("/registration") || !path.equals("/favicon.ico"))) {
                    ((HttpServletResponse) servletResponse).sendRedirect("/");
                }
            }
            if(path.equals("/favicon.ico")) {
                return;
            }
                //return;
            //filterChain.doFilter(servletRequest, servletResponse);


        } catch (IllegalArgumentException e) {
            ((HttpServletResponse) servletResponse).sendRedirect("/");
            //filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    public void destroy() {

    }
}
