/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import hospital.Connexion;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author romain
 */
public class AddAndUpdateTab extends JPanel{
    
    private JPanel form;
    
    //will contain
    public AddAndUpdateTab(Connexion con, int type)
    {
        this.setBackground(Color.LIGHT_GRAY);
        
        switch(type)
        {
            case 0:
                form = new PanelAddAndUpdateEmployee(con,0);
                break;
                
            case 1:
                form = new PanelAddAndUpdateEmployee(con,1);
                break;
                
            case 2:
                form = new PanelAddAndUpdatePatients(con);
                break;
                
            case 3:
                form = new PanelAddAndUpdateDepartment(con);
                break;
                
            case 4:
                form = new PanelAddAndUpdateRoom(con);
                break;
        }
        
        this.add(form);
    }
    
}
