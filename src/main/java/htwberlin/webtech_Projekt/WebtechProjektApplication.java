package htwberlin.webtech_Projekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
@EntityScan("htwberlin.webtech_Projekt.web.Entities")
public class WebtechProjektApplication {

	public static void main(String[] args) {

		SpringApplication.run(WebtechProjektApplication.class, args);
	}

}
