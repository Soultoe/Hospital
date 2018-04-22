/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import hospital.Connexion;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Romain
 */
public class PanelWork extends JPanel {

    //Tab Panels contained in the grid
    private final JTabbedPane search;
    private final JTabbedPane addAndUpdate;

    //in the tab Panels
    private final SearchTab[] searchTabs = new SearchTab[3];
    private final AddAndUpdateTab[] addAndUpdateTabs = new AddAndUpdateTab[5];
    private final ReportingTab reportingTab;
    private final DeleteTab deleteTab;

    /**
     *
     * @param con
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public PanelWork(Connexion con) throws SQLException, ClassNotFoundException {
        
        this.setBackground(Color.LIGHT_GRAY);

        //TabbedPane init
        addAndUpdate = new JTabbedPane();
        search = new JTabbedPane();

        //searchTabs init
        for (int i = 0; i < searchTabs.length; i++) {
            searchTabs[i] = new SearchTab(i, con);
        }

        //add and update tab init
        //will also become an array in due time
        for (int i = 0; i < addAndUpdateTabs.length; i++) {
            addAndUpdateTabs[i] = new AddAndUpdateTab(con, i);
        }

        //reporting Tab init
        reportingTab = new ReportingTab(con);
        
        //Delete Tab init
        deleteTab = new DeleteTab(con);

        //LAYOUT AND EMBRICATING PART
        search.addTab("Employees", searchTabs[0]);
        search.addTab("Patients", searchTabs[1]);
        search.addTab("Rooms", searchTabs[2]);
        search.addTab("Reporting", reportingTab);

        addAndUpdate.addTab("Doctor", addAndUpdateTabs[0]);
        addAndUpdate.addTab("Nurse", addAndUpdateTabs[1]);
        addAndUpdate.addTab("Patient", addAndUpdateTabs[2]);
        addAndUpdate.addTab("Department", addAndUpdateTabs[3]);
        addAndUpdate.addTab("Room", addAndUpdateTabs[4]);
        addAndUpdate.addTab("Delete", deleteTab);

        this.setLayout(new GridLayout(1, 2));
        this.add(search);
        this.add(addAndUpdate);

    }

}
