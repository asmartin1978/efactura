package es.application.ms_springmvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	super.addResourceHandlers(registry);
        registry
          .addResourceHandler("/webjars/**")
          .addResourceLocations("/webjars/")
          ;
    }
}
