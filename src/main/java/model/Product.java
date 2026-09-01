package model;

public class Product {
    public Integer id;
    private String name;
    private int quantity;
    private double price;

    public Product(Integer id, String name, int quantity, double price){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString(){
        return "Id: " + getId() + ", Name: " + getName() + ", Quantity: " + getQuantity() + ", Price: " + getPrice();
    }
}
