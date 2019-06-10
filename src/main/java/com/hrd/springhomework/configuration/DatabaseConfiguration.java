package com.hrd.springhomework.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {
    @Bean
    @Profile("local") // to identify the bean
    public DriverManagerDataSource localDB() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/articles_db");
        driverManagerDataSource.setUsername("postgres");
        driverManagerDataSource.setPassword("sokha");
        return driverManagerDataSource;
    }

    @Bean
    @Profile("memory")
    public DataSource memory() {
        EmbeddedDatabaseBuilder embeddedDatabaseBuilder = new EmbeddedDatabaseBuilder();
        embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2);
        embeddedDatabaseBuilder.addScripts("classpath:/static/sql/create_table_categories.sql","classpath:/static/sql/create_table_articles.sql","classpath:/static/sql/generate_articles.sql");
//        embeddedDatabaseBuilder.addScripts("classpath:/static/sql/create_table_categories.sql", "classpath:/static/sql/create_table_articles.sql", "classpath:/static/sql/generate_categories.sql", "classpath:/static/sql/generate_articles.sql");
        return embeddedDatabaseBuilder.build();
    }
}
