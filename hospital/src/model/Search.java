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
public class Search extends Action{
    private String select, from, where;
    
    /**
     * @name Overload constructor
     * @param con 
     */
    public Search(Connexion con)
    {
        super(con);
        select = from = where = "";
    }
    
    /**
     * @name SearchWithoutWhere
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void SearchWithoutWhere() throws SQLException, ClassNotFoundException
    {
        System.out.println(con.remplirChampsRequete("SELECT "+select+" FROM "+from));
    }
    
    /**
     * @name SearchWithWhere
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void SearchWithWhere() throws SQLException, ClassNotFoundException
    {
        System.out.println(con.remplirChampsRequete("SELECT "+select+" FROM "+from+" WHERE "+where));
    }
    
    /**
     * @name SearchRoom
     * @param building
     * @param department
     * @param bedNumber
     * @param roomNumber
     * @param warden
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void SearchRoom(String building, String department, String bedNumber, String roomNumber,  String warden ) throws  SQLException, ClassNotFoundException
    { 
        select = "batiment, code_service, no_chambre, concat(employe.nom, \" \",employe.prenom) as surveillant, nb_lits";
        from = "chambre, employe, service";
        where = "numero = surveillant AND code_service = service.code AND batiment like '%"+building+"%' AND code_service like '%"+department+"%' AND concat(employe.nom, \" \",employe.prenom) like '%"+warden+"%' AND no_chambre like '%"+roomNumber+"%' AND nb_lits like '%"+bedNumber+"%' ORDER BY batiment, code_service, no_chambre";
        this.SearchWithWhere();
    }
    
    /**
     * @name SearchEmployee
     * @param department
     * @param rotation
     * @param speciality
     * @param patient
     * @param firstName
     * @param lastName
     * @param patientsNumberMin
     * @param patientsNumberMax
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void SearchEmployee(String department, String rotation, String speciality, String patient, String firstName, String lastName, String patientsNumberMin, String patientsNumberMax) throws SQLException, ClassNotFoundException
    {
            System.out.println("== Liste des docteurs ==");
            this.SearchDoctor(speciality, patient, firstName, lastName, patientsNumberMin, patientsNumberMax);
            System.out.println("== Liste des infirmiers ==");
            this.SearchNurse(department, rotation, lastName, firstName);
            System.out.println("== Fin liste ==");
    }
    
    /**
     * @name SearchDoctor
     * @param speciality
     * @param patient
     * @param firstName
     * @param lastName
     * @param patientsNumberMin
     * @param patientsNumberMax
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void SearchDoctor(String speciality, String patient, String firstName, String lastName, String patientsNumberMin, String patientsNumberMax) throws SQLException, ClassNotFoundException
    {
       //parce que soit on cherche une liste de docteurs toute seule, soit on cherche une liste de docteurs par patients et c'est pas la même
       String selectPatient, fromPatient, wherePatient, wherePatientsNumberMax;
//       if((patient.length()!=0)&&(patientsNumberMax.length()!=0))
//       {
//           select="e.nom, e.prenom, specialite, concat(m.nom, \" \",m.prenom) as Patient, nb_patients, e.adresse, e.tel";
//           from ="employe e, docteur d, malade m, soigne s, (SELECT COUNT(no_malade) as nb_patients, no_docteur FROM soigne group by no_docteur) cmp";
//           where ="e.numero = d.numero AND m.numero = no_malade and d.numero=s.no_docteur and cmp.no_docteur = e.numero and concat(m.nom, \" \", m.prenom) like '%%' and e.nom like '%%' and e.prenom like '%%' and specialite like '%%' and nb_patients > '%%' and nb_patients < '%%'";
//           
//       }
//       else if((patient.length()!=0)&&(patientsNumberMax.length()==0))
//       {
//           
//       }
//        else
//        {
//            select=" e.nom, e.prenom, specialite, nb_patients, e.adresse, e.tel";
//            from ="employe e, docteur d, malade m, (SELECT COUNT(no_malade) as nb_patients, no_docteur FROM soigne group by no_docteur) cmp";
//            where ="e.numero = d.numero and cmp.no_docteur = e.numero and e.nom like '%%' and e.prenom like '%%' and specialite like '%%' and nb_patients < '%%' and nb_patients > '%%'";
//        }
       if(patient.length()!=0)
       {
           selectPatient =" concat(m.nom, \" \",m.prenom) as Patient, ";
           fromPatient="  malade m, soigne s, ";
           wherePatient=" AND m.numero = no_malade AND d.numero=s.no_docteur and concat(m.nom, \" \", m.prenom) like '%"+patient+"%'";
       }
       else
       {
           selectPatient =" ";
           fromPatient=" ";
           wherePatient=" ";
       }
       
       if(patientsNumberMax.length()!=0)
       {
           wherePatientsNumberMax=" and nb_patients < '"+patientsNumberMax+"%' ";
       }
       else
       {
           wherePatientsNumberMax=" ";
       }
       
       select ="e.nom, e.prenom, specialite,"+selectPatient+"nb_patients, e.adresse, e.tel";
       from ="employe e, docteur d,"+fromPatient+" (SELECT COUNT(no_malade) as nb_patients, no_docteur FROM soigne group by no_docteur) cmp";
       where = "e.numero = d.numero"+wherePatient+"and cmp.no_docteur = e.numero and e.nom like '%"+lastName+"%' and e.prenom like '%"+firstName+"%' and specialite like '%"+speciality+"%' and nb_patients > '"+patientsNumberMin+"%'"+wherePatientsNumberMax;
           
       this.SearchWithWhere();
    }
    
    /**
     * @name SearchNurse
     * @param department
     * @param rotation
     * @param lastName
     * @param firstName
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void SearchNurse(String department, String rotation, String lastName, String firstName) throws SQLException, ClassNotFoundException
    {
        
        select="nom, prenom, code_service, rotation, adresse, tel, salaire";
        from ="infirmier i inner join employe e on i.numero = e.numero";
        where = "nom like '%"+lastName+"%' and prenom like '%"+firstName+"%' and code_service like '%"+department+"%' and rotation like '%"+rotation+"%'";
        this.SearchWithWhere();
    }
    
    /**
     * @SearchPatient
     * @param lastName
     * @param firstName
     * @param mutual
     * @param department
     * @param roomNumber
     * @param bedNumber
     * @param doctor
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public void SearchPatient(String lastName, String firstName, String mutual, String department, String roomNumber, String bedNumber, String doctor) throws SQLException, ClassNotFoundException
    {
        if(doctor.length()!=0)
        {
            select ="m.nom,m.prenom,m.adresse,m.tel,m.mutuelle, code_service, no_chambre, lit, concat(e.nom, \" \", e.prenom) as docteur";
            from="malade m, hospitalisation h, soigne s, employe e";
            where=" m.numero = s.no_malade AND m.numero = h.no_malade AND s.no_docteur = e.numero AND m.nom LIKE '%"+lastName+"%' AND m.prenom LIKE '%"+firstName+"%' AND m.mutuelle LIKE '%"+mutual+"%' AND code_service LIKE '%"+department+"%' AND no_chambre LIKE '%"+roomNumber+"%' AND lit LIKE '%"+bedNumber+"%' AND concat(e.nom, \" \", e.prenom) LIKE '%"+doctor+"%'";
        }
        else
        {
            select ="m.nom,m.prenom,m.adresse,m.tel,m.mutuelle, code_service, no_chambre, lit";
            from="malade m INNER JOIN hospitalisation h ON m.numero = h.no_malade";
            where="m.nom LIKE '%"+lastName+"%' AND m.prenom LIKE '%"+firstName+"%' AND m.mutuelle LIKE '%"+mutual+"%' AND code_service LIKE '%"+department+"%' AND no_chambre LIKE '%"+roomNumber+"%' AND lit LIKE '%"+bedNumber+"%'";
        }
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
