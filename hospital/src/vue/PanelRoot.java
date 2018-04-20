/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import hospital.Connexion;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author romain
 */
public class PanelRoot extends JPanel{
    
    private PanelWork workPane;
    private JButton quit;
    //will be a border layout
    
    public PanelRoot(Connexion con)
    {
        workPane = new PanelWork(con);
        quit = new JButton("quit");
        quit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });
        
        this.setLayout(new BorderLayout());
        this.add(workPane,BorderLayout.CENTER);
        this.add(quit,BorderLayout.SOUTH);
    }
}
