package com.BlogApplication.BlogApplicaiton.security.filters;

import com.BlogApplication.BlogApplicaiton.rest.exceptions.GlobalExceptionHandler;
import com.BlogApplication.BlogApplicaiton.rest.exceptions.model.ApiResponse;
import com.BlogApplication.BlogApplicaiton.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * This filter will run for all the authenticated api calls except /login. as we are going to create jwt using that uri
 * now for other api's we are getting the values from header, validating the jwt amd in case of issues we are return a json
 * fron here only. becuase controller advice will only handle exceptions thrown in controllers. As we are setting the authentication
 * object which will set the is authenticated to true. so spring wont call basicauthenticationfilter.
 */
public class JWTValidationFilter extends OncePerRequestFilter {
    @Autowired
    GlobalExceptionHandler globalExceptionHandler;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtToken = request.getHeader("Authorization");

        if (jwtToken!=null)
        {
            try {
            JWTUtils.validateJwtToken(jwtToken,globalExceptionHandler);

            } catch (BadCredentialsException e) {
                ApiResponse apiResponse =new ApiResponse(new BadCredentialsException("Invalid Token received!").getMessage(),new Date(),false);
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType("application/json");

                ObjectMapper objectMapper = new ObjectMapper();
                PrintWriter printWriter = response.getWriter();
                printWriter.print(objectMapper.writeValueAsString(apiResponse));
                printWriter.flush();
                return;
        }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            ApiResponse apiResponse =new ApiResponse(new BadCredentialsException("Invalid Token received!").getMessage(),new Date(),false);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");

            ObjectMapper objectMapper = new ObjectMapper();
            PrintWriter printWriter = response.getWriter();
            printWriter.print(objectMapper.writeValueAsString(apiResponse));
            printWriter.flush();
            return;
        }

        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().contains("/api/auth/");
    }
}
