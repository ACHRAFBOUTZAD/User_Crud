package Crudpack;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("Crudpack")
@EnableJpaRepositories("Crudpack")

public class UsersCrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsersCrudApplication.class, args);
    }
}

