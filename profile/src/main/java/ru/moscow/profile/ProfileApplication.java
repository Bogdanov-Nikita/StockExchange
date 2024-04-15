package ru.moscow.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProfileApplication {

    /**
     * Сервис по работе с учётными записями клиентов
     *
     * @param args -
     */
    public static void main(String[] args) {
        SpringApplication.run(ProfileApplication.class, args);
    }

}
