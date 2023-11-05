package com.schoolWebsite.SchoolWebsite.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/about").setViewName("about.html");
        registry.addViewController("/classes").setViewName("classes.html");
        registry.addViewController("/facility").setViewName("facility.html");
        registry.addViewController("/teams").setViewName("team.html");
        registry.addViewController("/callToAction").setViewName("call-to-action.html");
        registry.addViewController("/appointment").setViewName("appointment.html");
        registry.addViewController("/testimonial").setViewName("testimonial.html");
        registry.addViewController("/404").setViewName("404.html");
    }
}
