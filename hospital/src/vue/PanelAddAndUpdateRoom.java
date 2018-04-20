/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author romain
 */
public class PanelAddAndUpdateRoom extends JPanel{
    
    private JPanel form;
    private JButton send;
    private JTextField results;
    
    public PanelAddAndUpdateRoom()
    {
        results = new JTextField();
        results.setPreferredSize(new Dimension(100,300));
        form = new JPanel();
        send = new JButton("Send");
        
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.LINE_AXIS));
        p1.add(form);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
        p2.add(results);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3,BoxLayout.LINE_AXIS));
        p3.add(send);
        
        this.add(p1);
        this.add(p3);
        this.add(p2);
    }
    
}
