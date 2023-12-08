package ru.moscow.profile.config;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class LiquibaseConfig {

    /**
     * Настройка liquibase
     *
     * @param dataSource Подключение к базе в которую будем записывать данные о миграциях
     * @return Настроенный бин liquibase
     */
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:database/changesets/changelog.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }

}
