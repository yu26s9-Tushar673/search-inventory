package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StoreApp {

    static void main() throws IOException
    {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> inventory = getInventory();
        Collections.sort(inventory, Comparator.comparing(Product::getName));

        do {
            System.out.print("What do you want to do?\n" +
                    "1- List all products\n" +
                    "2- Lookup a product by its id\n" +
                    "3- Find all products within a price range\n" +
                    "4- Add a new product\n" +
                    "5- Quit the application\n" +
                    "Enter command #:");
            int command = scanner.nextInt();
            scanner.nextLine(); // Buffer handling

            switch (command)
            {
                case 1:
                    displayProducts(inventory);
                    break;
                case 2:
                    lookUpByID(inventory, scanner);
                    break;
                case 3:
                    showProductInRange(inventory, scanner);
                    break;
                case 4:
                     inventory = addProduct(inventory, scanner);
                     break;
                case 5:
                    System.out.println("Thank you, Goodbye!");
                    return;
            }
        } while (true);

    }

    // Display Product Inventory
    public static void displayProducts(ArrayList<Product> inventory)
    {

        System.out.println("We carry the following inventory: ");
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.printf("id: %d %s - Price: $%.2f%n",
                    p.getId(), p.getName(), p.getPrice());
        }
    }

    public static void lookUpByID(ArrayList<Product> inventory, Scanner scanner)
    {
        System.out.print("Enter Product ID: ");
        int input = scanner.nextInt();
        scanner.nextLine(); // Buffer handling

        boolean found = false;
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            if (input == p.getId())
            {
                System.out.println("Product found: ");
                System.out.printf("id: %d %s - Price: $%.2f%n",
                        p.getId(), p.getName(), p.getPrice());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No Product found with that ID");
        }
    }

    public static void showProductInRange(ArrayList<Product> inventory, Scanner scanner)
    {
        System.out.print("Enter your minimum price: ");
        float min = scanner.nextFloat();
        System.out.print("Enter your maximum price: ");
        float max = scanner.nextFloat();

        System.out.println("Products in Price Range: ");
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            if (p.getPrice() >= min && p.getPrice() <= max)
            {
                System.out.printf("id: %d %s - Price: $%.2f%n",
                        p.getId(), p.getName(), p.getPrice());
            }
        }


    }

    public static ArrayList<Product> addProduct(ArrayList<Product> inventory, Scanner scanner)
    {
        System.out.print("Enter new product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Buffer Handling
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product price: ");
        float price = scanner.nextFloat();
        scanner.nextLine(); // Buffer Handling

        inventory.add(new Product(id,name,price));

        Collections.sort(inventory, Comparator.comparing(Product::getName));

        return inventory;
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
