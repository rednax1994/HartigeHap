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
public class Medewerker implements ImmutableMedewerker {

    private String voornaam, achternaam, status;
    private int code;

    public Medewerker(String voornaam, String achternaam, int code, String status) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.code = code;
        this.status = status;
    }

    @Override
    public String getVoornaam() {
        return voornaam;
    }

    @Override
    public String getAchternaam() {
        return achternaam;
    }

    @Override
    public int getCode() {
        return code;
    }
    
    @Override
    public String getStatus(){
        
        return status;
    }
}
