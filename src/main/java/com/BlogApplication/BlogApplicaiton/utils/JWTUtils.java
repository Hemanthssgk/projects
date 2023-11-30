package com.BlogApplication.BlogApplicaiton.utils;

import com.BlogApplication.BlogApplicaiton.rest.exceptions.GlobalExceptionHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class JWTUtils {
    public static final String KEY = "HemanthsJWT$2132154123asdfasq!#!@#!#@$!@#!@#!@$!@#$%%$@#$%*()#$%";
    public static String generateJwtToken(Authentication authentication)
    {

        if (authentication!=null) {
//            encoding the key value
                SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
//            here we are asking jwts to create a jwt token and sign it. It hashes and signs the token and it base 64 encodes the entire jwt token
//            and gives the output string as base64Encoded(Header).base64Encoded(Payload).secretKey
                return Jwts.builder().setIssuer("Hemanth").setSubject("JWT_Token")
                        .claim("userName", authentication.getName()).
                        claim("authorities", authentication.getAuthorities())
                        .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 300000000)).signWith(key).compact();
            }

        return "";
    }

    public static void validateJwtToken(String jwtToken, GlobalExceptionHandler globalExceptionHandler)
    {
//                creating the key from what we have in the app
            SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
//            this will take the header, payload and key and it creates a new jwt token and signs it, now this
//            jwt token is compared with the jwt token passed if it matches then it gives the body values because
//            we are invoking getBody method
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken).getBody();

            String userName = claims.get("userName").toString();
//        Object authorities = claims.get("authorities");
        HashMap<String,String> authorities = (HashMap<String, String>) ((List<Object>)claims.get("authorities")).get(0);

        List<SimpleGrantedAuthority> grantedAuthorities =  authorities.keySet().stream().map(k -> new SimpleGrantedAuthority(authorities.get(k))).toList();
        // creating the authentication object and setting it inside the security context.
            Authentication auth = new UsernamePasswordAuthenticationToken(userName, null,
                    grantedAuthorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
    }


}
