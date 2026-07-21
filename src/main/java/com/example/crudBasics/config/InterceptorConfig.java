package com.example.crudBasics.config;

import com.example.crudBasics.Interceptor.ClientSourceInterceptor;
import com.example.crudBasics.Interceptor.RequestLifecycleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestLifecycleInterceptor())
                .addPathPatterns("/api/**")
                .order(1);

        registry.addInterceptor(new ClientSourceInterceptor())
                .addPathPatterns("/api/students/**")
                .excludePathPatterns("/api/students/testcall")
                .order(2);
    }
}
