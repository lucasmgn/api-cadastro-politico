package br.com.sprint4.config;

import br.com.sprint4.services.convert.StringEnumConvertIdeology;
import br.com.sprint4.services.convert.StringEnumConvertOffice;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringEnumConvertIdeology());
        registry.addConverter(new StringEnumConvertOffice());
    }
}
