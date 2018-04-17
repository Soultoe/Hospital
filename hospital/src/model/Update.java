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
    
}
