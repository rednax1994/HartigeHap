/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.domain;

import java.util.Locale;

/**
 *
 * @author IVP3C2
 */
public class Product implements ImmutableProduct {

    private double prijs, totaalPrijs;
    private String naam;
    private int aantal;

    /**
     * Maak een nieuw product aan met prijs, naam en aantal
     *
     * @param prijs
     * @param naam
     * @param aantal
     */
    public Product(String naam, double prijs, int aantal) {
        this.prijs = prijs;
        this.naam = naam;
        this.aantal = aantal;
        this.totaalPrijs = prijs * aantal;
    }

    /**
     * Haal de naam van het product op
     *
     * @return De naam van het product
     */
    @Override
    public String getNaam() {
        return naam;
    }

    /**
     * Haal de prijs op van het product
     *
     * @return De prijs van het product
     */
    @Override
    public double getPrijs() {
        return prijs;
    }

    /**
     * Haal het aantal van het product op
     *
     * @return Het aantal van dit product
     */
    @Override
    public int getAantal() {
        return aantal;
    }

    /**
     * Haal de totaalprijs op van het product.
     *
     * @return De totaalprijs van dit product
     */
    @Override
    public double getTotaalPrijs() {
        return totaalPrijs;
    }

    @Override
    public String getTotaalPrijsFormat() {
        return String.valueOf(java.text.NumberFormat.getCurrencyInstance(Locale.ITALY).format(getTotaalPrijs()));
    }

    @Override
    public String getPrijsFormat() {
        return String.valueOf(java.text.NumberFormat.getCurrencyInstance(Locale.ITALY).format(getPrijs()));
    }
}
