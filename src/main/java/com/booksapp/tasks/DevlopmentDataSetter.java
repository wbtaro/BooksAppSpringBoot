package com.booksapp.tasks;

import java.sql.Connection;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

@Component
public class DevlopmentDataSetter implements CommandLineRunner{
    @Value("${spring.profiles.active}")
    private String environment;
    
    @Value("${spring.datasource.data:null}")
    private String testDataSQLScript;

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    ResourceLoader resourceLoader;
    
    Logger logger = org.slf4j.LoggerFactory.getLogger("com.booksapp.tasks.DevelopmentDataSetter");
    
    public void run(String... args) {
        if(environment.equals("development")) {
            Connection conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
            Resource resource = resourceLoader.getResource(testDataSQLScript);
            ScriptUtils.executeSqlScript(conn, resource);
            logger.info("SQL Script '" + testDataSQLScript + "' was executed");
        }
    }
}
