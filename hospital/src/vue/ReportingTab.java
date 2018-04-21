/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author romain
 */
public class ReportingTab extends JPanel{
    
    private JTabbedPane types;
    private JPanel provisoire1,provisoire2,provisoire3;
    
    public ReportingTab()
    {
        provisoire1 = new JPanel();
        provisoire1.setBackground(Color.GREEN);
        
        provisoire2 = new JPanel();
        provisoire2.setBackground(Color.RED);
        
        provisoire3 = new JPanel();
        provisoire3.setBackground(Color.BLUE);
        
        types = new JTabbedPane();
        
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridLayout(1,1));
        
        types.addTab("onglet1",provisoire1);
        types.addTab("onglet2",provisoire2);
        types.addTab("onglet3",provisoire3);
        
        this.add(types);
        
        //...
    }
    
}
