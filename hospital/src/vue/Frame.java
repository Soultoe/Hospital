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
    public Frame(Search r)
    {
        this.r=r;
        employeeList = new PanelEmployeeList(r);
        roomsList = new PanelRoomsList(r);
        this.setTitle("Hospital");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setContentPane(roomsList);
        this.setVisible(true); 
    }  
}
