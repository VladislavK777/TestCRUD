package com.vladislavk.testcrud.configuration.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-09
 */

public class JWTAuthFilter extends GenericFilterBean {
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication = JWTTokenAuthService
                .getAuthentication((HttpServletRequest) servletRequest);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
