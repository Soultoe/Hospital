/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import hospital.*;
import java.sql.SQLException;

/**
 *
 * @author romain
 */
public class Update extends Action {
    
    /**
     * @name Overload constructor
     * @param con 
     */
    public Update(Connexion con)
    {
        super(con);
    }
    
    /**
     * @name getUpdate
     * @param requete
     * @throws SQLException 
     */
    public void getUpdate(String requete) throws SQLException
    {
        con.executeUpdate(requete);
    }
    
    public String buildRequestEmployee(boolean dOrN, String[] fields) throws SQLException
    {
        String emp = "UPDATE employe SET nom = '" + fields[1] + "', prenom = '" + fields[2] + "', adresse = '" + fields[3] + "', tel = '" + fields[4] + "' WHERE numero = " + fields[0] + ";";
        System.out.println(emp);
        String type;
        
        this.getUpdate(emp);
        
        if(dOrN){ //means doctor
            type = "UPDATE docteur SET specialite = '" + fields[5] + "' WHERE numero = " + fields[0] + ";";
            System.out.println(type);
            this.getUpdate(type);
        }
        else {
            type = "UPDATE infirmier SET code_service = '" + fields[5] + "', rotation = '" + fields[6] + "', salaire = '" + fields[7] + "' WHERE numero = " + fields[0] + ";";
            System.out.println(type);
            this.getUpdate(type);
        }
        
        return emp + " | " + type;
        
    }
    
}
