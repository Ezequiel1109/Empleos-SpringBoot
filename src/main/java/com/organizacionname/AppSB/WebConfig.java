package com.organizacionname.AppSB;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${AppSB.ruta.imagenes}")
    private String rutaImagenes;

    @Value("${AppSB.ruta.cv}")
    private String rutaCv;

    public void addResourceHandlers(ResourceHandlerRegistry registry){
       // registry.addResourceHandler("/logos/**").addResourceLocations("file:c:/empleos/img-vacantes/");
       registry.addResourceHandler("/logos/**").addResourceLocations("file:" + rutaImagenes);
       registry.addResourceHandler("/cv/**").addResourceLocations("file:" + rutaCv);
    }
}
