package com.BlogApplication.BlogApplicaiton.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//enable to debug security logs
//@EnableWebSecurity(debug = true)
public class AppConfig {
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    @Bean
    public SecurityFilterChain securityConfig(HttpSecurity httpSecurity) throws Exception {
         httpSecurity.csrf(AbstractHttpConfigurer::disable);
         httpSecurity.cors(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests.requestMatchers("/api/auth/register").permitAll().anyRequest().authenticated()
        );
         return httpSecurity.httpBasic(Customizer.withDefaults()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
