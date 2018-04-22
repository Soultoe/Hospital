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
    
    /**
     * 
     * @param con 
     */
    public Delete(Connexion con)
    {
        super(con);
    }
    
    /**
     * 
     * @param requete
     * @throws SQLException 
     */
    public void delete(String requete) throws SQLException {
        con.executeUpdate(requete);
    }
    
    /**
     * 
     * @param id
     * @param doctor
     * @throws SQLException 
     */
    public void deleteEmployee(String id, boolean doctor) throws SQLException
    {
        String request,heal;
        
        try {
            if (doctor) {
               //create the requests
                request = "DELETE FROM docteur WHERE numero = " + id + ";";
                heal = "DELETE FROM soigne WHERE no_docteur = " + id + ";";
                //send it
                this.delete(heal);
            } else {
                //create the requests
                request = "DELETE FROM infirmier WHERE numero = " + id + ";";
            }

            //send it
            this.delete(request);

            request = "DELETE FROM employe WHERE numero = " + id + ";";

            this.delete(request);
        } catch (SQLException e) {
            System.out.println("deletion error.");
        }
    }
    
    /**
     * 
     * @param id
     * @throws SQLException 
     */
    public void deletePatient(String id) throws SQLException
    {
        String patient,heal,hospitalize;
        
        //create the requests
        patient = "DELETE FROM malade WHERE numero = " + id + ";";
        heal = "DELETE FROM soigne WHERE no_malade = " + id + ";";
        hospitalize = "DELETE FROM hospitalisation WHERE no_malade = " + id + ";";
        
        try 
        {
            //send the request
            this.delete(patient);
            this.delete(heal);
            this.delete(hospitalize);
        } catch (SQLException e) 
        {
            System.out.println("deletion error.");
        }
    }
    
}
