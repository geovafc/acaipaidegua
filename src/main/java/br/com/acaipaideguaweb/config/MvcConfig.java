package br.com.acaipaideguaweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.monitorjbl.json.JsonViewSupportFactoryBean;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {  
		registry.addViewController("/newlogin").setViewName("newlogin");
		registry.addViewController("/403").setViewName("403");
	}
	
	@Bean
	  public JsonViewSupportFactoryBean views() {
	    return new JsonViewSupportFactoryBean();
	  }
	
	
}
