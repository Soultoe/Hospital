/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

//on verra après celles dont on aura besoin et on limitera l'import des pacakges pour que ce soit moins lourd !
import model.*;
import hospital.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
/**
 *
 * @author User
 */
public class Frame extends JFrame {
    
    //Container of tabs
    private JTabbedPane connexionType;
    
    //JPanels contained in the tabs
    private PanelRoot local;
    private PanelRoot distant;
    
    /**
     * 
     * @param localCon
     * @param distantCon
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public Frame(Connexion localCon, Connexion distantCon) throws SQLException, ClassNotFoundException
    {
        //window setup
        super();
        this.setTitle("Hospital");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.LIGHT_GRAY);
        
        //setup of main tabs --> local and distant connexion
        connexionType = new JTabbedPane();
        
        //setup of root panes --> HERE WE SHALL MAKE THE DIFFERENCE BETWEEN LOCAL AND DISTANT CONNEXION? BY PASSING BOTH IN PARAMETERS
        local = new PanelRoot(localCon);
        distant = new PanelRoot(distantCon);
        
        //setup of the tabs
        connexionType.addTab("Local",local);
        connexionType.addTab("Distant",distant);
        
        //finalisation
        this.setContentPane(connexionType);
        this.setVisible(true);
    }  
}
