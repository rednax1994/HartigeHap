/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.businesslogic;

import nl.infosys.hartigehap.barSystem.domain.Bestelling;
import nl.infosys.hartigehap.barSystem.domain.Bon;
import nl.infosys.hartigehap.barSystem.domain.ImmutableBestelling;
import nl.infosys.hartigehap.barSystem.domain.ImmutableMedewerker;
import nl.infosys.hartigehap.barSystem.datestore.BestellingDAO;
import nl.infosys.hartigehap.barSystem.datestore.MedewerkerDAO;
import java.util.ArrayList;

/**
 *
 * @author iVP4C2
 */
public class BedieningManagerImpl implements BedieningManager {

    /**
     *
     * @param soort
     * @return De bestellingen van een bepaalde soort die open staan
     */
    @Override
    public ArrayList<ImmutableBestelling> getOpenBestellingen(Bestelling.Soort soort) {

        if (soort.equals(Bestelling.Soort.BAR)) {
            // Voor de drankjes is de status besteld, omdat deze niet op gereed worden gezet
            return getBestellingen(Bestelling.Status.BESTELD, soort);
        }

        return getBestellingen(Bestelling.Status.GEREED, soort);
    }

    /**
     *
     * @param status
     * @param soort
     * @return De bestellingen die voldoen aan een bepaalde status en soort
     */
    @Override
    public ArrayList<ImmutableBestelling> getBestellingen(Bestelling.Status status, Bestelling.Soort soort) {
        return getBestellingen(null, status, soort);
    }

    /**
     *
     * @param tafelnummer
     * @param status
     * @return De bestellingen die voldoen aan een bepaald tafelnummer en status
     */
    @Override
    public ArrayList<ImmutableBestelling> getBestellingen(Integer tafelnummer, Bestelling.Status status) {
        return getBestellingen(tafelnummer, status, Bestelling.Soort.VOLLEDIG);
    }

    /**
     *
     * @param tafelnummer
     * @param status
     * @param soort
     * @return De bestellingen die voldoen aan het tafelnummer, status en soort
     */
    @Override
    public ArrayList<ImmutableBestelling> getBestellingen(Integer tafelnummer, Bestelling.Status status, Bestelling.Soort soort) {
        BestellingDAO bestellingDAO = new BestellingDAO();

        return bestellingDAO.getBestellingen(tafelnummer, status, soort);
    }

    /**
     *
     * @param medewerkersCode
     * @param bestellingsID
     */
    @Override
    public boolean bestellingUitserveren(int medewerkersCode, int bestellingsID) {
        MedewerkerDAO medewerkerDAO = new MedewerkerDAO();

        if (medewerkerDAO.checkMedewerkersCode(medewerkersCode)) {
            BestellingDAO bestellingDAO = new BestellingDAO();

            return bestellingDAO.updateBestelregelStatus(bestellingsID, medewerkersCode, Bestelling.Status.GELEVERD);
        }

        return false;
    }

    /**
     *
     * @return De bestellingen die de klanten willen betalen.
     */
    @Override
    public ArrayList<ImmutableBestelling> getBestellingenWiltBetalen() {
        return getBestellingen(Bestelling.Status.WILTBETALEN, Bestelling.Soort.VOLLEDIG);
    }

    /**
     *
     * @param tafelnummer
     * @param medewerkersCode
     * @param status
     * @return information
     */
    @Override
    public boolean updateBestellingStatus(int tafelnummer, int medewerkersCode, Bestelling.Status status) {
        MedewerkerDAO medewerkerDAO = new MedewerkerDAO();

        if (medewerkerDAO.checkMedewerkersCode(medewerkersCode)) {
            BestellingDAO bestellingDAO = new BestellingDAO();

            return bestellingDAO.updateBestellingStatus(tafelnummer, medewerkersCode, status);
        }

        return false;
    }

    public ArrayList<ImmutableMedewerker> getMedewerkers() {
        MedewerkerDAO medewerkerDAO = new MedewerkerDAO();

        return medewerkerDAO.getMedewerkers();
    }

    @Override
    public ArrayList<ImmutableMedewerker> getAllMedewerkers() {
        MedewerkerDAO medewerkerDAO = new MedewerkerDAO();

        return medewerkerDAO.getAllMedewerkers();
    }
    
    @Override
    public boolean updateMedewerkerStatus(int code, String status){
        MedewerkerDAO medewerkerDAO = new MedewerkerDAO();
        
        return medewerkerDAO.updateMedewerkerStatus(code, status);
    }

    /**
     *
     * @return
     */
    @Override
    public Integer[] getMedewerkersCodes() {

        ArrayList<ImmutableMedewerker> medewerkers = getMedewerkers();

        if (medewerkers.size() > 0) {
            Integer[] codes = new Integer[medewerkers.size()];

            int i = 0;

            for (ImmutableMedewerker medewerker : medewerkers) {
                codes[i] = medewerker.getCode();

                i++;
            }

            return codes;
        }

        return null;
    }

    /**
     *
     * @param bestelling
     */
    @Override
    public void printBon(ImmutableBestelling bestelling) {
        new Bon(bestelling);
    }
}
