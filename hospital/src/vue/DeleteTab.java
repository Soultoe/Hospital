/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import model.Delete;
import hospital.Connexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author romain
 */
public class DeleteTab extends JPanel{
    
    private JLabel employee,patient;
    private JCheckBox doc,nurse;
    private JFormattedTextField employeeNum, patientNum;
    private JButton sendEmployee, sendPatient;
    
    private Delete delete;
    
    public DeleteTab(Connexion con)
    {
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        
        employee = new JLabel("Delete an employee");
        employee.setBackground(Color.LIGHT_GRAY);
        patient = new JLabel("Delete a Patient");
        patient.setBackground(Color.LIGHT_GRAY);
        
        doc = new JCheckBox("doc");
        doc.setBackground(Color.LIGHT_GRAY);
        doc.addActionListener((ActionEvent event) -> {
            if(nurse.isSelected())
                nurse.setSelected(false);
            if(!nurse.isSelected()&&!doc.isSelected())
                doc.setSelected(true);
        });
        
        nurse = new JCheckBox("nurse");
        nurse.setBackground(Color.LIGHT_GRAY);
        nurse.addActionListener((ActionEvent event) -> {
            if(doc.isSelected())
                doc.setSelected(false);
            if(!nurse.isSelected()&&!doc.isSelected())
                doc.setSelected(true);
        });
        
        employeeNum = new JFormattedTextField();
        employeeNum.setPreferredSize(new Dimension(150, 30));
        patientNum = new JFormattedTextField();
        patientNum.setPreferredSize(new Dimension(150, 30));
        
        sendEmployee = new JButton("delete");
        sendEmployee.addActionListener((ActionEvent event) -> {
            try {
                this.sendRequestEmp();
            } catch (SQLException ex) {
                Logger.getLogger(DeleteTab.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        sendPatient = new JButton("delete");
        sendPatient.addActionListener((ActionEvent event) -> {
            try {
                this.sendRequestPatient();
            } catch (SQLException ex) {
                Logger.getLogger(DeleteTab.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        delete = new Delete(con);
        
        
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.LINE_AXIS));
        p1.add(doc);
        p1.add(nurse);
        
        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
        p2.add(employeeNum);
        
        JPanel p6 = new JPanel();
        p6.setLayout(new BoxLayout(p6,BoxLayout.LINE_AXIS));
        p6.add(sendEmployee);
        
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3,BoxLayout.LINE_AXIS));
        p3.add(patientNum);
        
        JPanel p7 = new JPanel();
        p7.setLayout(new BoxLayout(p7,BoxLayout.LINE_AXIS));
        p7.add(sendPatient);
        
        JPanel p4 = new JPanel();
        p4.setLayout(new BoxLayout(p4,BoxLayout.LINE_AXIS));
        p4.add(employee);
        
        JPanel p5 = new JPanel();
        p5.setLayout(new BoxLayout(p5,BoxLayout.LINE_AXIS));
        p5.add(patient);
        
        this.add(p4);
        this.add(p1);
        this.add(p2);
        this.add(p6);
        this.add(p5);
        this.add(p3);
        this.add(p7);
    }
    
    public void sendRequestEmp() throws SQLException
    {
        try {
            if (doc.isSelected()) {
                delete.deleteEmployee(employeeNum.getText(), true);
            } else {
                delete.deleteEmployee(employeeNum.getText(), false);
            }
            
            employeeNum.setText("Deletion successful");
        } catch (SQLException e) {
            employeeNum.setText("Deletion failed");
        }

    }
    
    public void sendRequestPatient() throws SQLException
    {
        try
        {
        delete.deletePatient(patientNum.getText());
        patientNum.setText("Deletion successful");
        }
        catch(SQLException e)
        {
            patientNum.setText("Deletion failed");
        }
    }
    
}
