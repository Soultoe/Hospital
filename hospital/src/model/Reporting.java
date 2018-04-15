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
 * @author User
 */
//je suis entrain de réflechir à peut être en faire une classe qui hériterait de Search
public class Reporting {
    private Connexion con;
    private String select, from, where;
    
    public Reporting(Connexion con)
    {
        this.con=con;
    }
    
    public void SearchWithWhere() throws SQLException, ClassNotFoundException
    {
        System.out.println(con.remplirChampsRequete("SELECT "+select+" FROM "+from+" WHERE "+where));
    }
    
    public void Moyennes() throws SQLException, SQLException, ClassNotFoundException
    {
        select ="";
        from= "";
        where= "";
        this.SearchWithWhere();
    }
}
