package ru.itis.filters;

import javax.servlet.*;
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
                String token = ((HttpServletRequest) servletRequest).getHeader("token");
                if(token != null) {
                    verifyUserExistByToken(token);
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                else
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
