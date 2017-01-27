/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.domain;

import java.util.ArrayList;
import java.util.Locale;

/**
 *
 * @author IVP3C2
 */
public class Bestelling implements ImmutableBestelling {

    public enum Status {

        BESTELD, GEREED, GELEVERD, BETAALD, WILTBETALEN
    };

    public enum Soort {

        KEUKEN, BAR, VOLLEDIG
    };

    private int tafelNummer, id;
    private double totaalPrijs;
    private ArrayList<Product> producten;
    private Status status;
    private Soort soort;

    /**
     * Maak een nieuwe bestelling aan
     *
     * @param tafelNummer
     * @param soort
     * @param status
     */
    public Bestelling(int id, int tafelNummer, Soort soort, Status status) {
        this.tafelNummer = tafelNummer;
        this.totaalPrijs = 0;
        this.producten = new ArrayList<Product>();
        this.soort = soort;
        this.id = id;
        this.status = status;
    }

    /**
     * Haal de totaalPrijs op van de bestelling
     *
     * @return De totaalprijs van de bestelling
     */
    @Override
    public double getTotaalPrijs() {
        return totaalPrijs;
    }
    
    @Override
    public double getBtwBedrag()
    {
        return totaalPrijs - (totaalPrijs / 1.21);
    }

    /**
     * Haal het tafelNummer op van de bestelling
     *
     * @return Het tafelnummer van de bestelling
     */
    @Override
    public int getTafelNummer() {
        return tafelNummer;
    }

    /**
     * Haal de status op van de bestelling
     *
     * @return De status van de bestelling (In Verwerking, gereed, geleverd,
     * betaald)
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Voeg een product toe aan de bestelling
     *
     * @param product
     */
    public void addProduct(Product product) {
        producten.add(product);

        totaalPrijs += product.getTotaalPrijs();
    }

    /**
     *
     * @return De producten uit de bestelling
     */
    @Override
    public ArrayList<Product> getProducten() {
        return producten;
    }

    /**
     *
     * @return Het aantal producten in de bestelling
     */
    @Override
    public int getAantalProducten() {
        return producten.size();
    }

    /**
     *
     * @return De soort bestelling (keuken of bar)
     */
    @Override
    public Soort getSoort() {
        return soort;
    }

    /**
     *
     * @return Het id van de bestelling
     */
    @Override
    public int getId() {
        return id;
    }
    
    private String formatGeld(double prijs)
    {
        return String.valueOf(java.text.NumberFormat.getCurrencyInstance(Locale.ITALY).format(prijs));
    }

    @Override
    public String getTotaalPrijsFormat() {
        return formatGeld(totaalPrijs);
    }
    
    @Override
    public String getBtwBedragFormat()
    {
        return formatGeld(getBtwBedrag());
    }    
    
    @Override
    public String getPrijsExclBtwFormat()
    {
        return formatGeld(totaalPrijs - getBtwBedrag());
    }
}
