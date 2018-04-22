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
 * @author Anastasia
 */
public class ReportingTab extends JPanel{
    
    private final Connexion con;
    private final JTabbedPane types;
    private final ReportingEmployee reportingEmployee;
    private final ReportingDoctor reportingDoctor;
    private final ReportingRoom reportingRoom;
    private final ReportingPatient reportingPatient;
    
    /**
     * 
     * @param con
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ReportingTab(Connexion con) throws SQLException, ClassNotFoundException
    {
        this.con = con;
        reportingEmployee = new ReportingEmployee(con);
        reportingDoctor = new ReportingDoctor(con);
        reportingRoom = new ReportingRoom(con);
        reportingPatient = new ReportingPatient(con);
        
        types = new JTabbedPane();
        
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new GridLayout(1,1));
        
        types.addTab("Infirmiers",reportingEmployee);
        types.addTab("Docteurs",reportingDoctor);
        types.addTab("Chambres",reportingRoom);
        types.addTab("Patients", reportingPatient);
        this.add(types);
    }
    
}
