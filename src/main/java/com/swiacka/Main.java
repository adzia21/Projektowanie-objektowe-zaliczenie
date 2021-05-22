package com.swiacka;


import java.util.*;

public class Main {
    public static void main(String[] args) {
        Funkcje funkcje = new Funkcje();
        HashMap<String, String> mieszkancy = new HashMap<>();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj miasto: ");
        String miasto = scanner.next();

        int opcja = 1;
        while (opcja == 1){
            System.out.println("Podaj poprawny numer PESEL: ");
            String pesel = scanner.next();
            if (!funkcje.weryfikacjaPesel(pesel))
            {
                System.out.println("Niepoprawny numer PESEL.");
                continue;
            }
            System.out.println("Podaj imię: ");
            String imie = scanner.next();
            System.out.println("Podaj nazwisko: ");
            String nazwisko = scanner.next();

            mieszkancy.put(pesel, imie + " " + nazwisko);

            System.out.println("Co chcesz zrobić? ");
            System.out.println("1. Dododaj kolejnego mieszkanca");
            System.out.println("2. Zapisz i wyjdz");
            System.out.println("3. Wyjdz bez zapisu");
            if (scanner.hasNextInt())
                opcja = scanner.nextInt();
            else
                System.out.println("Nieprawidłowa opcja");

            if (opcja > 3 || opcja < 0){
                System.out.println("Brak takiej opcji. Podaj jeszcze raz: ");
                if (scanner.hasNextInt())
                    opcja = scanner.nextInt();
                else
                    System.out.println("Nieprawidłowa opcja");
            }
        }
        if (opcja == 2){
            funkcje.zapisz(mieszkancy, miasto);
        }
    }
}
