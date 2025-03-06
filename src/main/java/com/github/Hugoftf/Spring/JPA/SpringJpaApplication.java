package com.github.Hugoftf.Spring.JPA;

import com.github.Hugoftf.Spring.JPA.model.Autor;
import com.github.Hugoftf.Spring.JPA.repository.AutorRepository;
import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
@EnableJpaAuditing
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);

	}

}
