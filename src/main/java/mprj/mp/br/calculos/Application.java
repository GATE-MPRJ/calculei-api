package mprj.mp.br.calculos;


import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@EnableJpaRepositories

public class Application {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/calc");
		// System.setProperty("server.server.servlet-path", "/api");
		SpringApplication.run(Application.class, args);
	}


}
