/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author romain
 */
public class PanelAddAndUpdatePatients extends JPanel{
    
    private JPanel form;
    private JButton send;
    private JTextField results;
    private JCheckBox update,add;
    
    private JFormattedTextField[] commons;
    private String[] commonNames = {"id","name","surname","address","phone","social security","assigned doctor","department","room","bed"};
    private TextPrompt[] tp = new TextPrompt[commonNames.length];
    
    public PanelAddAndUpdatePatients()
    {
        results = new JTextField();
        results.setPreferredSize(new Dimension(100,300));
        form = new JPanel();
        form.setBorder(new EmptyBorder(50,30,30,30));
        form.setBackground(Color.LIGHT_GRAY);
        send = new JButton("Send");
        send.addActionListener((ActionEvent event) -> {
            try {
                this.sendRequest();
            } catch (SQLException ex) {
                Logger.getLogger(PanelAddAndUpdateEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        add = new JCheckBox("add");
        add.addActionListener((ActionEvent event) -> {
            if(update.isSelected())
                update.setSelected(false);
            if(!update.isSelected()&&!add.isSelected())
                add.setSelected(true);
        });
        
        update = new JCheckBox("update");
        update.addActionListener((ActionEvent event) -> {
            if(add.isSelected())
                add.setSelected(false);
            if(!update.isSelected()&&!add.isSelected())
                add.setSelected(true);
        });
        
        GridLayout g = new GridLayout(5,2);
        //g.setHgap(60); g.setVgap(100);
        form.setLayout(g);
        
        commons = new JFormattedTextField[10];
        for(int i=0;i<commons.length;i++){
            commons[i] = new JFormattedTextField();
            commons[i].setPreferredSize(new Dimension(150, 30));
            //commons[i].setText(commonNames[i]);
            commons[i].setBackground(Color.white);
            
            tp[i]=new TextPrompt(commonNames[i], commons[i]);
            tp[i].setForeground(Color.GRAY );
            tp[i].changeStyle(Font.ITALIC);
            
            form.add(commons[i]);
            
        }
        
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
        
        
        JPanel p4 = new JPanel();
        p4.setLayout(new BoxLayout(p4,BoxLayout.LINE_AXIS));
        p4.add(add);
        p4.add(update);
        
        this.add(p4);
        this.add(p1);
        this.add(p3);
        this.add(p2);
    }
    
    public void sendRequest() throws SQLException
    {
        
    }
    
}
