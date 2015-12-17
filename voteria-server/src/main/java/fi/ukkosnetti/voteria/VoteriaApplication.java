package fi.ukkosnetti.voteria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EntityScan
public class VoteriaApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(VoteriaApplication.class, args);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("static/**").addResourceLocations("classpath").setCachePeriod(0);		
	}

}
