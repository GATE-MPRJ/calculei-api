package mprj.mp.br.calculos;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


//@SpringBootApplication
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

@EnableAutoConfiguration
@Configuration
@EnableJpaRepositories

public class Application  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		//System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
		SpringApplication.run(Application.class, args);
	}
	/*
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	 */


}
