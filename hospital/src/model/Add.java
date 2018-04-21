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
public class Add extends Action{
    
    /**
     * @name Overload constructor
     * @param con 
     */
    public Add(Connexion con)
    {
        super(con);
    }
    
    /**
     * @name getRequete
     * @param requete
     * @throws SQLException 
     */
    public void getRequete(String requete) throws SQLException
    {
       con.executeUpdate(requete);
    }
    
    public String buildRequest(boolean dOrN, String[] fields) throws SQLException
    {
        String emp = "INSERT INTO employe(numero,nom,prenom,adresse,tel) VALUES(" + fields[0] + ", '" + fields[1] + "', '" + fields[2] + "', '" + fields[3] + "', '" + fields[4] + "');";
        String type;
        
        this.getRequete(emp);
        
        if(dOrN){ //means doctor
            type = "INSERT INTO docteur(numero,specialite) VALUES(" + fields[0] + ", '" + fields[5] + "');";
            System.out.println(type);
            this.getRequete(type);
        }
        else {
            type = "INSERT INTO infirmier(numero,code_service,rotation,salaire) VALUES(" + fields[0] + ", '" + fields[5] + "', '" + fields[6] + "', '" + fields[7] + "');";
            System.out.println(type);
            this.getRequete(type);
        }
        
        return emp + " | " + type;
        
    }
    
}
