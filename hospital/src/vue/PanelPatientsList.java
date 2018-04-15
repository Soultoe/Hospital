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
public class PanelPatientsList extends JPanel{
    private final int fieldsNumber=7;
    private final Search r;

    //champs d'entrée de texte
    private final JFormattedTextField tab[]=new JFormattedTextField[fieldsNumber];
    private final TextPrompt tp[] = new TextPrompt[fieldsNumber];
    private final String txt[]={"Nom", "Prénom", "Mutuelle", "Code secteur", "n° chambre", "n° lit", "Docteur"};

    //bouton pour demander la requête
    private final JButton b = new JButton ("Chercher");
   
  
    public PanelPatientsList(Search r)
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
     
        this.add(b); 
       
    }
    
    
    class BoutonListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e)
      {
          try {
                r.SearchPatient(tab[0].getText(), tab[1].getText(), tab[2].getText(), tab[3].getText(), tab[4].getText(),tab[5].getText(),tab[6].getText());
          } catch (SQLException ex) {
              Logger.getLogger(PanelEmployeeList.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(PanelEmployeeList.class.getName()).log(Level.SEVERE, null, ex);
          } 
          
      }
  }
}
