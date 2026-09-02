package dao;

import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static config.DataBase.getConnection;


public class ProductDao {
    public static final String QUERY_ADD_NEW_PRODUCT = "INSERT INTO product (name, quantity, price) VALUES (?, ?, ?)";

    public static final String QUERY_UPDATE_QUANTITY_PRODUCT = "UPDATE product SET quantity = ? WHERE id = ?";

    public static final String QUERY_DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";

    public static final String QUERY_GET_ALL = "SELECT * FROM product";

    public static final String QUERY_GET_FROM_ID = "SELECT * FROM product WHERE id = ?";

    public static final String QUERY_CLEAR_TABLE = "TRUNCATE TABLE product RESTART IDENTITY";

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

    private static List<Product> fromRsToProduct(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while(rs.next()){
            Product product = new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
            );
            products.add(product);
        }
        return products;
    }
    public static List<Product> getAllProducts(){
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(QUERY_GET_ALL)){
            return new ArrayList<>(fromRsToProduct(rs));
        } catch (SQLException e){
            System.out.println("Error: " + e);
            return null;
        }
    }

    public static void saveProduct(String name, int quantity, double price){
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_ADD_NEW_PRODUCT)){
            stmt.setString(1, name);
            stmt.setInt(2, quantity);
            stmt.setDouble(3, price);
            stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error: " + e);
        }
    }

    public static void updateQuantity(int quantity, int id){
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE_QUANTITY_PRODUCT)){
            stmt.setInt(1, quantity);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            System.out.println("Successful update quantity product");
        } catch (SQLException e){
            System.out.println("Error: " + e);
        }
    }

    public static void deleteProduct(int id){
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE_PRODUCT)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Successful delete product");
        } catch (SQLException e){
            System.out.println("Error: " + e);
        }
    }

    public static Product findById(int id){
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(QUERY_GET_FROM_ID)){
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                List<Product> product = fromRsToProduct(rs);
                return product.isEmpty() ? null : product.getFirst();
            }
        } catch (SQLException e){
            System.out.println("Error: " + e);
        }
        return null;
    }

    public static void clearTable(){
        try(Connection conn = getConnection(); Statement stmt = conn.createStatement()){
            stmt.execute(QUERY_CLEAR_TABLE);
        } catch (SQLException e){
            System.out.println("Error: " + e);
        }
    }

}
