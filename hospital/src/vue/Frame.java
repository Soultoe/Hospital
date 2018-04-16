/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

//on verra après celles dont on aura besoin et on limitera l'import des pacakges pour que ce soit moins lourd !
import model.*;
import hospital.*;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author User
 */
public class Frame extends JFrame {
    private Search r;
    private PanelEmployeeList employeeList;
    private PanelRoomsList roomsList;
    private PanelPatientsList patientsList;
    private JPanel container;
    private PanelAjout add;
    public Frame(Search r, Connexion con)
    {
        //setup de la fenetre
        this.setTitle("Hospital");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.r=r;
        
        //setup du conteneur global
        container = new JPanel();
        container.setLayout(new GridLayout(1,2));
        
        //setup des conteneurs de Recherche
        employeeList = new PanelEmployeeList(r);
        roomsList = new PanelRoomsList(r);
        patientsList = new PanelPatientsList(r);
        
        //setup des conteneurs d'ajout/modification
        add = new PanelAjout(con);
        
        //imbrication des conteneurs
        container.add(employeeList);
        container.add(add);
        
        
        //finalisation
        this.setContentPane(container);
        this.setVisible(true); 
    }  
}
