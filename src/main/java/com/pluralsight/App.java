package com.pluralsight;

import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.nio.file.LinkPermission;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class App {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Product> inventory = getProducts();
        ArrayList<Product> cart = getProducts();


        //addProductToCart(input, inventory, cart);

        //System.out.println(cart);
        //displayMenu(input);

        mainMenuDisplay(input, inventory, cart);
//


    }

    private static void addProductToCart(Scanner input, ArrayList<Product> inventory, ArrayList<Product> cart) {
        System.out.print("Enter the SKU of product: ");
        String skuNumber = input.nextLine().toUpperCase().trim();

        for (Product product : inventory){
            if(product.getId().equals(skuNumber)){
                String[] item = product.toCartDisplay().split(",");
                cart.add(new Product(item[0], item[1], Double.parseDouble(item[2]), item[3]));
                //cart.add(new Product(product.getId(), product.getName(), product.getPrice(), product.getCategory()));
            }

        }
        printCartItems(cart, input);
    }

    private static void mainMenuDisplay(Scanner input, ArrayList<Product> inventory, ArrayList<Product> cart) {
        int mainMenuDecision = 0;
        displayMenu(input);
        mainMenuDecision = input.nextInt();
        input.nextLine();

        switch (mainMenuDecision) {
            case 1 -> displayProducts(inventory, cart, input); //displayProducts(books, input);
            case 2 -> printCartItems(cart, input);//displayCart(books, input);
            case 3 -> {
                System.out.println("Goodbye!");
                input.close();
            }

            default -> System.out.println("Invalid Input!");
        }
    }


    private static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner input) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("products.csv"));
            String line;
            bufferedReader.readLine();
            while((line = bufferedReader.readLine()) != null){
                String[] products = line.split("\\|");
                inventory.add(new Product(products[0], products[1], Double.parseDouble(products[2]), products[3]));
            }
            //sortListByName(inventory);
            printInventory(inventory);
            displayAdjustMenu(input);
            int menuDecision = input.nextInt();
            input.nextLine();

            switch (menuDecision){
                case 1-> searchBy(inventory, input);//searchByRange(inventory, input);//search list
                case 2-> addProductToCart(input, inventory, cart);//add product to cart
                case 3-> mainMenuDisplay(input, inventory, cart);//displayMenu
                default -> System.out.print("Number out of range!");
            }

        } catch (IOException e) {
            System.out.println("ERROR: File not found please check file name or direction.");
        }
    }
    private static void printCartItems(ArrayList<Product> cart, Scanner input){
        System.out.println("""
                
                Your cart items:\s
                ---------------------------------""");
        for (Product items : cart) {
            System.out.println(items.toPriceFormat());
        }
    }

    private static void printInventory(ArrayList<Product> inventory) {
        System.out.println("""
                
                We carry the following inventory:\s
                ---------------------------------""");
        for (Product product : inventory) {
            System.out.println(product.toPriceFormat());
        }
    }

    private static ArrayList<Product> getProducts() {
        ArrayList<Product>inventory = new ArrayList<>();
        return inventory;
    }

    private static void displayMenu(Scanner input) {
        System.out.print("""
                --------------------------------------------
                What would you like to do:
                1. Display Products
                2. Display Cart
                3. Exit - closes out of the application:\s""");
        //String menuDecision = input.nextLine();
    }

    private static void displayAdjustMenu(Scanner input) {
        System.out.print("""
                
                |Choose an option|
                1. Search the list of products
                2. Add a product to cart
                3. Go Back to the home screen:\s""");
    }

    private static void searchBy(ArrayList<Product> inventory, Scanner input){
        System.out.print("""
                
                Would You Like To Search By
                1. Product Name
                2. Price Range
                3. Department:\s""");
        int menuDecision = input.nextInt();
        input.nextLine();

        switch (menuDecision){
            case 1-> searchByProductName(inventory, input);
            case 2-> searchByRange(inventory, input);
            case 3-> searchByDepartment(inventory, input);
            default -> System.out.print("Number out of range!");
        }
    }

    private static void searchByProductName(ArrayList<Product> inventory, Scanner input){
        System.out.println("--------------------------");
        System.out.print("Enter the product name: ");
        String productName = input.nextLine().trim().toLowerCase();

        for(Product product : inventory){
            if(product != null) {
                if (product.getName().toLowerCase().contains(productName)) {
                    System.out.println(product.toPriceFormat());
                }
            }
        }
    }

    private static void searchByDepartment(ArrayList<Product> inventory, Scanner input){
        System.out.println("--------------------------");
        System.out.print("Enter the department name: ");
        String department = input.nextLine().trim();

        for(Product product : inventory){
            if(product != null) {
                if (product.getCategory().equalsIgnoreCase(department)) {

                    System.out.println(product.toPriceFormat());
                }
            }
        }
    }

    private static void searchByRange(ArrayList<Product> inventory, Scanner input){
        System.out.print("Enter the minimum price range: ");
        double minimumRange = input.nextDouble();
        input.nextLine();

        System.out.print("Enter the maximum price range: ");
        double maximumRange = input.nextDouble();

        for(Product product : inventory){
            if(product != null) {
                if (product.getPrice() >= minimumRange && product.getPrice() <= maximumRange) {
                    System.out.println("--------------------------");
                    System.out.println(product.toPriceFormat());
                }
            }
        }

    }
}
