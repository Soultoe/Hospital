/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import hospital.*;
import model.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author romain
 */
public class PanelAjout extends JPanel{
    
    private JFormattedTextField req;
    private final JButton bip;
    private Add add;
    
    public PanelAjout(Connexion con)
    {
        super();
        add = new Add(con);
        req = new JFormattedTextField();
        req.setPreferredSize(new Dimension(150, 30));
        this.add(req);
        
        bip = new JButton("Add");
        bip.addActionListener((ActionEvent event) -> {
            System.out.println(req.getText());
            try {
                add.getRequete(req.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PanelAjout.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        this.add(bip);
        
    }
    
}
