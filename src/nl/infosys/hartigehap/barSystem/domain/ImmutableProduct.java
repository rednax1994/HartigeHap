/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.domain;

/**
 *
 * @author iVP4C2
 */
public interface ImmutableProduct {

    /**
     * Haal de naam van het product op
     *
     * @return De naam van het product
     */
    public String getNaam();

    /**
     * Haal de prijs op van het product
     *
     * @return De prijs van het product
     */
    public double getPrijs();

    /**
     * Haal het aantal van het product op
     *
     * @return Het aantal van dit product
     */
    public int getAantal();

    /**
     * Haal de totaalprijs op van het product.
     *
     * @return De totaalprijs van dit product
     */
    public double getTotaalPrijs();

    /**
     * Haal de totaalprijs op van het product in een euro formaat
     *
     * @return De totaalprijs van dit product
     */
    public String getTotaalPrijsFormat();

    /**
     * Haal de prijs op van het product in een euro formaat
     *
     * @return De prijs van dit product
     */
    public String getPrijsFormat();
}
