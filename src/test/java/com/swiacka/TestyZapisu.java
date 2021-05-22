package com.swiacka;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.AfterClass;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestyZapisu {
    private final Funkcje funkcje = new Funkcje();
    private static final String testMiasto = RandomStringUtils.randomAlphabetic(15);
    private static final File plik = new File(testMiasto+".txt");

    @AfterClass
    public static void clean(){
        if (plik.exists()){
            plik.delete();
        }
    }

    @Test
    public void plikIstnieje_gdyZapisany(){
        HashMap<String, String> testMapa = new HashMap<>();
        testMapa.put("test", "test");
        funkcje.zapisz(testMapa, testMiasto);

        assertTrue(plik.exists());
    }

    @Test
    public void plikNieIstnieje_przedZapisem(){
        File nieistniejacyPlik = new File(plik+".txt");
        assertFalse(nieistniejacyPlik.exists());
    }
}
