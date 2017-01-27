/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.loginSystem.businesslogic;

import nl.infosys.hartigehap.loginSystem.databaseacces.LoginDao;

/**
 *
 * @author Sander van Belleghem
 */
public class LoginManager implements ImmutableLoginManager {

    private final LoginDao loginDao;
    private String username;

    public LoginManager() {
        loginDao = new LoginDao();
    }

    @Override
    public boolean login(String username, String password) {
        if (loginDao.login(username, password)) {
            this.username = username;

            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        loginDao.logout(username);
    }

    @Override
    public String getFunctie() {
        return loginDao.getFunctie();
    }
}
