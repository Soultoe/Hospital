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
    
    public String buildRequest(boolean dOrN, String[] fields)
    {
        if(dOrN) //means doctor
            System.out.println("UPDATE docteur");
        else
            System.out.println("UPDATE docteur");
        
        return "Successful update!";
        
    }
    
}
