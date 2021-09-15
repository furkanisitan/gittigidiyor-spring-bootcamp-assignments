package dev.patika.schoolmanagementsystem.config;

import dev.patika.schoolmanagementsystem.core.interceptors.ClientRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ClientRequestInterceptorConfig implements WebMvcConfigurer {

    private final ClientRequestInterceptor clientRequestInterceptor;

    @Autowired
    public ClientRequestInterceptorConfig(ClientRequestInterceptor clientRequestInterceptor) {
        this.clientRequestInterceptor = clientRequestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(clientRequestInterceptor).addPathPatterns("/**");
    }
}
