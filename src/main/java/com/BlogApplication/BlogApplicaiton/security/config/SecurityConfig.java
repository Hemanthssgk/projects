package com.BlogApplication.BlogApplicaiton.security.config;

import com.BlogApplication.BlogApplicaiton.security.filters.JWTValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
//enable to debug security logs
@EnableWebSecurity(debug = true)
@EnableMethodSecurity(prePostEnabled = true )
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityConfiguration(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.authorizeHttpRequests((authorizeHttpRequests) ->
//                authorizeHttpRequests.requestMatchers("/api/auth/register","/error").permitAll().anyRequest().authenticated()
                        authorizeHttpRequests.anyRequest().permitAll()
        );
        httpSecurity.addFilterBefore(new JWTValidationFilter(), BasicAuthenticationFilter.class);
        return httpSecurity.httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
