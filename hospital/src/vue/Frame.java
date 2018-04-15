/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

//on verra après celles dont on aura besoin et on limitera l'import des pacakges pour que ce soit moins lourd !
import model.*;
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
    public Frame(Search r)
    {
        this.r=r;
        employeeList = new PanelEmployeeList(r);
        roomsList = new PanelRoomsList(r);
        patientsList = new PanelPatientsList(r);
        this.setTitle("Hospital");
        this.setSize(300, 350);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(employeeList);
        this.setVisible(true); 
    }  
}
