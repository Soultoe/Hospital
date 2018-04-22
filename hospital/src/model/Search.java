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
     public String[][] convertToString(ArrayList SQLresult)
    {
        int nbRows = SQLresult.size();
        int nbColumns=SQLresult.get(0).toString().length() - SQLresult.get(0).toString().replace(",","").length()+1;
        String columnValues[];
        String values[][]=new String[nbRows][nbColumns];
        
        for(int i=0; i<nbRows;i++)
        {
            columnValues=SQLresult.get(i).toString().split(",");
            for(int j=0; j<nbColumns;j++)
            {
               values[i][j]=columnValues[j];
               
            }
            
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
        String columnNames = con.remplirChampsTable("SELECT "+select+" FROM "+from).toString().replace("[ ","");
        columnNames = columnNames.replace("\n]","");
//         String columnNames = con.remplirChampsTable("SELECT "+select+" FROM "+from).toString();
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
        select = "batiment as \"bat\" , code_service as \"service\", no_chambre as \"n°chambre\", concat(employe.nom, \" \",employe.prenom) as surveillant, nb_lits as \"lit\"";
        from = "chambre, employe, service";
        where = "numero = surveillant AND code_service = service.code AND batiment like '%"+building+"%' AND code_service like '%"+department+"%' AND concat(employe.nom, \" \",employe.prenom) like '%"+warden+"%' AND no_chambre like '%"+roomNumber+"%' AND nb_lits like '%"+bedNumber+"%' ORDER BY batiment, code_service, no_chambre";
        
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
    public String[][] SearchDoctor(String speciality, String patient, String firstName, String lastName, String patientsNumberMin, String patientsNumberMax) throws SQLException, ClassNotFoundException
    {
       //parce que soit on cherche une liste de docteurs toute seule, soit on cherche une liste de docteurs par patients et c'est pas la même
       String selectPatient, fromPatient, wherePatient, wherePatientsNumberMax;
     
       if(patient.length()!=0)
       {
           selectPatient =" concat(m.nom, \" \",m.prenom) as Patient, ";
           fromPatient=" INNER JOIN soigne s ON d.numero=s.no_docteur INNER JOIN malade m ON m.numero = no_malade  ";
           wherePatient=" concat(m.nom, \" \", m.prenom) like '%"+patient+"%' and";
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
       
       select ="e.nom as \"nom\", e.prenom as \"prenom\", specialite as \"spécialité\","+selectPatient+"nb_patients, replace(e.adresse, ',', ';') as \"adresse\", e.tel as \"tel\"";
       from ="employe e INNER JOIN docteur d ON e.numero = d.numero "+fromPatient+" INNER JOIN (SELECT COUNT(no_malade) as nb_patients, no_docteur FROM soigne group by no_docteur) cmp ON cmp.no_docteur = e.numero ";
       where = wherePatient+" e.nom like '%"+lastName+"%' and e.prenom like '%"+firstName+"%' and specialite like '%"+speciality+"%' and nb_patients > '"+patientsNumberMin+"%'"+wherePatientsNumberMax;
           
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
    public String[][] SearchNurse(String department, String rotation, String lastName, String firstName) throws SQLException, ClassNotFoundException
    {
        
        select="nom, prenom, code_service as \"service\", rotation, replace(adresse, ',', ';') as \"adresse\", tel, salaire";
        from ="infirmier i inner join employe e on i.numero = e.numero";
        where = "nom like '%"+lastName+"%' and prenom like '%"+firstName+"%' and code_service like '%"+department+"%' and rotation like '%"+rotation+"%'";
       
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
    public String[][] SearchPatient(String lastName, String firstName, String mutual, String department, String roomNumber, String bedNumber, String doctor) throws SQLException, ClassNotFoundException
    {
        if(doctor.length()!=0)
        {
            select ="m.nom as \"nom\",m.prenom as \"prenom\",replace(m.adresse, ',', ';') as \"adresse\",m.tel as \"tel\",m.mutuelle as \"mutuelle\", code_service as \"service\", no_chambre as \"n°chambre\", lit, concat(e.nom, \" \", e.prenom) as docteur";
            from="malade m INNER JOIN hospitalisation h ON m.numero = h.no_malade INNER JOIN soigne s ON m.numero = s.no_malade INNER JOIN employe e on s.no_docteur = e.numero";
            where=" m.numero = s.no_malade AND m.numero = h.no_malade AND m.nom LIKE '%"+lastName+"%' AND m.prenom LIKE '%"+firstName+"%' AND m.mutuelle LIKE '%"+mutual+"%' AND code_service LIKE '%"+department+"%' AND no_chambre LIKE '%"+roomNumber+"%' AND lit LIKE '%"+bedNumber+"%' AND concat(e.nom, \" \", e.prenom) LIKE '%"+doctor+"%'";
        }
        else
        {
            select ="m.nom as \"nom\",m.prenom as \"prenom\",replace(m.adresse, ',', ';') as \"adresse\",m.tel as \"tel\",m.mutuelle as \"mutuelle\", code_service as \"service\", no_chambre as \"n°chambre\", lit";
            from="malade m INNER JOIN hospitalisation h ON m.numero = h.no_malade";
            where="m.nom LIKE '%"+lastName+"%' AND m.prenom LIKE '%"+firstName+"%' AND m.mutuelle LIKE '%"+mutual+"%' AND code_service LIKE '%"+department+"%' AND no_chambre LIKE '%"+roomNumber+"%' AND lit LIKE '%"+bedNumber+"%'";
        }
     
       return this.convertToString(SearchWithWhere());
    }
} 
   