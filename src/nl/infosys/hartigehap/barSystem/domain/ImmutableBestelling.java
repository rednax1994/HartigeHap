/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.domain;

import java.util.ArrayList;

/**
 *
 * @author iVP4C2
 */
public interface ImmutableBestelling {

    /**
     * Haal de totaalPrijs op van de bestelling
     *
     * @return De totaalprijs van de bestelling
     */
    public double getTotaalPrijs();
    
    public double getBtwBedrag();

    /**
     * Haal het tafelNummer op van de bestelling
     *
     * @return Het tafelnummer van de bestelling
     */
    public int getTafelNummer();

    /**
     * Haal de status op van de bestelling
     *
     * @return De status van de bestelling (In Verwerking, gereed, geleverd,
     * betaald)
     */
    public Bestelling.Status getStatus();

    /**
     *
     * @return De soort bestelling (keuken of bar)
     */
    public Bestelling.Soort getSoort();

    /**
     *
     * @return De producten uit de bestelling
     */
    public ArrayList<Product> getProducten();

    /**
     *
     * @return Het aantal producten in de bestelling
     */
    public int getAantalProducten();

    /**
     *
     * @return Het id van de bestelling
     */
    public int getId();

    /**
     * Haal de totaalprijs op van het bestelling in een euro formaat
     *
     * @return De totaalprijs van dit product
     */
    public String getTotaalPrijsFormat();
    
    public String getBtwBedragFormat();
    
    public String getPrijsExclBtwFormat();
}
