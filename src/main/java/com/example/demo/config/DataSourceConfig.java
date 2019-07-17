package com.example.demo.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${db.url}")
    private String url;

    @Value("${db.username}")
    private String username;

    @Value("${db.driver-class-name}")
    private String driver;

    @Value("${db.schema}")
    private String schema;

    @Bean
    public DataSource datasource() {
        HikariConfig config = new HikariConfig();

        config.setDriverClassName(driver);
        config.setUsername(username);
        config.setJdbcUrl(url);
        config.setSchema(schema);
        return new HikariDataSource(config);
    }
}
