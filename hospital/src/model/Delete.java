/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import hospital.Connexion;
import java.sql.SQLException;

/**
 *
 * @author romain
 */
public class Delete extends Action{
    
    public Delete(Connexion con)
    {
        super(con);
    }
    
    public void delete(String requete) throws SQLException {
        con.executeUpdate(requete);
    }
    
    public void deleteEmployee(String id, boolean doctor) throws SQLException
    {
        String request,heal;
        
        try {
            if (doctor) {
                request = "DELETE FROM docteur WHERE numero = " + id + ";";
                heal = "DELETE FROM soigne WHERE no_docteur = " + id + ";";
                this.delete(heal);
            } else {
                request = "DELETE FROM infirmier WHERE numero = " + id + ";";
            }

            this.delete(request);

            request = "DELETE FROM employe WHERE numero = " + id + ";";

            this.delete(request);
        } catch (SQLException e) {
            System.out.println("deletion error.");
        }
    }
    
    public void deletePatient(String id) throws SQLException
    {
        String patient,heal,hospitalize;
        
        patient = "DELETE FROM malade WHERE numero = " + id + ";";
        heal = "DELETE FROM soigne WHERE no_malade = " + id + ";";
        hospitalize = "DELETE FROM hospitalisation WHERE no_malade = " + id + ";";
        
        try 
        {
            this.delete(patient);
            this.delete(heal);
            this.delete(hospitalize);
        } catch (SQLException e) 
        {
            System.out.println("deletion error.");
        }
    }
    
}
