/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import hospital.Connexion;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class Search {
    private Connexion con;
    
    public Search(Connexion con)
    {
        this.con=con;
    }
    
     public void SearchWithoutWhere(String select, String from, String where) throws SQLException, ClassNotFoundException
    {
        System.out.println(con.remplirChampsRequete("SELECT "+select+" FROM "+from));
    }
    
    public void SearchWithWhere(String select, String from, String where) throws SQLException, ClassNotFoundException
    {
        System.out.println(con.remplirChampsRequete("SELECT "+select+" FROM "+from+" WHERE "+where));
    }
    
    //Faudra un champ texte pour celui là
    public String SearchMutual()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Selon quelle mutuelle voulez vous trier les patients?");
        String MutualName = sc.nextLine();
        return "mutuelle like '"+MutualName+"'";
    }
    
    //des boutons suffisent pour celui-là
    public String nursingRotation()
    {
        Scanner sc = new Scanner(System.in);
        int rotation;
        do
        {
            System.out.println("Infirmiers travaillant le jour (0) ou la nuit (1)");
            rotation = sc.nextInt();
        }while((rotation != 1)&&(rotation != 0));
        
        if(rotation == 0)
            return "rotation like 'NUIT'";
        else
            return "rotation like 'JOUR'";
        
        
    }
}
