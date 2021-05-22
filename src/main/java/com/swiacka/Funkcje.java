package com.swiacka;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Funkcje {

    public boolean weryfikacjaPesel(String pesel){
        byte[] peselByte = new byte[11];

        if (pesel.length() != 11)
            return false;
        else {
            for (int i = 0; i < 11 ; i++) {
                peselByte[i] = Byte.parseByte(pesel.substring(i, i+1));
            }
            return sprawdzSume(peselByte) && sprawdzDzien(peselByte);
        }
    }

    private boolean sprawdzDzien(byte[] peselByte) {
        int dzien = pobierzDzien(peselByte);
        int miesiac = pobierzMiesiac(peselByte);
        int rok = pobierzRok(peselByte);

        if (miesiac < 1 || miesiac > 12){
            return false;
        }
        if ((dzien >0 && dzien < 32) &&
                (miesiac == 1 || miesiac == 3 || miesiac == 5 ||
                        miesiac == 7 || miesiac == 8 || miesiac == 10 ||
                        miesiac == 12)) {
            return true;
        }
        else if ((dzien >0 && dzien < 31) &&
                (miesiac == 4 || miesiac == 6 || miesiac == 9 ||
                        miesiac == 11)) {
            return true;
        }
        else return (dzien > 0 && dzien < 30 && czyRokPrzestepny(rok)) ||
                    (dzien > 0 && dzien < 29 && !czyRokPrzestepny(rok));
    }

    private boolean czyRokPrzestepny(int rok) {
        return rok % 4 == 0 && rok % 100 != 0 || rok % 400 == 0;
    }

    private int pobierzDzien(byte[] peselByte) {
        int dzien;
        dzien = 10 * peselByte[4];
        dzien += peselByte[5];
        return dzien;
    }

    private int pobierzMiesiac(byte[] peselByte) {
        int miesiac;
        miesiac = 10 * peselByte[2];
        miesiac += peselByte[3];
        if (miesiac > 80 && miesiac < 93) {
            miesiac -= 80;
        }
        else if (miesiac > 20 && miesiac < 33) {
            miesiac -= 20;
        }
        else if (miesiac > 40 && miesiac < 53) {
            miesiac -= 40;
        }
        else if (miesiac > 60 && miesiac < 73) {
            miesiac -= 60;
        }
        return miesiac;
    }

    private int pobierzRok(byte[] peselByte) {
        int miesiac;
        int rok;
        rok = 10 * peselByte[0];
        rok += peselByte[1];
        miesiac = 10 * peselByte[2];
        miesiac += peselByte[3];
        if (miesiac > 80 && miesiac < 93) {
            rok += 1800;
        }
        else if (miesiac > 0 && miesiac < 13) {
            rok += 1900;
        }
        else if (miesiac > 20 && miesiac < 33) {
            rok += 2000;
        }
        else if (miesiac > 40 && miesiac < 53) {
            rok += 2100;
        }
        else if (miesiac > 60 && miesiac < 73) {
            rok += 2200;
        }
        return rok;
    }

    private boolean sprawdzSume(byte[] peselByte) {
        int sum =
                peselByte[0] +
                        3 * peselByte[1] +
                        7 * peselByte[2] +
                        9 * peselByte[3] +
                        peselByte[4] +
                        3 * peselByte[5] +
                        7 * peselByte[6] +
                        9 * peselByte[7] +
                        peselByte[8] +
                        3 * peselByte[9];

        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        return sum == peselByte[10];
    }

    public void zapisz(HashMap<String, String> mieszkancy, String miasto) {
        try {
            File plik = new File(miasto + ".txt");
            FileWriter fileWriter = new FileWriter(miasto + ".txt");
            if (plik.createNewFile()){
                System.out.println("Utworzony plik.");
            }
            fileWriter.append("Miasto: ").append(miasto).append("\n");
            for (Map.Entry<String, String> entry: mieszkancy.entrySet()
            ) {
                fileWriter.append("PESEL: ").append(entry.getKey()).append("\n")
                        .append("Imię i nazwisko: ").append(entry.getValue()).append("\n")
                        .append("#--------#\n");
            }
            fileWriter.close();
            System.out.println("Plik zapisany w miejscu: " + plik.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Problem z zapisem pliku");
    }
}
