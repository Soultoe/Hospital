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
import model.Reporting;

/**
 *
 * @author romain
 */
public class ReportingTab extends JPanel{
    
    private Connexion con;
    private JTabbedPane types;
    private JPanel provisoire2,provisoire3;
    private ReportingEmployee reportingEmployee;
    private ReportingDoctor reportingDoctor;
    private ReportingRoom reportingRoom;
    
    public ReportingTab(Connexion con) throws SQLException, ClassNotFoundException
    {
        this.con = con;
        reportingEmployee = new ReportingEmployee(con);
        reportingDoctor = new ReportingDoctor(con);
        reportingRoom = new ReportingRoom(con);
        
        types = new JTabbedPane();
        
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridLayout(1,1));
        
        types.addTab("Infirmiers",reportingEmployee);
        types.addTab("Docteurs",reportingDoctor);
        types.addTab("Chambres",reportingRoom);
        
        this.add(types);
        
        //...
    }
    
}
