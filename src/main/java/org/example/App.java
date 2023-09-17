package org.example;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;

public class App {


    public static void main(String[] args) {
        Locale swedishLocale = new Locale("sv", "SE");
        Locale.setDefault(swedishLocale);

        Scanner scanner = new Scanner(System.in);
        int [] pricesFromUser = new int[24];
        int choice;
        String userChoice;
        String menu = """
                Elpriser
                ========
                1. Inmatning
                2. Min, Max och Medel
                3. Sortera
                4. Bästa Laddningstid (4h)
                e. Avsluta
                """;
        do {
            System.out.print(menu);
            userChoice = scanner.nextLine();

            switch (userChoice) {
                case "1":
                    pricesFromUser = inputPrice(scanner);
                    break;
                case "2":
                    minMax(pricesFromUser);
                    break;
                case "3":
                    sortArray(pricesFromUser);
                    break;
                case "4":
                    bestTime(pricesFromUser);
                    break;
            }
        } while (!userChoice.equalsIgnoreCase("e"));


    }
    public static int[] inputPrice(Scanner scanner) {
        int [] prices = new int[24];
        System.out.println("Skriv in priset per kWh under dygnets 24 timmar:");
        for (int i = 0; i < prices.length; i++) {
            prices[i] = scanner.nextInt();
        }
            return prices;

    }
    public static void minMax (int [] input) {
        int sum = 0;
        int min = 0;
        int max = 0;

        for (int i = 0; i < input.length; i++){
            int val = input [i];
            sum += val;
            if (i == 0 || val < min)
                min = val;
            if (i == 0 || val > max)
                max = val;
        }
            float average = sum / input.length;
            //räkna ut min, max medel.
        System.out.printf("Lägsta pris: "+ min +" öre/kWh\n");
        System.out.printf("Högsta pris: "+ max +" öre kWh \n");
        System.out.printf("Medelpris: " + average + " öre/kWh");

    }
    public static void sortArray (int [] input) {
        Integer[] integerArray = new Integer[input.length]; // Create an array of Integer objects with the same length

        for (int i = 0; i < input.length; i++) {
            integerArray[i] = Integer.valueOf(input[i]); // Convert each int to Integer
        }
        Arrays.sort(integerArray, Collections.reverseOrder());

        for (int i = 0; i < input.length; i++) {
            String formattedI = (i < 10) ? "0" + (i) : String.valueOf(i);
            String formattedNextI = ((i + 1) < 10) ? "0" + (i + 1) : String.valueOf(i + 1);

            System.out.printf(formattedI + "-" + formattedNextI + " " + integerArray[i] + " öre\n");
        }
    }

    public static void bestTime (int [] input) {
        //find the 4 cheapets consecutive hours in a row for 24 hours, the program should tell when
        //u should start charge the car to


          // 01 -05 == 50
        //02- 06 ==


    }

}