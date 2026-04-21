package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StoreApp {

    static void main() throws IOException
    {
        ArrayList<Product> inventory = getInventory();
        Collections.sort(inventory, Comparator.comparing(Product::getName));

        // Display Inventory
        System.out.println("We carry the following inventory: ");
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.printf("id: %d %s - Price: $%.2f%n",
                    p.getId(), p.getName(), p.getPrice());
        }
    }

    public static ArrayList<Product> getInventory() throws IOException {
        ArrayList<Product> inventory = new ArrayList<Product>();

        BufferedReader bufReader = new BufferedReader(new FileReader("inventory.csv"));
        String input;
        while((input = bufReader.readLine()) != null)
        {
            String[] info = input.split("\\|");
            Product item = new Product(Integer.parseInt(info[0]), info[1], Float.parseFloat(info[2]));
            inventory.add(item);
        }

        /* Manual ArrayList initialization
        inventory.add(new Product(1234, "Star Driver", 2.99F));
        inventory.add(new Product(1235, "Flathead", 3.99F));
        inventory.add(new Product(1236, "Pliers", 10.99F));
        inventory.add(new Product(1237, "Impact Gun", 399.99F));
        inventory.add(new Product(1238, "Screw Gun", 149.99F));
        */
        return inventory;
    }
}
