package com.vladislavk.testcrud.configuration.jwt;

import com.vladislavk.testcrud.util.PropertyUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

/**
 * @author Vladislav Klochkov
 * @project testcrud
 * @date 2019-06-09
 */

public class JWTTokenAuthService {
    private static PropertyUtil propertyUtil = new PropertyUtil();
    private static final long EXPIRATION_TIME = Long.parseLong(propertyUtil.getProperty("auth.expiration.time"));
    private static final String SECRET_KEY = propertyUtil.getProperty("auth.secret.key");
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_AUTH = "Authorization";

    public static void addAuthentication(HttpServletResponse response, String username) {
        String JWT = Jwts.builder().setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
        response.addHeader(HEADER_AUTH, TOKEN_PREFIX + JWT);
    }

    public static Authentication getAuthentication(@NonNull HttpServletRequest request) {
        String token = request.getHeader(HEADER_AUTH);
        if (token != null) {
            String user = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
                    .getSubject();
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) : null;
        }
        return null;
    }
}
