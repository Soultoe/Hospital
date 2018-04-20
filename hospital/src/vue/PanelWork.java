/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import hospital.Connexion;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author romain
 */
public class PanelWork extends JPanel{
    
    //this panel will have a gridLayout of 1,2
    
    //Tab Panels contained in the grid
    private JTabbedPane search;
    private JTabbedPane addAndUpdate;
    
    //in the tab Panels
    private SearchTab[] searchTabs = new SearchTab[3];
    private AddAndUpdateTab addAndUpdateTab;
    private ReportingTab reportingTab;
    
    public PanelWork(Connexion con)
    {
        //TabbedPane init
        addAndUpdate = new JTabbedPane();
        search = new JTabbedPane();
        
        //searchTabs init
        for(int i=0;i<searchTabs.length;i++)
            searchTabs[i] = new SearchTab(i,con);
        
        //add and update tab init
        //will also become an array in due time
        addAndUpdateTab = new AddAndUpdateTab(con);
        
        //reporting Tab init
        reportingTab = new ReportingTab();
        
        //LAYOUT AND EMBRICATING PART
        
        search.addTab("Employees",searchTabs[0]);
        search.addTab("Patients",searchTabs[1]);
        search.addTab("Rooms",searchTabs[2]);
        
        addAndUpdate.addTab("provisoire",addAndUpdateTab);
        
        this.setLayout(new GridLayout(1,2));
        this.add(search);
        this.add(addAndUpdate);
        
    }
    
}
