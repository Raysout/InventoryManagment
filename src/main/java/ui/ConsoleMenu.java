package ui;

import model.Product;
import service.InventoryService;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final InventoryService service;
    private final Scanner sc = new Scanner(System.in);

    public ConsoleMenu(InventoryService inventoryService) {
        this.service = inventoryService;
    }

    public void init() {
        while (true) {
            System.out.println("""
                  Storage Menu
                  1. View and search Products
                  2. Catalog Management
                  3. Storage Operation
                  4. Analytics
                  5. Exit
                  """);
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> viewInventory();
                case "2" -> catalogManagement();
                case "3" -> storageOperation();
                case "4" -> analytics();
                case "5" -> {
                    return;
                }
                default -> System.out.println("Invalid input!");
            }
        }
    }

    public void viewInventory() {
        boolean back = false;
        while (!back) {
            System.out.println("""
                    1. Get all Products
                    2. Search product with ID
                    3. Search product with Name
                    4. Get sorted Products
                    5. Main menu
                    """);
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    List<Product> products = service.getAllProducts();
                    System.out.println(products);
                }
                case "2" -> {
                    System.out.println("Enter product ID: ");
                    int id = Integer.parseInt(sc.nextLine().trim());
                    System.out.println(service.getProductById(id));
                }
                case "3" -> {
                    System.out.println("Enter product name: ");
                    String name = sc.nextLine().trim();
                    System.out.println(service.searchByName(name));
                }
                case "4" -> {
                    System.out.println("Enter field (name, price, quantity): ");
                    String field = sc.nextLine().trim();
                    System.out.println("Ascending? (true/false): ");
                    boolean asc = Boolean.parseBoolean(sc.nextLine().trim());
                    System.out.println(service.getSortedProducts(field, asc));
                }
                case "5" -> back = true;
                default -> System.out.println("Invalid input!");
            }
        }
    }

    public void catalogManagement() {
        boolean back = false;
        while (!back) {
            System.out.println("""
                    1. Add Product
                    2. Update Product
                    3. Delete Product
                    4. Change Price Product
                    5. Clear All Products
                    6. Main menu
                    """);
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    System.out.println("Enter product name: ");
                    String name = sc.nextLine().trim();
                    System.out.println("Enter quantity: ");
                    int quantity = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Enter price: ");
                    double price = Double.parseDouble(sc.nextLine().trim());
                    service.createProduct(name, quantity, price);
                    System.out.println("Product has been added successfully!");
                }
                case "2" -> {
                    System.out.println("Enter product ID: ");
                    int id = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Enter new name: ");
                    String newName = sc.nextLine().trim();
                    System.out.println("Enter new quantity: ");
                    int newQuantity = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Enter new price: ");
                    double newPrice = Double.parseDouble(sc.nextLine().trim());
                    service.updateProduct(id, newName, newQuantity, newPrice);
                    System.out.println("Product updated successfully!");
                }
                case "3" -> {
                    System.out.println("Enter product ID: ");
                    int deleteId = Integer.parseInt(sc.nextLine().trim());
                    service.deleteProduct(deleteId);
                    System.out.println("Product deleted successfully!");
                }
                case "4" -> {
                    System.out.println("Enter product ID: ");
                    int priceId = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Enter new price: ");
                    double priceVal = Double.parseDouble(sc.nextLine().trim());
                    service.changePrice(priceId, priceVal);
                    System.out.println("Price updated successfully!");
                }
                case "5" -> {
                    System.out.println("You sure to delete all Products? Write 'sure'");
                    if (sc.nextLine().trim().equalsIgnoreCase("sure")) {
                        service.clearAllInventory();
                        System.out.println("All inventory cleared!");
                    }
                }
                case "6" -> back = true;
                default -> System.out.println("Invalid input!");
            }
        }
    }

    public void storageOperation() {
        boolean back = false;
        while (!back) {
            System.out.println("""
                    1. Replenish Products
                    2. Write off stock
                    3. Main menu
                    """);
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    System.out.println("Enter product ID: ");
                    int id = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Enter amount: ");
                    int amount = Integer.parseInt(sc.nextLine().trim());
                    service.replenishStock(id, amount);
                    System.out.println("Stock replenished successfully!");
                }
                case "2" -> {
                    System.out.println("Enter product ID: ");
                    int writeOffId = Integer.parseInt(sc.nextLine().trim());
                    System.out.println("Enter amount: ");
                    int writeOffAmount = Integer.parseInt(sc.nextLine().trim());
                    service.writeOffStock(writeOffId, writeOffAmount);
                    System.out.println("Stock written off successfully!");
                }
                case "3" -> back = true;
                default -> System.out.println("Invalid input!");
            }
        }
    }

    public void analytics() {
        boolean back = false;
        while (!back) {
            System.out.println("""
                    1. Get low stock Products
                    2. Calculate price warehouse
                    3. Get out stock
                    4. Main menu
                    """);
            String choice = sc.nextLine().trim();
            switch (choice) {
                case "1" -> {
                    System.out.println("Enter threshold quantity: ");
                    int threshold = Integer.parseInt(sc.nextLine().trim());
                    System.out.println(service.getLowStockAlerts(threshold));
                }
                case "2" -> System.out.println("Price WareHouse: " + service.calculateWarehouseValue());
                case "3" -> service.getLowStockAlerts(0);
                case "4" -> back = true;
                default -> System.out.println("Invalid input!");
            }
        }
    }
}