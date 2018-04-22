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
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author User
 */
public class ReportingEmployee extends JPanel{
    private final Reporting r;
    private Connexion con;

    //bouton pour demander la requête
    private final String txt_boutons[] = {"Effectif","Patients et infirmiers","Salaire moyen"};
    
    private final JButton but[] = new JButton[3];

    
    //Table for results
    private JTable tableau;
    JPanel result = new JPanel();
    
    //charts
    private JFreeChart barChart;
    private ChartPanel chartPanel;
    
    
    public ReportingEmployee(Connexion con) throws SQLException, ClassNotFoundException
    {
        
        this.con=con;
        r = new Reporting(con);
        
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setBackground(Color.LIGHT_GRAY);

        tableau = new JTable(r.reportingNurse(),r.tableColumnsName());
        tableau.setAutoCreateRowSorter(true);

        
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
    
   public ChartPanel averageSalary() throws SQLException, ClassNotFoundException
    {
       DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        
            for(int j=0;j<r.reportingNurse().length;j++)
            {
                dataset.addValue( Double.parseDouble(r.reportingNurse()[j][3]) , r.reportingNurse()[j][0], r.reportingNurse()[j][0]); 
                
            }
        
         barChart = ChartFactory.createBarChart(
         "Salaire moyen d'un infirmier par service",           
         "Secteur",            
         "Salaire moyen",            
         dataset,          
         PlotOrientation.VERTICAL,           
         true, true, false);
        
        chartPanel = new ChartPanel( barChart );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 400 , 400 ) );        
        return chartPanel; 
    }
    
    public ChartPanel effectif()  throws SQLException, ClassNotFoundException
    {
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        for(int i=0;i<r.dayWorkforce().length;i++)
        {
            dataset.addValue(Double.parseDouble(r.dayWorkforce()[i][2]),r.dayWorkforce()[i][1], r.dayWorkforce()[i][0]);
            dataset.addValue(Double.parseDouble(r.nightWorkforce()[i][2]),r.nightWorkforce()[i][1], r.nightWorkforce()[i][0]);
        }
        
        barChart = ChartFactory.createStackedBarChart(
            "Effectif d'infirmiers par service",  // chart title
            "Service",                  // domain axis label
            "Effectif total",                     // range axis label
            dataset,                     // data
            PlotOrientation.VERTICAL,    // the plot orientation
            true,                        // legend
            true,                        // tooltips
            false                        // urls
        );
        
        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        return chartPanel;
    }
    
    public ChartPanel effectifPatients() throws SQLException, ClassNotFoundException
    {
       DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        
            for(int j=0;j<r.reportingNurse().length;j++)
            {
                dataset.addValue( Integer.parseInt(r.reportingNurse()[j][2]) , "patient", r.reportingNurse()[j][0]);
                dataset.addValue( Integer.parseInt(r.reportingNurse()[j][1]) , "infirmier", r.reportingNurse()[j][0]);
                
            }
        
         barChart = ChartFactory.createBarChart(
         "Nombre de patients et d'infirmiers",           
         "Service",            
         "Effectif",            
         dataset,          
         PlotOrientation.VERTICAL,           
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
                  result.add(effectif());
              }
              else if(e.getSource()==but[1])
              {
                  result.add(effectifPatients());
              }
              else if(e.getSource()==but[2])
              {
                  result.add(averageSalary());
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
