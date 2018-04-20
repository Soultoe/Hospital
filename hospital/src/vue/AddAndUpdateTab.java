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
    
    //will be a box Layout
    
    //will contain
    private PanelAddAndUpdate addAndUpdate;
    private JButton send;
    private JTextField resultsField;
    
    public AddAndUpdateTab(Connexion con)
    {
        this.setBackground(Color.LIGHT_GRAY);
        
        addAndUpdate = new PanelAddAndUpdate();
        send = new JButton("send");
        resultsField = new JTextField();
    }
    
}
