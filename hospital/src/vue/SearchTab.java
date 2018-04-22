/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import model.Search;
import hospital.Connexion;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.JPanel;

/**
 *
 * @author romain
 */
public class SearchTab extends JPanel{
    
    //contained in this JPanel
    private JPanel form; //will either be a PanelEmployeeList, or a PanelPatients, or a PanelRooms
    
    //non graphic objects needed
    private Search search;
    
    /**
     * 
     * @param type
     * @param con
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public SearchTab(int type, Connexion con) throws SQLException, ClassNotFoundException
    {
        this.setBackground(Color.LIGHT_GRAY);
        
        search = new Search(con);
        
        switch(type)
        {
            case 0:
                form = new PanelEmployeeList(search);
                break;
                
            case 1:
                form = new PanelPatientsList(search);
                break;
                
            case 2:
                form = new PanelRoomsList(search);
                break;
        }
        
        this.add(form);
        
    }
    
    
}
