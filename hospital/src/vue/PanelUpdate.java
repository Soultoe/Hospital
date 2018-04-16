/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import hospital.Connexion;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import model.Update;

/**
 *
 * @author romain
 */
public class PanelUpdate extends JPanel{
    
    private JFormattedTextField req;
    private final JButton bip;
    private Update up;
    
    public PanelUpdate(Connexion con)
    {
        super();
        up = new Update(con);
        req = new JFormattedTextField();
        req.setPreferredSize(new Dimension(150, 30));
        this.add(req);
        
        bip = new JButton("Update");
        bip.addActionListener((ActionEvent event) -> {
            System.out.println(req.getText());
            try {
                up.getUpdate(req.getText());
            } catch (SQLException ex) {
                Logger.getLogger(PanelAjout.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
        this.add(bip);
        
    }
    
}
