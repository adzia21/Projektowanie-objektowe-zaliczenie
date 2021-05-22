package com.swiacka;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestyWeryfikacji {

    private final Funkcje funkcje = new Funkcje();

    @Test
    public void prawidlowyPesel_kiedyPrawidlowyPesel(){
        assertTrue(funkcje.weryfikacjaPesel("46012431715"));
    }

    @Test
    public void prawidlowyPesel_kiedyPrawidlowyPeselIRokPrzestepny(){
        assertTrue(funkcje.weryfikacjaPesel("96022803217"));
    }
    @Test
    public void nieprawidlowyPesel_KiedyZaDlugi (){
        assertFalse(funkcje.weryfikacjaPesel("460124317152"));
    }

    @Test
    public void nieprawidlowyPesel_KiedyDzienZaDuzy(){
        assertFalse(funkcje.weryfikacjaPesel("46014431715"));
    }
    @Test
    public void nieprawidlowyPesel_KiedyNieprawidlowaSuma(){
        assertFalse(funkcje.weryfikacjaPesel("46012431745"));
    }

}
