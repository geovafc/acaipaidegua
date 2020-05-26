package br.com.acaipaideguaweb;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
public class AcaipaideguawebApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcaipaideguawebApplication.class, args);

	}

	@Bean
	public FixedLocaleResolver localeResolver() {

		return new FixedLocaleResolver(new Locale("pt", "BR"));

	}

//	@Configuration
//	public static class MvcConfig extends WebMvcConfigurerAdapter {
//
//		@Override
//		public void addViewControllers(ViewControllerRegistry registry) {
//			registry.addRedirectViewController("/", "/estabelecimentos");
//		}
//
//	}
	
	

}
