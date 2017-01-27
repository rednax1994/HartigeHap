/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.datestore;

import nl.infosys.hartigehap.barSystem.databaseacces.ConnectionDB;
import nl.infosys.hartigehap.barSystem.domain.ImmutableMedewerker;
import nl.infosys.hartigehap.barSystem.domain.Medewerker;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Olivier
 */
public class MedewerkerDAO {

    /**
     *
     * @return return Medewerker
     */
    public ArrayList<ImmutableMedewerker> getMedewerkers() {

        try {
            ConnectionDB connect = new ConnectionDB();

            String queryTekst = "SELECT Voornaam, Achternaam, MedewerkerID FROM medewerker WHERE Status = 'ingelogd' AND functie = 'Bediening'";

            ResultSet rs = connect.executeQuery(queryTekst);

            ArrayList<ImmutableMedewerker> medewerkers = new ArrayList<ImmutableMedewerker>();

            while (rs.next()) {
                Medewerker medewerker = new Medewerker(rs.getString("Voornaam"), rs.getString("Achternaam"), rs.getInt("MedewerkerID"), "ingelogd");
                medewerkers.add(medewerker);
            }
            connect.close();

            return medewerkers;

        } catch (SQLException e) {
            System.err.println("SQL Foutmelding: " + e.getMessage());
        }

        return null;
    }

    /**
     *
     * @return return Medewerker
     */
    public ArrayList<ImmutableMedewerker> getAllMedewerkers() {

        try {
            ConnectionDB connect = new ConnectionDB();

            String queryTekst = "SELECT Voornaam, Achternaam, MedewerkerID, Status FROM medewerker ORDER BY Achternaam";

            ResultSet rs = connect.executeQuery(queryTekst);

            ArrayList<ImmutableMedewerker> medewerkers = new ArrayList<ImmutableMedewerker>();

            while (rs.next()) {
                Medewerker medewerker = new Medewerker(rs.getString("Voornaam"), rs.getString("Achternaam"), rs.getInt("MedewerkerID"), rs.getString("Status"));
                medewerkers.add(medewerker);
            }
            connect.close();

            return medewerkers;

        } catch (SQLException e) {
            System.err.println("SQL Foutmelding: " + e.getMessage());
        }

        return null;
    }

    public boolean updateMedewerkerStatus(int code, String status) {
        
        try {
            // Maak een connectie met de database
            ConnectionDB connect = new ConnectionDB();

            if (code < 1 && status.isEmpty()) {
                return false;
            } else {
                return connect.executeUpdate("UPDATE medewerker SET `Status` = '" + status + "' WHERE `MedewerkerID` = " + code + "");
            }

        } catch (SQLException e) {
            System.err.println("Melding: " + e.getMessage());
        }
        return false;
    }

    public boolean checkMedewerkersCode(int medewerkersCode) {
        try {
            // Maak een connectie met de database
            ConnectionDB connect = new ConnectionDB();

            ResultSet rs = connect.executeQuery("SELECT MedewerkerID FROM medewerker WHERE MedewerkerID = " + medewerkersCode + " AND Functie = 'Bediening' AND Status = 'ingelogd'");

            return rs.isBeforeFirst();

        } catch (SQLException e) {
            System.err.println("SQL Foutmelding: " + e.getMessage());
        }

        return false;
    }
}
