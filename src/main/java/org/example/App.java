package org.example;

import java.util.Locale;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Locale swedishLocale = new Locale("sv", "SE");
        Locale.setDefault(swedishLocale);

        menuBar();
    }

    public static String menuBar() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String userChoice;

        do {
            System.out.print
                    ("Elpriser\n"
                            + "========\n"
                            + "1. Inmatning\n"
                            + "2. Min, Max och Medel\n"
                            + "3. Sortera\n"
                            + "4. Bästa Laddningstid (4h)\n"
                            + "e. Avsluta\n");

            String userChoice = scanner.nextLine();
            choice = Integer.parseInt(userChoice);

        }while (!userChoice.equalsIgnoreCase ("e"));

        }
    public static void performAction (String userChoice) {
         switch (menuBar()) {
            case "1":
                inputPrice();
                break;
            case "2":
                System.out.println("nummer 2");
                break;
        }

    }
        public static void inputPrice () {
            System.out.println("Skriv in pris/h i ören för de 24 senaste timmarna:");
        }

}