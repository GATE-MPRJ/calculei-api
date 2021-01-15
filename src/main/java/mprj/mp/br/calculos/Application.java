package mprj.mp.br.calculos;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

//mprj.mp.br.calculos.api.rest.persistence
/*
@Configuration
@SpringBootApplication(scanBasePackages = {"mprj.mp.br.calculos"})
@ComponentScan(basePackages={"mprj.mp.br.calculos"})
@EntityScan(basePackages = "mprj.mp.br.calculos.domain.jpa")
@EnableJpaRepositories(basePackages = {"mprj.mp.br.calculos.api.rest.persistence.mybatis"})
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableWebMvc

public class Application  {

	public static void main(String[] args) {
		//System.setProperty("server.servlet.context-path", "/calc");
		SpringApplication.run(Application.class, args);
	}


}
