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
        //ma tentative de connexion tunnel SSH T-T 
        //j'ai demandé à Gab
        //String username = "ad151913";
        //String password = "mvkPTN~0109";
        //String firstHost = "gandalf.ece.fr";
        //String secondHost = "sql-users.ece.fr";
        //int firstHostPort = 22;
        //int secondHostPort = 3305;
        
        
        // SSHTunnel con1 = new SSHTunnel(username,password,firstHost,secondHost,firstHostPort,secondHostPort);
        //con1.connect();
        
        //connexion en local
        String nameDatabase = "Hopital";
        String loginDatabase = "root";
        String passwordDatabase = "";   
                
        Connexion con1 = new Connexion(nameDatabase, loginDatabase, passwordDatabase);
        
        //pour l'instant je m'en sers juste pour lire le contenu de la table et tester bêtement que ça marche :)
        System.out.println(con1.remplirChampsRequete("SELECT * from malade"));
    }
    
}
