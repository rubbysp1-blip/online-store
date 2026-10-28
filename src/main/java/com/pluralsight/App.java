package com.pluralsight;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //displayMenu(input);

        int menuDecision = 0;

        while(menuDecision != 3) {
            displayMenu(input);
            menuDecision = input.nextInt();
            input.nextLine();

//            switch (menuDecision) {
//                case 1 -> showAvailableBooks(books, input);
//                case 2 -> checkedOutBooks(books, input);
//                case 3 -> System.out.println("Goodbye!");
//                default -> System.out.println("Invalid Input!");
//            }
        }
    }

    private static void displayMenu(Scanner input) {
        System.out.println("""
                What would you like to do:
                1. Display Products
                2. Display Cart
                3. Exit - closes out of the application:\s""");
        String menuDecision = input.nextLine();
    }

}
