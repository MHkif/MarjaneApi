package yc.mhkif.marjaneapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;

@SpringBootApplication
public class MarjaneApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarjaneApiApplication.class, args);
    }

}
