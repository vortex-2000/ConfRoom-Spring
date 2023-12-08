package com.confRoom;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;





@SpringBootApplication
public class ConfRoomSpringApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ConfRoomSpringApplication.class, args);
	}

}


