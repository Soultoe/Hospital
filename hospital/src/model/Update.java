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
public class Update extends Action {

    /**
     * @name Overload constructor
     * @param con
     */
    public Update(Connexion con) {
        super(con);
    }

    /**
     * @name getUpdate
     * @param requete
     * @throws SQLException
     */
    public void getUpdate(String requete) throws SQLException {
        con.executeUpdate(requete);
    }

    /**
     * @name buildRequestEmployee
     * @param dOrN information about the employee beeing a doctor or a nurse
     * @param fields the fields filled by the user
     * @return the outcome of the request
     * @throws SQLException 
     */
    public String buildRequestEmployee(boolean dOrN, String[] fields) throws SQLException {
        
        boolean[] emptyFields = new boolean[fields.length];
        for (int i=0;i<fields.length;i++) {
            emptyFields[i] = !fields[i].equals("");
        }
        
        String adress,tel; adress = tel = "";
        
        if(emptyFields[3])
            if(emptyFields[4])
                adress = "adresse = '" + fields[3] + "', ";
            else
                adress = "adresse = '" + fields[3] + "'";
        if(emptyFields[4])
            tel = "tel = '" + fields[4] + "'";
        
        try {
            
            if ("".equals(adress) && "".equals(tel)) {
                return "modifiez le champ d'adresse ou de téléphone.";
            } else {
                String emp = "UPDATE employe SET " + adress + tel + " WHERE numero = " + fields[0] + ";";
                System.out.println(emp);
                String type;

                this.getUpdate(emp);

                if (dOrN) { //means doctor
                    type = "UPDATE docteur SET specialite = '" + fields[5] + "' WHERE numero = " + fields[0] + ";";
                    System.out.println(type);
                    this.getUpdate(type);
                } else {
                    type = "UPDATE infirmier SET code_service = '" + fields[5] + "', rotation = '" + fields[6] + "', salaire = '" + fields[7] + "' WHERE numero = " + fields[0] + ";";
                    System.out.println(type);
                    this.getUpdate(type);
                }

                return "Mise à Jour Réussie!";
            }
        } catch (SQLException e) {
            return "Votre requête est erronnée, vérifiez vos entrées.";
        }

    }

    /**
     * @name buildRequestPatients
     * @param fields the fields filled by the user
     * @return the outcome of the request
     * @throws SQLException 
     */
    public String buildRequestPatients(String[] fields) throws SQLException {
        
        boolean[] emptyFields = new boolean[fields.length];
        for (int i=0;i<fields.length;i++) {
            emptyFields[i] = !fields[i].equals("");
        }
        
        try {
            String patient = "UPDATE malade SET nom = '" + fields[1] + "', prenom = '" + fields[2] + "', adresse = '" + fields[3] + "', tel = '" + fields[4] + "', mutuelle = '" + fields[5] + "' WHERE numero = " + fields[0] + ";";
            String heal = "UPDATE soigne SET no_docteur = '" + fields[6] + "' WHERE no_malade = " + fields[0] + ";";
            String hospitalize = "UPDATE hospitalisation SET code_service = '" + fields[7] + "', " + fields[8] + ", " + fields[9] + " WHERE no_malade = " + fields[0] + ";";

            System.out.println(patient);
            System.out.println(heal);
            System.out.println(hospitalize);

            this.getUpdate(patient);
            this.getUpdate(heal);
            this.getUpdate(hospitalize);

            return "Mise à Jour Réussie!";
        } catch (SQLException e) {
            return "Votre requête est erronnée, vérifiez vos entrées.";
        }

    }

    /**
     * @name buildRequestDepartment
     * @param fields the fields filled by the user
     * @return the outcome of the request
     * @throws SQLException 
     */
    public String buildRequestDepartment(String[] fields) throws SQLException {
        
        boolean[] emptyFields = new boolean[fields.length];
        for (int i=0;i<fields.length;i++) {
            emptyFields[i] = !fields[i].equals("");
        }
        
        String batiment,directeur; batiment = directeur = "";
        
        if(emptyFields[2])
            if(emptyFields[3])
                batiment = "batiment = '" + fields[2] + "', ";
            else
                batiment = "batiment = '" + fields[2] + "'";
        if(emptyFields[3])
            directeur = "directeur = " + fields[3];
        
        try {

            if ("".equals(batiment) && "".equals(directeur)) {
                return "Remplissez au moins le champ batiment ou directeur pour qu'un changement soit possible.";
            } else {

                String request = "UPDATE service SET " + batiment + directeur + " WHERE code = '" + fields[0] + "' AND nom = '" + fields[1] + "';";
                System.out.println(request);
                this.getUpdate(request);
                return "Mise à Jour Réussie!";
            }
        } catch (SQLException e) {
            return "Votre requête est erronnée, vérifiez vos entrées.";
        }

    }

    /**
     * @name buildRequestRoom
     * @param fields the fields filled by the user
     * @return the outcome of the request
     * @throws SQLException 
     */
    public String buildRequestRoom(String[] fields) throws SQLException {

        boolean[] emptyFields = new boolean[fields.length];
        for (int i=0;i<fields.length;i++) {
            emptyFields[i] = !fields[i].equals("");
        }
        
        String surveillant,nb_lits; surveillant = nb_lits = "";
        
        if(emptyFields[2])
            if(emptyFields[3])
                surveillant = "surveillant = " + fields[2] + ", ";
            else
                surveillant = "surveillant = " + fields[2];
        if(emptyFields[3])
            nb_lits = "nb_lits = " + fields[3];

        try {
            
            if("".equals(surveillant)&&"".equals(nb_lits))
                return "Remplissez au moins le champ Surveillant ou le nombre de lit pour qu'un changement soit possible."; 
            else
            {
                String request = "UPDATE chambre SET " + surveillant + nb_lits + " WHERE code_service = '" + fields[0] + "' AND no_chambre = " + fields[1] + ";";
                System.out.println(request);
                this.getUpdate(request);
                return "Mise à Jour Réussie!";
            }

        } catch (SQLException e) {

            return "Votre requête est erronnée, vérifiez vos entrées.";
        }

    }

}
