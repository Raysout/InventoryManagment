package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;


public class DataBase {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(getDataBaseUrl());
    }

    public static String getDataBaseUrl(){
        return Objects.requireNonNull(System.getenv("DATABASE_URL"), "Environment variable DATABASE_URL must be set");
    }
}
