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
    
    public Update(Connexion con)
    {
        super(con);
    }
    
    public void getUpdate(String requete) throws SQLException
    {
        con.executeUpdate(requete);
    }
    
}
