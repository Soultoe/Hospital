/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author User
 */
public class PanelEmployeeList extends JPanel{
    private final int fieldsNumber=7;
    private final Search r;

    //champs d'entrée de texte
    private final JFormattedTextField tab[]=new JFormattedTextField[fieldsNumber];
    private final TextPrompt tp[] = new TextPrompt[fieldsNumber];
    private final String txt[]={"Nom","Prénom","Spécialité du docteur","Patient du docteur","Département de l'infirmier", "Nombre de patients min", "Nombre de patients max"};
     
    //sélection de texte pour la rotation de l'infirmier
    private final String rot[]= {"","JOUR", "NUIT"};
    private final JComboBox rotation = new JComboBox(rot);
    
    //cases à cocher pour filtrer les employés
    private final JCheckBox doctor = new JCheckBox("Docteur");
    private final JCheckBox infirmier = new JCheckBox("Infirmier");
    
    //bouton pour demander la requête
    private final JButton b = new JButton ("Chercher");
    
    //TextField for results
    private JTextField results = new JTextField();
   
  
    public PanelEmployeeList(Search r)
    {
        this.setBackground(Color.LIGHT_GRAY);
        this.r=r;
        
        results.setPreferredSize(new Dimension(100,300));
        
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        b.addActionListener(new BoutonListener());
        
        //initialisation des cases d'entrée de texte
        JPanel form = new JPanel();
        GridLayout g = new GridLayout(5,2);
        //g.setHgap(60); g.setVgap(100);
        form.setLayout(g);
        form.setBorder(new EmptyBorder(50,30,30,30));
        form.setBackground(Color.LIGHT_GRAY);
        
        for(int i=0;i<tab.length;i++)
        {
            tab[i]= new JFormattedTextField();
            tab[i].setPreferredSize(new Dimension(150, 30));
            form.add(tab[i]);
            
            //là c'est pour qu'on écrive un texte préféfini dedans
            tp[i]=new TextPrompt(txt[i], tab[i]);
            tp[i].setForeground(Color.GRAY );
            tp[i].changeStyle(Font.ITALIC);
        }
        
        form.add(rotation);
        form.add(doctor);
        form.add(infirmier);
        
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.LINE_AXIS));
        p1.add(form);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
        p2.add(results);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3,BoxLayout.LINE_AXIS));
        p3.add(b);
        
        this.add(p1);
        this.add(p3);
        this.add(p2); 
       
    }
    
    
    class BoutonListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e)
      {
          try {
                if((infirmier.isSelected()==true)&&(doctor.isSelected()==true))
                {
                    r.SearchEmployee(tab[4].getText(),(String)rotation.getSelectedItem(),tab[2].getText(),tab[3].getText(),tab[1].getText(),tab[0].getText(),tab[5].getText(),tab[6].getText());
                }
                else if(infirmier.isSelected()==true)
                {
                    r.SearchNurse(tab[4].getText(),(String)rotation.getSelectedItem(),tab[1].getText(),tab[0].getText());
                }
                else if(doctor.isSelected()==true)
                {
                    r.SearchDoctor(tab[2].getText(),tab[3].getText(),tab[1].getText(),tab[0].getText(),tab[5].getText(),tab[6].getText());
                }  
                else
                {
                   System.out.println("Rien à afficher"); 
                }
          } catch (SQLException | ClassNotFoundException ex) {
              Logger.getLogger(PanelEmployeeList.class.getName()).log(Level.SEVERE, null, ex);
          } 
          
      }
  }
}
