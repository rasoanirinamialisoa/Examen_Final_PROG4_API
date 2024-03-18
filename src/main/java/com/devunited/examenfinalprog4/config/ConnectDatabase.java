package com.devunited.examenfinalprog4.config;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



@Getter
@Setter
@NoArgsConstructor
@Configuration
public class ConnectDatabase {
    private static ConnectDatabase instance;
    public static String username = System.getenv("DB_USERNAME");
    public static String password = System.getenv("DB_PASSWORD");
    public static String url = System.getenv("DB_URL");
    public static ConnectDatabase getInstance() {
        if (instance == null) {
            instance = new ConnectDatabase();
        }
        return instance;
    }
    public Connection getConnection(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connexion à la base de données !");
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}