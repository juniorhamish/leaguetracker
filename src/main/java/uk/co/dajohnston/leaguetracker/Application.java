package uk.co.dajohnston.leaguetracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
@SuppressWarnings("PMD.UseUtilityClass")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
