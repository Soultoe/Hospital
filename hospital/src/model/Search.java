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
    private String select, from, where;
    
    public Search(Connexion con)
    {
        this.con=con;
    }
    
     public void SearchWithoutWhere() throws SQLException, ClassNotFoundException
    {
        System.out.println(con.remplirChampsRequete("SELECT "+select+" FROM "+from));
    }
    
    public void SearchWithWhere() throws SQLException, ClassNotFoundException
    {
        System.out.println(con.remplirChampsRequete("SELECT "+select+" FROM "+from+" WHERE "+where));
    }
    
    public void SearchRoom(String building, String department, String bedNumber, String roomNumber,  String warden ) throws  SQLException, ClassNotFoundException
    { 
        select = "batiment, code_service, no_chambre, concat(employe.nom, \" \",employe.prenom) as surveillant, nb_lits";
        from = "chambre, employe, service";
        where = "numero = surveillant AND code_service = service.code AND batiment like '%"+building+"%' AND code_service like '%"+department+"%' AND concat(employe.nom, \" \",employe.prenom) like '%"+warden+"%' AND no_chambre like '%"+roomNumber+"%' AND nb_lits like '%"+bedNumber+"%' ORDER BY batiment, code_service, no_chambre";
        this.SearchWithWhere();
    }
    
    public void SearchEmployee(String department, String rotation, String speciality, String patient, String firstName, String lastName) throws SQLException, ClassNotFoundException
    {
            System.out.println("== Liste des docteurs ==");
            this.SearchDoctor(speciality, patient, firstName, lastName);
            System.out.println("== Liste des infirmiers ==");
            this.SearchNurse(department, rotation, lastName, firstName);
            System.out.println("== Fin liste ==");
    }
    
    public void SearchDoctor(String speciality, String patient, String firstName, String lastName) throws SQLException, ClassNotFoundException
    {
       //parce que soit on cherche une liste de docteurs toute seule, soit on cherche une liste de docteurs par patients et c'est pas la même
       if(patient.length()!=0)
       {
           select="e.nom, e.prenom, specialite, concat(m.nom, \" \",m.prenom) as Patient, e.adresse, e.tel";
           from ="employe e, docteur d, malade m, soigne s";
           where ="e.numero = d.numero AND m.numero = no_malade and d.numero=no_docteur and concat(m.nom, \" \", m.prenom) like '%"+patient+"%' and e.nom like '%"+lastName+"%' and e.prenom like '%"+firstName+"%' and specialite like '%"+speciality+"%'";
           
       }
       else
       {
           select="e.nom, e.prenom, specialite, e.adresse, e.tel";
           from ="employe e, docteur d, malade m";
           where ="e.numero = d.numero and e.nom like '%"+lastName+"%' and e.prenom like '%"+firstName+"%' and specialite like '%"+speciality+"%'";
       }
           
        this.SearchWithWhere();
    }
    
    public void SearchNurse(String department, String rotation, String lastName, String firstName) throws SQLException, ClassNotFoundException
    {
        
        select="nom, prenom, code_service, rotation, adresse, tel, salaire";
        from ="infirmier i inner join employe e on i.numero = e.numero";
        where = "nom like '%"+lastName+"%' and prenom like '%"+firstName+"%' and code_service like '%"+department+"%' and rotation like '%"+rotation+"%'";
        this.SearchWithWhere();
    }
    
    
    
    //ANCIEN CODE
//    //Faudra un champ texte pour celui là
//    public String SearchMutual()
//    {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Selon quelle mutuelle voulez vous trier les patients?");
//        String MutualName = sc.nextLine();
//        return "mutuelle like '"+MutualName+"'";
//    }
//    
//    //des boutons suffisent pour celui-là
//    public String nursingRotation() throws SQLException, ClassNotFoundException
//    {
//        Scanner sc = new Scanner(System.in);
//        int rotation;
//        
//        //String rot;
//        do
//        {
//            System.out.println("Infirmiers travaillant le jour (0) ou la nuit (1)");
//            rotation = sc.nextInt();
//        }while((rotation != 1)&&(rotation != 0));
//        
//        if(rotation == 0)
//           return "'NUIT'"; 
//        else
//           return "'JOUR'";
//        
//        //this.SearchWithWhere("numero","infirmier","rotation like "+rot);
//        
//       //SELECT nom, prenom from employe e, (SELECT numero from infirmier where rotation like 'JOUR') i where i.numero = e.numero 
//       //this.SearchWithWhere("nom, prenom", "employe e, (SELECT numero from infirmier where rotation like "+rot+") i", "i.numero = e.numero ");  
//       //System.out.println(con.remplirChampsRequete("SELECT nom, prenom from employe e, (SELECT numero from infirmier where rotation like 'JOUR') i where i.numero = e.numero"));
//    }
//    
//    public void menu(int choice) throws SQLException, ClassNotFoundException
//    {
//        //pour nos recherches
//            String select, from, where;
//            
//            //c'est juste un teste hein je nettoierai ça après
//            if(choice == 0)
//            {
//                select = "nom,prenom";
//                from = "malade";
//                where = this.SearchMutual();
//            }
//            else
//            {
//                //SearchMutual.nursingRotation();
//                select = "nom,prenom";
//                from =  "employe e, (SELECT numero from infirmier where rotation like "+this.nursingRotation()+") i";
//                where  = "i.numero = e.numero ";
//            }
//            
//            this.SearchWithWhere(select, from, where);
//    }
}
