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
public class PanelAddAndUpdateEmployee extends JPanel{
    
    private JPanel form;
    private JButton send;
    private JTextField results;
    private JCheckBox update,add;
    
    private JFormattedTextField[] commons;
    private String[] commonNames = {"id","name","surname","address","phone"};
    private TextPrompt[] tp = new TextPrompt[commonNames.length];
    private JFormattedTextField[] specifics;
    private String[] specificNames = {"speciality","dept","rotation","wage"};
    private TextPrompt[]tp2 = new TextPrompt[specificNames.length];
    
    public PanelAddAndUpdateEmployee(int type)
    {
        results = new JTextField();
        results.setPreferredSize(new Dimension(100,300));
        form = new JPanel();
        form.setBorder(new EmptyBorder(50,30,30,30));
        form.setBackground(Color.LIGHT_GRAY);
        send = new JButton("Send");
        
        add = new JCheckBox("add");
        add.addActionListener((ActionEvent event) -> {
            if(update.isSelected())
                update.setSelected(false);
        });
        
        update = new JCheckBox("update");
        update.addActionListener((ActionEvent event) -> {
            if(add.isSelected())
                add.setSelected(false);
        });
        
        commons = new JFormattedTextField[5];
        for(int i=0;i<commons.length;i++){
            commons[i] = new JFormattedTextField();
            commons[i].setPreferredSize(new Dimension(150, 30));
            //commons[i].setText(commonNames[i]);
            commons[i].setBackground(Color.white);
            
            tp[i]=new TextPrompt(commonNames[i], commons[i]);
            tp[i].setForeground(Color.GRAY );
            tp[i].changeStyle(Font.ITALIC);
            
        }
        
        if(type==0){
            specifics = new JFormattedTextField[1];
            specifics[0] = new JFormattedTextField();
            specifics[0].setPreferredSize(new Dimension(150, 30));
            //specifics[0].setText(specificNames[0]);
            specifics[0].setBackground(Color.white);
            
            tp2[0]=new TextPrompt(specificNames[0], specifics[0]);
            tp2[0].setForeground(Color.GRAY );
            tp2[0].changeStyle(Font.ITALIC);
            
            GridLayout g = new GridLayout(4,2);
            //g.setHgap(60); g.setVgap(100);
            form.setLayout(g);
            
            for(int i=0;i<commons.length+1;i++)
            {
                if(i<commons.length)
                    
                    form.add(commons[i]);
                else
                    form.add(specifics[0]);
            }
            
        }
        else if(type==1){
            specifics = new JFormattedTextField[3];
            for(int i=0;i<specifics.length;i++)
            {
                specifics[i] = new JFormattedTextField();
                specifics[i].setPreferredSize(new Dimension(150, 30));
                //specifics[i].setText(specificNames[i+1]);
                specifics[i].setBackground(Color.white);
                
                tp2[i]=new TextPrompt(specificNames[i+1], specifics[i]);
                tp2[i].setForeground(Color.GRAY );
                tp2[i].changeStyle(Font.ITALIC);
            }
            GridLayout g = new GridLayout(4,2);
            //g.setHgap(60); g.setVgap(100);
            form.setLayout(g);
            
            for(int i=0;i<commons.length+specifics.length;i++)
            {
                if(i<commons.length)
                    form.add(commons[i]);
                else
                    form.add(specifics[i-commons.length]);
            }
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
        p3.setBackground(Color.LIGHT_GRAY);
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
