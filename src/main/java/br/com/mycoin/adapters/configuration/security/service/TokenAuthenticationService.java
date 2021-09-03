package br.com.mycoin.adapters.configuration.security.service;

import br.com.mycoin.adapters.configuration.security.model.Token;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {

    // EXPIRATION_TIME = 5 horas
    static final long EXPIRATION_TIME = 5 * 60 * 60 * 1000;
    static final String SECRET = "MyC0!n-mICr0S3RVICe@-2o2i";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void addAuthentication(HttpServletResponse response, String username) {
        try {
            Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
            String JWT = Jwts.builder()
                    .setSubject(username)
                    .setExpiration(expiration)
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact();
            String token = TOKEN_PREFIX + " " + JWT;
            response.addHeader(HEADER_STRING, token);
            Token t = new Token(TOKEN_PREFIX, JWT, dateFormat.format(expiration));
            response.getOutputStream().print(new Gson().toJson(t));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        try {
            String token = request.getHeader(HEADER_STRING);
            if (token != null) {
                // faz parse do token
                String user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

                if (user != null) {
                    return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                }
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String authenticateToken(String token) {
        try {

            if (token != null) {
                // faz parse do token
                String user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

                if (user != null) {
                    return user;
                }
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
