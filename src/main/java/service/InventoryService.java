package service;

import model.Product;

import java.util.List;

public interface InventoryService {
    void createProduct(String name, int quantity, double price);
    List<Product> getAllProducts();
    Product getProductById(int id);
    List<Product> searchByName(String query);
    List<Product> getSortedProducts(String field, boolean asc);
    void replenishStock(int id, int amount);
    void writeOffStock(int id, int amount);
    void changePrice(int id, double newPrice);
    void deleteProduct(int id);
    void clearAllInventory();
    List<Product> getLowStockAlerts(int threshold);
    double calculateWarehouseValue();
    void updateProduct(int id, String name, int quantity, double price);
}
