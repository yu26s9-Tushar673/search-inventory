package com.pluralsight;

import java.util.ArrayList;

public class StoreApp {
    public static void main(String[] args) {
        ArrayList<Product> inventory = getInventory();

        // Display Inventory
        System.out.println("We carry the following inventory: ");
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.printf("id: %d %s - Price: $%.2f%n",
                    p.getId(), p.getName(), p.getPrice());
        }
    }
    public static ArrayList<Product> getInventory() {
        ArrayList<Product> inventory = new ArrayList<Product>();
        inventory.add(new Product(1234, "Star Driver", 2.99F));
        inventory.add(new Product(1235, "Flathead", 3.99F));
        inventory.add(new Product(1236, "Pliers", 10.99F));
        inventory.add(new Product(1237, "Impact Gun", 399.99F));
        inventory.add(new Product(1238, "Screw Gun", 149.99F));
        return inventory;
    }
}
