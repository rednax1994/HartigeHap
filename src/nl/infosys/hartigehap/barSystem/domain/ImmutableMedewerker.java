/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.domain;

/**
 *
 * @author Olivier
 */
public interface ImmutableMedewerker {

    /**
     * Haalt de voornaam op van de desbetreffende medewerker
     *
     * @return de medewerkers voornaam
     */
    public String getVoornaam();

    /**
     *
     * Haalt de achternaam op van de desbetreffende medewerker
     *
     * @return de medewerkers achternaam
     */
    public String getAchternaam();

    /**
     * Haalt de medewerkerscode op
     *
     * @return de medewerkers code
     */
    public int getCode();
    
    /**
     * Haalt de status van de medewerker op
     *
     * @return de medewerkers status
     */
    public String getStatus();

}
