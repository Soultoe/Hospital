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
public class PanelAddAndUpdateDepartment extends JPanel{
    
    private JPanel form;
    private JButton send;
    private JTextField results;
    private JCheckBox update,add;
    
    private JFormattedTextField[] commons;
    private String[] commonNames = {"code","name","building","director"};
    private TextPrompt[] tp = new TextPrompt[commonNames.length];
    
    public PanelAddAndUpdateDepartment()
    {
        results = new JTextField();
        results.setPreferredSize(new Dimension(100,300));
        form = new JPanel();
        form.setBorder(new EmptyBorder(50,30,30,30));
        form.setBackground(Color.LIGHT_GRAY);
        GridLayout g = new GridLayout(2,2);
        //g.setHgap(60); g.setVgap(100);
        form.setLayout(g);
        send = new JButton("Send");
        add = new JCheckBox("add");
        update = new JCheckBox("update");
        
        commons = new JFormattedTextField[4];
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
    
}
