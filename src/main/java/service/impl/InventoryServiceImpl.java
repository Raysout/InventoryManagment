package service.impl;

import dao.ProductDao;
import model.Product;

import utils.FieldValidator;
import service.InventoryService;

import java.util.List;

public class InventoryServiceImpl implements InventoryService {

    @Override
    public void createProduct(String name, int quantity, double price) {
        FieldValidator.validatePositive(price);
        FieldValidator.isNull(name);
        if (quantity < 0){
            throw new IllegalArgumentException();
        }

        ProductDao.saveProduct(name, quantity, price);
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = ProductDao.getAllProducts();
        return products != null ? products : List.of();
    }

    @Override
    public Product getProductById(int id) {
        FieldValidator.validatePositive(id);
        return ProductDao.findById(id);
    }

    @Override
    public List<Product> searchByName(String query) {
        FieldValidator.isNull(query);
        return ProductDao.findByName(query);
    }

    @Override
    public List<Product> getSortedProducts(String field, boolean asc) {
        FieldValidator.isNull(field);
        return ProductDao.findAllSorted(field, asc);
    }

    public void updateProduct (int id, String name, int quantity, double price) {
        FieldValidator.validatePositive(id);
        FieldValidator.validatePositive(price);
        FieldValidator.isNull(name);
        ProductDao.updateProduct(id, name, quantity, price);
    }

    @Override
    public void replenishStock(int id, int amount) {
        FieldValidator.validatePositive(id);
        FieldValidator.validatePositive(amount);

        Product product = getProductById(id);
        FieldValidator.isNull(product);

        ProductDao.updateQuantity(product.getId(), product.getQuantity() + amount);
    }

    @Override
    public void writeOffStock(int id, int amount) {
        FieldValidator.validatePositive(id);
        FieldValidator.validatePositive(amount);

        Product product = getProductById(id);

        FieldValidator.isNull(product);

        if (amount > product.getQuantity()){
            throw new RuntimeException("Insufficient stock. Available: "
                    + product.getQuantity() + ", requested: " + amount);
        }

        ProductDao.updateQuantity(id, product.getQuantity() - amount);
    }

    @Override
    public void changePrice(int id, double newPrice) {
        FieldValidator.validatePositive(id);
        FieldValidator.validatePositive(newPrice);
        ProductDao.updatePrice(id, newPrice);
    }

    @Override
    public void deleteProduct(int id) {
        FieldValidator.validatePositive(id);
        ProductDao.deleteProduct(id);
    }

    @Override
    public void clearAllInventory() {
        ProductDao.clearTable();
    }

    @Override
    public List<Product> getLowStockAlerts(int threshold) {
        return ProductDao.findLowStock(threshold);
    }

    @Override
    public double calculateWarehouseValue() {
        List<Product> products = ProductDao.getAllProducts();
        if (products == null || products.isEmpty()) {
            return 0.0;
        }
        return products.stream().mapToDouble(product -> product.getQuantity() * product.getPrice()).sum();
    }
}
