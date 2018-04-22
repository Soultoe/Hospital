/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import hospital.*;
import java.sql.SQLException;

/**
 *
 * @author romain
 */
public class Add extends Action{
    
    /**
     * @name Overload constructor
     * @param con 
     */
    public Add(Connexion con)
    {
        super(con);
    }
    
    /**
     * @name getRequete
     * @param requete
     * @throws SQLException 
     */
    public void getRequete(String requete) throws SQLException
    {
           con.executeUpdate(requete);
    }
    
    /**
     * @name buildRequestEmployee
     * @param dOrN used to know if we are adding a doctor or a nurse
     * @param fields fields filled by the user with the chosen information
     * @return the outcome of the operation
     * @throws SQLException 
     */
    public String buildRequestEmployee(boolean dOrN, String[] fields) throws SQLException
    {
        String emp = "INSERT INTO employe(numero,nom,prenom,adresse,tel) VALUES(" + fields[0] + ", '" + fields[1] + "', '" + fields[2] + "', '" + fields[3] + "', '" + fields[4] + "');";
        String type;
        
        try {
            
            this.getRequete(emp);
        
            if(dOrN){ //means doctor
                type = "INSERT INTO docteur(numero,specialite) VALUES(" + fields[0] + ", '" + fields[5] + "');";
                System.out.println(type);
                this.getRequete(type);
            }
            else {
                type = "INSERT INTO infirmier(numero,code_service,rotation,salaire) VALUES(" + fields[0] + ", '" + fields[5] + "', '" + fields[6] + "', '" + fields[7] + "');";
                System.out.println(type);
                this.getRequete(type);
            }
        
            return "Insertion réussie!";
        }
        catch(SQLException e)
        {
            return "Votre requête est erronnée, vérifiez vos entrées.";
        }
        
    }
    
    /**
     * @name buildRequestPatients
     * @param fields fields filled by the user with the chosen information
     * @return the outcome of the operation
     * @throws SQLException 
     */
    public String buildRequestPatients(String[] fields) throws SQLException {
        try {
            String patient = "INSERT INTO malade(numero,nom,prenom,adresse,tel,mutuelle) VALUES(" + fields[0] + ", '" + fields[1] + "', '" + fields[2] + "', '" + fields[3] + "', '" + fields[4] + "', '" + fields[5] + "');";
            String heal = "INSERT INTO soigne(no_docteur,no_malade) VALUES (" + fields[6] + ", " + fields[0] + ");";
            String hospitalize = "INSERT INTO hospitalisation(no_malade,code_service,no_chambre,lit) VALUES(" + fields[0] + ", '" + fields[7] + "', " + fields[8] + ", " + fields[9] + ");";
            System.out.println(patient);
            System.out.println(heal);
            System.out.println(hospitalize);

            this.getRequete(patient);
            this.getRequete(heal);
            this.getRequete(hospitalize);

            return "Insertion réussie!";
        } catch (SQLException e) {
            return "Votre requête est erronnée, vérifiez vos entrées.";
        }
    }

    /**
     * @name buildRequestDepartment
     * @param fields fields filled by the user with the chosen information
     * @return the outcome of the operation
     * @throws SQLException 
     */
    public String buildRequestDepartment(String[] fields) throws SQLException {
        try {
            String request = "INSERT INTO service(code,nom,batiment,directeur) VALUES ('" + fields[0] + "' ,'" + fields[1] + "' ,'" + fields[2] + "' ,'" + fields[3] + "');";
            System.out.println(request);
            this.getRequete(request);
            return "Insertion réussie!";
        } catch (SQLException e) {
            return "Votre requête est erronnée, vérifiez vos entrées.";
        }
    }

    /**
     * @name buildRequestRoom
     * @param fields fields filled by the user with the chosen information
     * @return the outcome of the operation
     * @throws SQLException 
     */
    public String buildRequestRoom(String[] fields) throws SQLException {
        try {
            String request = "INSERT INTO chambre(code_service,no_chambre,surveillant,nb_lits) VALUES ('" + fields[0] + "' ," + fields[1] + " ," + fields[2] + " ," + fields[3] + ");";
            System.out.println(request);
            this.getRequete(request);
            return "Insertion réussie!";
        } catch (SQLException e) {
            return "Votre requête est erronnée, vérifiez vos entrées.";
        }
    }
    
}
