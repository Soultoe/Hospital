/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;
import hospital.Connexion;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import model.*;
import org.jfree.chart.*;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYBarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author User
 */
public class ReportingPatient extends JPanel{
    private final Reporting r;
    private Connexion con;

    //bouton pour demander la requête
    private final String txt_boutons[] = {"Effectif","Hospitalisés","Par spécialité"};
    
    private final JButton but[] = new JButton[3];

    
    //Table for results
    private JTable tableau;
    JPanel result = new JPanel();
    
    //charts
    private JFreeChart barChart;
    private ChartPanel chartPanel;
    
    
    public ReportingPatient(Connexion con) throws SQLException, ClassNotFoundException
    {
        
        this.con=con;
        r = new Reporting(con);
        
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setBackground(Color.LIGHT_GRAY);

        tableau = new JTable(r.reportingPatient(),r.tableColumnsName());
        tableau.setAutoCreateRowSorter(true);

        result.setLayout(new BorderLayout());
        
        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1,BoxLayout.PAGE_AXIS));
        p1.add(tableau.getTableHeader());
        p1.add(tableau);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2,BoxLayout.LINE_AXIS));
        p2.add(new JScrollPane(result));
        
        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3,BoxLayout.LINE_AXIS));
        for(int i=0;i<but.length;i++)
        {
            but[i]=new JButton(txt_boutons[i]);
            but[i].addActionListener(new BoutonListener());
            p3.add(but[i]);
        }
       
        
        this.add(p1);
        this.add(p3);
        this.add(p2);  
       
    }
    
   public ChartPanel Division() throws SQLException, ClassNotFoundException
    {
      DefaultPieDataset dataset=new DefaultPieDataset();
        
            dataset.setValue("Malades", Double.parseDouble(r.reportingPatient()[0][3]));                
            dataset.setValue("Hospitalisés", Double.parseDouble(r.reportingPatient()[0][4]));
        
         barChart = ChartFactory.createPieChart(
         "Pourcentage d'hospitalisés ou non",                      
         dataset,                    
         true, true, false);
        
        chartPanel = new ChartPanel( barChart );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 400, 400 ) );        
        return chartPanel; 
    }
    
    public ChartPanel Hospitalized()  throws SQLException, ClassNotFoundException
    {
        DefaultPieDataset dataset=new DefaultPieDataset();
        
        for(int i=0;i<r.reportingPatient().length;i++)
        {
            dataset.setValue(r.reportingPatient()[i][0], Double.parseDouble(r.reportingPatient()[i][2]));           
        }

         barChart = ChartFactory.createPieChart(
         "Pourcentage répartition hospitalisés",                      
         dataset,                    
         true, true, false);
        
        chartPanel = new ChartPanel( barChart );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 400, 400 ) );        
        return chartPanel; 
    }
    
    public ChartPanel doctorSpeciality() throws SQLException, ClassNotFoundException
    {
       DefaultPieDataset dataset=new DefaultPieDataset();
        
       for(int i=0;i<r.reportingDoctor().length;i++)
       {
           dataset.setValue(r.reportingDoctor()[i][0], Double.parseDouble(r.reportingDoctor()[i][2]));  
       }
                          
            
        
         barChart = ChartFactory.createPieChart(
         "Répartion des patients par spé",                      
         dataset,                    
         true, true, false);
        
        chartPanel = new ChartPanel( barChart );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 400, 400 ) );        
        return chartPanel; 
    }
    
    
    class BoutonListener implements ActionListener{
      @Override
      public void actionPerformed(ActionEvent e)
      {
        //on supprimme tout
        result.removeAll();
        result.validate();
          try {
              if(e.getSource()==but[0])
              {
                  result.add(Division());
              }
              else if(e.getSource()==but[1])
              {
                  result.add(Hospitalized());
              }
              else if(e.getSource()==but[2])
              {
                  result.add(doctorSpeciality());
              }
              
          } catch (SQLException ex) {
              Logger.getLogger(ReportingEmployee.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(ReportingEmployee.class.getName()).log(Level.SEVERE, null, ex);
          }
        result.revalidate();
        result.repaint();
          
      }
  }
    
   
}
