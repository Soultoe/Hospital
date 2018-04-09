/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hospital;
import model.*;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author romain
 */
public class Hospital {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
 
        //connexion en distant
        String usernameECE = "ad151913";
        String passwordECE = "mdp";
        String loginDatabaseECE = "ad151913-rw";
        String passwordDatabaseECE = "tNi6Vlia";
        
        //connexion en local
        String nameDatabase = "Hopital";
        String loginDatabase = "root";
        String passwordDatabase = "";   
                  
          
        //pour mon test
        Scanner sc = new Scanner(System.in);
        System.out.println("Que voulez vous chercher? Patients selon leur mutuelle (0)), rotation des infirmiers (1)");
        int choice = sc.nextInt();
        try
        {
            //distant
            //Connexion con1 = new Connexion(usernameECE, passwordECE, loginDatabaseECE, passwordDatabaseECE);
            
            //local
            Connexion con1 = new Connexion(nameDatabase, loginDatabase, passwordDatabase);
            Search SearchMutual = new Search(con1);
            
            //c'est juste un teste hein je nettoierai ça après
            if(choice == 0)
            {
                SearchMutual.SearchWithWhere("nom,prenom","malade",SearchMutual.SearchMutual());
            }
            if(choice == 1)
            {
                //SearchMutual.nursingRotation();
                
                SearchMutual.SearchWithWhere("nom, prenom", "employe e, (SELECT numero from infirmier where rotation like "+SearchMutual.nursingRotation()+") i", "i.numero = e.numero ");
            }
            

        }
        catch(Exception e)
        {
            System.out.println("CONNEXION ERROR!");
        }
    }
    
}
