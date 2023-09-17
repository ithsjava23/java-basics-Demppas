package org.example;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.*;

public class App {
private static Locale swedishLocale = new Locale("sv", "SE");

    public static void main(String[] args) {
        java.util.Locale.setDefault(swedishLocale);

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
                    bestTimeToCharge(pricesFromUser);
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
        float sum = 0;
        int min = 0;
        int max = 0;
        String min2 ="";
        String max2 ="";

        for (int i = 0; i < input.length; i++){         //calculate avg, min and max value
            int val = input [i];
            sum += val;
            if (i == 0 || val < min) {
                min = val;
                min2 = timeAndPrice(i, val);
            }
            if (i == 0 || val > max) {
                max = val;
                max2 = timeAndPrice(i, val);
            }
        }
            float average = sum / input.length;
            //räkna ut min, max medel.
        System.out.printf("Lägsta pris: "+ min2 +" öre/kWh\n");
        System.out.printf("Högsta pris: "+ max2 +" öre/kWh\n");
        System.out.printf("Medelpris: " + String.format(swedishLocale,"%.2f", average) + " öre/kWh\n");
    }

    public static String timeAndPrice(int position, int price){

        String formattedI = (position < 10) ? "0" + (position) : String.valueOf(position);
        String formattedNextI = ((position + 1) < 10) ? "0" + (position + 1) : String.valueOf(position + 1);

        return formattedI + "-" + formattedNextI + ", " + price;
    }

    public static void sortArray (int [] input) {
        TimeAndPrice[] timePrices = new TimeAndPrice [input.length];// Create an array of Integer objects with the same length

        for (int i = 0; i < input.length; i++) {
            timePrices[i] = new TimeAndPrice (i, input[i]); // Convert each int to Integer
        }
        Arrays.sort(timePrices, Comparator.comparingInt(TimeAndPrice::getPrice).reversed());

        for (int i = 0; i < timePrices.length; i++) {
            TimeAndPrice t = timePrices [i];
            int pos = t.getTime();
            String formattedI = (pos < 10) ? "0" + (pos) : String.valueOf(pos);
            String formattedNextI = ((pos + 1) < 10) ? "0" + (pos + 1) : String.valueOf(pos + 1);

            System.out.printf(formattedI + "-" + formattedNextI + " " + t.getPrice() + " öre\n");
        }
    }

    public static void bestTimeToCharge (int [] input) {
        float val1 = 0;
        float val2 = 0;
        int time = 0;
        for (int i = 0; i < input.length -4; i++){
            val1 = input [i] + input [i+1] + input [i+2] + input [i+3];
            if (val2 < val1);
                val2 = val1;
                time = i;
        }
        System.out.printf("Påbörja laddning klockan " + time +"\n" +
                "Medelpris 4h: " +String.format(swedishLocale,"%.1f", val2 /4) + " öre/kWh\n");
        //find the 4 cheapets consecutive hours in a row for 24 hours, the program should tell when
        //u should start charge the car to
    }
static class TimeAndPrice{
        private int time;
        private int price;

        public TimeAndPrice(int time, int price){
            this.time = time;
            this.price = price;
        }
        public int getTime(){
            return time;
        }
        public int getPrice(){
            return price;
        }
}
}
