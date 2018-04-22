/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import hospital.*;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author User
 */
public class Reporting extends Action {
    //initialisation des variables
    private String requete;
    
    /**
     * @name Overload constructor
     * @param con 
     */
    public Reporting(Connexion con)
    {
        super(con);
    }
    
    /**
     * @name tableColumnsName
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[] tableColumnsName() throws SQLException, ClassNotFoundException
    {
        //on récupère les noms de chaque colonne de la table résultant de notre requête
        String columnNames = con.remplirChampsTable(requete).toString().replace("[ ","");
        columnNames = columnNames.replace("\n]","");
        
        //on les stock dans une matrice de string
        String title[]=columnNames.split(" ");
        return title;
    }
    
    
    
    /**
     * @name searchWithWhere
     * @return cette méthode retourne le résultat d'une requête SQL avec une ou plusieurs conditions WHERE
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ArrayList SearchWithWhere() throws SQLException, ClassNotFoundException
    {
        return con.remplirChampsRequete(requete);
    }
    

    /**
     * @name convertToString
     * @param SQLresult
     * @return cette méthode sert à convertir l'ArrayList en une matrice de String à 2 dimensions
     */
    public String[][] convertToString(ArrayList SQLresult)
    {
        //on récupère le nombre de lignes que fait le résultat de notre requête
        int nbRows = SQLresult.size();
        
        //on compte également le nombre de colonnes
        int nbColumns=SQLresult.get(0).toString().length() - SQLresult.get(0).toString().replace(",","").length()+1;
        
        //initialisation des variables
        String columnValues[];
        String values[][]=new String[nbRows][nbColumns];
        
        //on stock le contenu de notre arraylist dans une matrice de String à 2 dimensions
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
    
    
    //I.Employés
    //a. Les infirmiers
    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[][] reportingNurse() throws SQLException, ClassNotFoundException
    {
        //cette requête retourne les informations suivantes : service, effectif, nombre de malades, salaire moyen, effectif par roation et par service, et nombre de malades par infirmier
        requete = "SELECT s.nom as \"service\", nb as \"effectif\", nb_m as \"malades\", moy as \"salaireMoyen\",  rotation, count(rotation) as \"effectifRot\", nb_m/nb as \"mal/inf\" from infirmier inner join (SELECT code_service, avg(salaire) as moy, count(code_service) as nb from infirmier group by code_service) cs on cs.code_service=infirmier.code_service inner join (SELECT code_service, count(no_malade) as nb_m from hospitalisation group by code_service) cm on cm.code_service = cs.code_service inner join service s on s.code = cs.code_service group by cs.code_service, rotation";
        String finalTab[][]=this.convertToString(this.SearchWithWhere());

        return finalTab;
    }
    
    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[][] dayWorkforce() throws SQLException, ClassNotFoundException
    {
        //cette requête retourne le nombre d'infirmiers travaillant le jour par service
        requete = "SELECT s.nom, rotation, count(rotation) from infirmier inner join (SELECT code_service, avg(salaire) as moy, count(code_service) as nb from infirmier group by code_service) cs on cs.code_service=infirmier.code_service inner join service s on s.code = cs.code_service where rotation='JOUR' group by cs.code_service, rotation";
        String finalTab[][]=this.convertToString(this.SearchWithWhere());

        return finalTab;
    }
    
    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[][] nightWorkforce() throws SQLException, ClassNotFoundException
    {
        //cette requête retourne le nombre d'infirmiers travaillant la nuit par service
        requete = "select s.nom, rotation, count(rotation) from infirmier inner join (SELECT code_service, avg(salaire) as moy, count(code_service) as nb from infirmier group by code_service) cs on cs.code_service=infirmier.code_service inner join service s on s.code = cs.code_service where rotation='NUIT' group by cs.code_service, rotation";
        String finalTab[][]=this.convertToString(this.SearchWithWhere());

        return finalTab;
    }
    
    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[][] reportingDoctor() throws SQLException, ClassNotFoundException
    {
        requete =  "select d.specialite, count(d.specialite) as \"effectif\", nb_malades as \"malades\", nb_malades/count(d.specialite) as \"mal/doc\" from docteur d, (select specialite, count(no_malade) as nb_malades from docteur, soigne where numero = no_docteur group by specialite) m where d.specialite = m.specialite group by d.specialite";
        String finalTab[][]=this.convertToString(this.SearchWithWhere());

        return finalTab;
    }
    
    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[][] reportingEmployee() throws SQLException, ClassNotFoundException
    {
        requete="SELECT nb_inf, nb_doc from (SELECT count(DISTINCT numero) as nb_doc from docteur) d inner join (SELECT count(DISTINCT numero) as nb_inf from infirmier) i";
        String finalTab[][]=this.convertToString(this.SearchWithWhere());

        return finalTab;
    }
    
    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[][] roomsAndBedsAvaibility() throws SQLException, ClassNotFoundException
    {
        requete="select batiment, nom, count(c.no_chambre), sum(nb_lits), ch_occupee, (count(c.no_chambre)-ch_occupee), lit_occupe, sum(nb_lits)-lit_occupe from chambre c, service, (select code_service, no_chambre, count(DISTINCT no_chambre) as ch_occupee, count(lit) as lit_occupe from hospitalisation group by code_service) h where code = c.code_service and (h.code_service = c.code_service) group by batiment, c.code_service";
        String finalTab[][]=this.convertToString(this.SearchWithWhere());

        return finalTab;
    }
    
    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[][] reportingRoom() throws SQLException, ClassNotFoundException
    {
        requete="select batiment as bat, nom, count(c.no_chambre) as totCh, sum(nb_lits) as totLits, ch_occupee as \"chOccupées\", lit_occupe as \"litOccupé\", nb_sur nbSurv from service INNER JOIN chambre c ON code = c.code_service INNER JOIN (select code_service, no_chambre, count(DISTINCT no_chambre) as ch_occupee, count(lit) as lit_occupe from hospitalisation group by code_service) h ON h.code_service = c.code_service INNER JOIN (select code_service, count(DISTINCT surveillant) as nb_sur from chambre group by code_service) as sur ON sur.code_service = c.code_service group by batiment, c.code_service";
        String finalTab[][]=this.convertToString(this.SearchWithWhere());

        return finalTab;
    }
    
    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[][] wardenDistribution() throws SQLException, ClassNotFoundException
    {
        requete="select batiment, nom, nb_sur from service INNER JOIN chambre c ON code = c.code_service INNER JOIN (select code_service, no_chambre, count(DISTINCT no_chambre) as ch_occupee, count(lit) as lit_occupe from hospitalisation group by code_service) h ON h.code_service = c.code_service INNER JOIN (select code_service, count(DISTINCT surveillant) as nb_sur from chambre group by code_service) as sur ON sur.code_service = c.code_service group by batiment, c.code_service";
        String finalTab[][]=this.convertToString(this.SearchWithWhere());
        return finalTab;
    }
    
    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public String[][] reportingPatient() throws SQLException, ClassNotFoundException
    {
        requete="select s.nom, count(DISTINCT numero) as patients, count(DISTINCT no_malade) as \"hospitalises\", count(DISTINCT numero)-su.sum as malades, su.sum as totHosp from hospitalisation, service s, malade, (select count(*) as sum from hospitalisation) as su where s.code = code_service group by code_service";
        String finalTab[][]=this.convertToString(this.SearchWithWhere());
        return finalTab;
    }
    
    
 }