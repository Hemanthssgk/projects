package com.BlogApplication.BlogApplicaiton.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
public class JWTUtils {
    public static final String KEY = "HemanthsJWT$2132154123asdfasq!#!@#!#@$!@#!@#!@$!@#$%%$@#$%*()#$%";
    public static String GenerateJwtToken(Authentication authentication)
    {

        if (authentication!=null) {
//            encoding the key value
                SecretKey key = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
//            here we are asking jwts to create a jwt token and sign it. It hashes and signs the token and it base 64 encodes the entire jwt token
//            and gives the output string as base64Encoded(Header).base64Encoded(Payload).secretKey
                return Jwts.builder().setIssuer("Hemanth").setSubject("JWT_Token")
                        .claim("userName", authentication.getName()).
                        claim("authorities", authentication.getAuthorities())
                        .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 300000)).signWith(key).compact();
            }

        return "";
    }
}
