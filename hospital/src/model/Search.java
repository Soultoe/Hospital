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
     * @name convertToString
     * @param SQLresult
     * @return 
     */
     public String[][] convertToString(ArrayList SQLresult) throws SQLException, ClassNotFoundException
    {
        
        int nbRows = SQLresult.size();
        
        String columnValues[];
        String values[][];
        
        //si la requête retourne quelque chose, alors on stock nos données de l'arraylist dans un String[][]
        if(nbRows !=0)
        {
            int nbColumns=SQLresult.get(0).toString().length() - SQLresult.get(0).toString().replace(",","").length()+1;
            values=new String[nbRows][nbColumns];
            for(int i=0; i<nbRows;i++)
            {
                columnValues=SQLresult.get(i).toString().split(",");
                for(int j=0; j<nbColumns;j++)
                {
                   values[i][j]=columnValues[j];
                }
            }
        }
        //on retourne une matrice vide si la requête ne retourne rien
        else
        {
            //on retourne une matrice vide
            values = new String[1][tableColumnsName().length];
        }
            
        return values;
    }
     
    /**
     * @name tableColumnsName
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[] tableColumnsName() throws SQLException, ClassNotFoundException
    {
        //cette fonction sert à récupérer les noms des colonnes de la table résultant de notre requête afin d'en faire des en-têtes pour les JTable
        String columnNames = con.remplirChampsTable("SELECT "+select+" FROM "+from).toString().replace("[ ","");
        columnNames = columnNames.replace("\n]","");
        String title[]=columnNames.split(" ");
        return title;
    }
    
    /**
     * @name SearchWithoutWhere
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList SearchWithoutWhere() throws SQLException, ClassNotFoundException
    {
        return con.remplirChampsRequete("SELECT "+select+" FROM "+from);
    }
    
    /**
     * @name SearchWithWhere
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList SearchWithWhere() throws SQLException, ClassNotFoundException
    {
        return con.remplirChampsRequete("SELECT "+select+" FROM "+from+" WHERE "+where);
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
    public String[][] SearchRoom(String building, String department, String bedNumber, String roomNumber,  String warden ) throws  SQLException, ClassNotFoundException
    { 
        select = "batiment as \"bat\" , concat(dirnom, \" \", dirprenom) as Directeur, code_service as \"service\", no_chambre as \"n°chambre\", concat(employe.numero, \" | \",employe.nom, \" \",employe.prenom) as surveillant, nb_lits as \"lit\"";
        from = "chambre, employe, service, (select code, employe.nom as dirnom, prenom as dirprenom from service, employe where directeur = numero) dir";
        where = "numero = surveillant AND code_service = service.code and dir.code =code_service AND batiment like '%"+building+"%' AND code_service like '%"+department+"%' AND concat(employe.nom, \" \",employe.prenom) like '%"+warden+"%' AND no_chambre like '%"+roomNumber+"%' AND nb_lits like '%"+bedNumber+"%' ORDER BY batiment, code_service, no_chambre";
        
        return this.convertToString(SearchWithWhere());
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
    public String[][] SearchDoctor(String speciality, String patient, String firstLastName, String ID, String patientsNumberMin, String patientsNumberMax) throws SQLException, ClassNotFoundException
    {
       //parce que soit on cherche une liste de docteurs toute seule, soit on cherche une liste de docteurs par patients et c'est pas la même
       String selectPatient, fromPatient, wherePatient, wherePatientsNumberMax;
     
       //si on n'a pas entré le nom d'un patient, ou qu'on recherche pas un docteur par son nom, prénom ou ID on affiche pas le patient, sinon on l'affiche
       if((patient.length()!=0)||(firstLastName.length()!=0)||(ID.length()!=0))
       {
           selectPatient =" concat(m.numero, \" | \",m.nom, \" \",m.prenom) as Patient, ";
           fromPatient=" INNER JOIN soigne s ON d.numero=s.no_docteur INNER JOIN malade m ON m.numero = no_malade  ";
           wherePatient=" concat(m.nom, \" \", m.prenom) like '%"+patient+"%' and";
       }
       
       else
       {
           selectPatient =" ";
           fromPatient=" ";
           wherePatient=" ";
       }
       
       //si on ne demande pas de patient max alors on exécute pas le bout de la requête, sinon on l'éxécute
       if(patientsNumberMax.length()!=0)
       {
           wherePatientsNumberMax=" and nb_patients < '"+patientsNumberMax+"%' ";
       }
       else
       {
           wherePatientsNumberMax=" ";
       }
       
       select ="e.numero as ID, concat(e.nom, \" \", e.prenom) as Docteur, specialite as \"spécialité\","+selectPatient+"nb_patients, replace(e.adresse, ',', ';') as \"adresse\", e.tel as \"tel\"";
       from ="employe e INNER JOIN docteur d ON e.numero = d.numero "+fromPatient+" INNER JOIN (SELECT COUNT(no_malade) as nb_patients, no_docteur FROM soigne group by no_docteur) cmp ON cmp.no_docteur = e.numero ";
       where = wherePatient+" e.numero like '%"+ID+"%' and concat(e.nom, \" \", e.prenom) like '%"+firstLastName+"%' and specialite like '%"+speciality+"%' and nb_patients > '"+patientsNumberMin+"%'"+wherePatientsNumberMax;
           
       return convertToString(this.SearchWithWhere());
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
    public String[][] SearchNurse(String department, String rotation, String ID, String firstLastName) throws SQLException, ClassNotFoundException
    {
        
        select="i.numero as ID, concat(nom, \" \", prenom) as Infirmier, code_service as \"service\", rotation, replace(adresse, ',', ';') as \"adresse\", tel, salaire";
        from ="infirmier i inner join employe e on i.numero = e.numero";
        where = "i.numero like '%"+ID+"%' and concat(nom, \" \", prenom) like '%"+firstLastName+"%' and code_service like '%"+department+"%' and rotation like '%"+rotation+"%'";
       
        return convertToString(this.SearchWithWhere());
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
    public String[][] SearchPatient(String ID, String firstLastName, String mutual, String department, String roomNumber, String bedNumber, String doctor) throws SQLException, ClassNotFoundException
    {
        //si y a pas de docteur d'inscrit, ou qu'on cherche pas un patient par son ID ou nom/prénom alors on affiche pas le docteur
        if((doctor.length()!=0)||(firstLastName.length()!=0)||(ID.length()!=0))
        {
            select ="m.numero as ID, concat(m.nom, \" \", m.prenom) as Patient,replace(m.adresse, ',', ';') as \"adresse\",m.tel as \"tel\",m.mutuelle as \"mutuelle\", code_service as \"service\", no_chambre as \"n°chambre\", lit, concat(e.numero, \" | \",e.nom, \" \", e.prenom) as docteur";
            from="malade m INNER JOIN hospitalisation h ON m.numero = h.no_malade INNER JOIN soigne s ON m.numero = s.no_malade INNER JOIN employe e on s.no_docteur = e.numero";
            where=" m.numero = s.no_malade AND m.numero = h.no_malade AND m.numero LIKE '%"+ID+"%' AND concat(m.nom, \" \", m.prenom) LIKE '%"+firstLastName+"%' AND m.mutuelle LIKE '%"+mutual+"%' AND code_service LIKE '%"+department+"%' AND no_chambre LIKE '%"+roomNumber+"%' AND lit LIKE '%"+bedNumber+"%' AND concat(e.nom, \" \", e.prenom) LIKE '%"+doctor+"%'";
        }
        //sinon on affiche le docteur
        else
        {
            select ="m.numero as ID, concat(m.nom, \" \", m.prenom) as Patient,replace(m.adresse, ',', ';') as \"adresse\",m.tel as \"tel\",m.mutuelle as \"mutuelle\", code_service as \"service\", no_chambre as \"n°chambre\", lit";
            from="malade m INNER JOIN hospitalisation h ON m.numero = h.no_malade";
            where="m.numero LIKE '%"+ID+"%' AND concat(m.nom, \" \", m.prenom) LIKE '%"+firstLastName+"%' AND m.mutuelle LIKE '%"+mutual+"%' AND code_service LIKE '%"+department+"%' AND no_chambre LIKE '%"+roomNumber+"%' AND lit LIKE '%"+bedNumber+"%'";
        }
     
       return this.convertToString(SearchWithWhere());
    }
} 
   