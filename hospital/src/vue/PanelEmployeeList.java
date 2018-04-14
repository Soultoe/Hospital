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

/**
 *
 * @author User
 */
public class PanelEmployeeList extends JPanel{
    private final int fieldsNumber=5;
    private final Search r;

    //champs d'entrée de texte
    private final JFormattedTextField tab[]=new JFormattedTextField[fieldsNumber];
    private final TextPrompt tp[] = new TextPrompt[fieldsNumber];
    private final String txt[]={"Nom","Prénom","Spécialité du docteur","Patient du docteur","Département de l'infirmier"};
     
    //sélection de texte pour la rotation de l'infirmier
    private final String rot[]= {"","JOUR", "NUIT"};
    private final JComboBox rotation = new JComboBox(rot);
    
    //cases à cocher pour filtrer les employés
    private final JCheckBox doctor = new JCheckBox("Docteur");
    private final JCheckBox infirmier = new JCheckBox("Infirmier");
    
    //bouton pour demander la requête
    private final JButton b = new JButton ("Chercher");
   
  
    public PanelEmployeeList(Search r)
    {
        this.r=r;
        
        //this.setLayout(new BorderLayout());
        //this.setLayout(new GridLayout(3, 2));
        
        
        b.addActionListener(new BoutonListener());
        
        //initialisation des cases d'entrée de texte
        for(int i=0;i<tab.length;i++)
        {
            tab[i]= new JFormattedTextField();
            tab[i].setPreferredSize(new Dimension(150, 30));
            this.add(tab[i]);
            
            //là c'est pour qu'on écrive un texte préféfini dedans
            tp[i]=new TextPrompt(txt[i], tab[i]);
            tp[i].setForeground(Color.GRAY );
            tp[i].changeStyle(Font.ITALIC);
        }
        
        this.add(rotation);
        this.add(doctor);
        this.add(infirmier);
        this.add(b); 
       
    }
    
    
    class BoutonListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e)
      {
          try {
                if((infirmier.isSelected()==true)&&(doctor.isSelected()==true))
                {
                    r.SearchEmployee(tab[4].getText(),(String)rotation.getSelectedItem(),tab[2].getText(),tab[3].getText(),tab[1].getText(),tab[0].getText(),3);
                }
                else if(infirmier.isSelected()==true)
                {
                    r.SearchEmployee(tab[4].getText(),(String)rotation.getSelectedItem(),tab[2].getText(),tab[3].getText(),tab[1].getText(),tab[0].getText(),2);
                }
                else if(doctor.isSelected()==true)
                {
                    r.SearchEmployee(tab[4].getText(),(String)rotation.getSelectedItem(),tab[2].getText(),tab[3].getText(),tab[1].getText(),tab[0].getText(),1);
                }  
                else
                {
                   System.out.println("Rien à afficher"); 
                }
          } catch (SQLException ex) {
              Logger.getLogger(PanelEmployeeList.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(PanelEmployeeList.class.getName()).log(Level.SEVERE, null, ex);
          } 
          
      }
  }
}
