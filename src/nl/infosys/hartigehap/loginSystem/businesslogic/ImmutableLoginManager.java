/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.loginSystem.businesslogic;

/**
 *
 * @author Sander van Belleghem
 */
public interface ImmutableLoginManager {

    public boolean login(String username, String password);

    public void logout();

    public String getFunctie();
}
