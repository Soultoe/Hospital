/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import hospital.Connexion;
import java.sql.SQLException;
import java.util.ArrayList;
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
    public String nursingRotation() throws SQLException, ClassNotFoundException
    {
        Scanner sc = new Scanner(System.in);
        int rotation;
        
        //String rot;
        do
        {
            System.out.println("Infirmiers travaillant le jour (0) ou la nuit (1)");
            rotation = sc.nextInt();
        }while((rotation != 1)&&(rotation != 0));
        
        if(rotation == 0)
           return "'NUIT'"; 
        else
           return "'JOUR'";
        
        //this.SearchWithWhere("numero","infirmier","rotation like "+rot);
        
       //SELECT nom, prenom from employe e, (SELECT numero from infirmier where rotation like 'JOUR') i where i.numero = e.numero 
       //this.SearchWithWhere("nom, prenom", "employe e, (SELECT numero from infirmier where rotation like "+rot+") i", "i.numero = e.numero ");  
       //System.out.println(con.remplirChampsRequete("SELECT nom, prenom from employe e, (SELECT numero from infirmier where rotation like 'JOUR') i where i.numero = e.numero"));
    }
    
    public void menu(int choice) throws SQLException, ClassNotFoundException
    {
        //pour nos recherches
            String select, from, where;
            
            //c'est juste un teste hein je nettoierai ça après
            if(choice == 0)
            {
                select = "nom,prenom";
                from = "malade";
                where = this.SearchMutual();
            }
            else
            {
                //SearchMutual.nursingRotation();
                select = "nom,prenom";
                from =  "employe e, (SELECT numero from infirmier where rotation like "+this.nursingRotation()+") i";
                where  = "i.numero = e.numero ";
            }
            
            this.SearchWithWhere(select, from, where);
    }
}
