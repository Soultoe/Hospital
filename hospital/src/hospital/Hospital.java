/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hospital;
import model.*;
import java.sql.SQLException;

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
          
        
        try
        {
            //distant
            //Connexion con1 = new Connexion(usernameECE, passwordECE, loginDatabaseECE, passwordDatabaseECE);
            
            //local
            Connexion con1 = new Connexion(nameDatabase, loginDatabase, passwordDatabase);
            Search SearchMutual = new Search(con1);
            SearchMutual.SearchWithWhere("nom,prenom","malade",SearchMutual.SearchMutual());

        }
        catch(Exception e)
        {
            System.out.println("CONNEXION ERROR!");
        }
    }
    
}
