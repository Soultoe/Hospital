/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hospital;
import model.*;
import vue.*;
import java.sql.SQLException;
import java.util.Scanner;
import org.jfree.data.general.DefaultPieDataset;

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
            //Connexion distantCon = new Connexion(usernameECE, passwordECE, loginDatabaseECE, passwordDatabaseECE);
            Connexion distantCon = new Connexion(nameDatabase, loginDatabase, passwordDatabase); //CAREFUL THIS IS A LOCAL CONNEXION BUT I PUT IT LIKE THIS IN DISTANT CUZ DISTANT DOES NOT WORK

            
            //local
            Connexion localCon = new Connexion(nameDatabase, loginDatabase, passwordDatabase);
            
            //Fenetre
            Frame fen = new Frame(localCon,distantCon);

        }
        catch(ClassNotFoundException | SQLException e)
        {
            System.out.println("CONNEXION ERROR!");
        }
    }
    
}
