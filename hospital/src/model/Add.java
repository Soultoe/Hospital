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
    
    public Add(Connexion con)
    {
        super(con);
    }
    
    public void getRequete(String requete) throws SQLException
    {
       con.executeUpdate(requete);
    }
    
}
