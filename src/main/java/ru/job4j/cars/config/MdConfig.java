package ru.job4j.cars.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.job4j.cars.filter.Md5PasswordEncrypter;

@Configuration
public class MdConfig {

    @Bean
    public Md5PasswordEncrypter md5PasswordEncrypter() {
        return new Md5PasswordEncrypter();
    }

}
