/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.businesslogic;

import nl.infosys.hartigehap.barSystem.domain.Bestelling;
import nl.infosys.hartigehap.barSystem.domain.ImmutableBestelling;
import java.util.ArrayList;
import nl.infosys.hartigehap.barSystem.domain.ImmutableMedewerker;

/**
 *
 * @author iVP4C2
 */
public interface BedieningManager {

    /**
     *
     * @return De bestellingen die de klanten willen betalen.
     */
    public ArrayList<ImmutableBestelling> getBestellingenWiltBetalen();

    /**
     *
     * @param soort
     * @return De bestellingen van een bepaalde soort die open staan
     */
    public ArrayList<ImmutableBestelling> getOpenBestellingen(Bestelling.Soort soort);

    /**
     *
     * @param status
     * @param soort
     * @return De bestellingen die voldoen aan een bepaalde status en soort
     */
    public ArrayList<ImmutableBestelling> getBestellingen(Bestelling.Status status, Bestelling.Soort soort);

    /**
     *
     * @param tafelnummer
     * @param status
     * @return De bestellingen die voldoen aan een bepaald tafelnummer en status
     */
    public ArrayList<ImmutableBestelling> getBestellingen(Integer tafelnummer, Bestelling.Status status);

    /**
     *
     * @param tafelnummer
     * @param status
     * @param soort
     * @return De bestellingen die voldoen aan het tafelnummer, status en soort
     */
    public ArrayList<ImmutableBestelling> getBestellingen(Integer tafelnummer, Bestelling.Status status, Bestelling.Soort soort);

    /**
     *
     * @param medewerkersCode
     * @param bestellingsID
     * @return boolean (gelukt of niet gelukt)
     */
    public boolean bestellingUitserveren(int medewerkersCode, int bestellingsID);

    /**
     *
     * @param tafelnummer
     * @param medewerkersCode
     * @param status
     * @return
     */
    public boolean updateBestellingStatus(int tafelnummer, int medewerkersCode, Bestelling.Status status);

    /**
     *
     * @return medewerkersNamen
     */
    public Integer[] getMedewerkersCodes();
    
    /**
     *
     * @param bestelling
     */
    public void printBon(ImmutableBestelling bestelling);    
    
    
    public ArrayList<ImmutableMedewerker> getAllMedewerkers();
    
    
    public boolean updateMedewerkerStatus(int Code, String status);
    
}
