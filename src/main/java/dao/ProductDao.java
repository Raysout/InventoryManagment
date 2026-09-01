package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static config.DataBase.getConnection;


public class ProductDao {
    public static final String QUERY_CREATE_TABLE = """
                CREATE TABLE IF NOT EXISTS PRODUCT (
                id SERIAL PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                quantity INT NOT NULL,
                price DOUBLE PRECISION NOT NULL
            );
            """;

    public static void init(){
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            stmt.execute(QUERY_CREATE_TABLE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
