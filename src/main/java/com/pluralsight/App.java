package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ArrayList<Product> inventory = getProducts();

        Scanner input = new Scanner(System.in);


        //displayMenu(input);

        int menuDecision = 0;

        while(menuDecision != 3) {
            displayMenu(input);
            menuDecision = input.nextInt();
            input.nextLine();

            switch (menuDecision) {
                case 1 -> System.out.println(); //displayProducts(books, input);
                case 2 -> System.out.println();//displayCart(books, input);
                case 3 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid Input!");
            }
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("product.csv"));
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] products = line.split("\\|");
                inventory.add(new Product(Integer.parseInt(products[0]), products[1], Double.parseDouble(products[2])));
            }
            //sortListByName(inventory);
            //printInventory(inventory);

        } catch (IOException e) {
            System.out.print("ERROR: File not found please check file name or direction.");
        }

    }

    private static ArrayList<Product> getProducts() {
        ArrayList<Product>inventory = new ArrayList<>();

        return inventory;
    }

    private static void displayMenu(Scanner input) {
        System.out.println("""
                What would you like to do:
                1. Display Products
                2. Display Cart
                3. Exit - closes out of the application:\s""");
        //String menuDecision = input.nextLine();
    }



}
