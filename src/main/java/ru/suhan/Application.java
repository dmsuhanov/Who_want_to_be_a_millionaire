package ru.suhan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.sql.SQLException;

@SpringBootApplication
@EnableCaching
public class Application {
    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Application.class);
    }
}
