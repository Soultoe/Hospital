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
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author User
 */
public class ReportingRoom extends JPanel{
    private final Reporting r;
    private Connexion con;

    //bouton pour demander la requête
    private final String txt_boutons[] = {"Dispo chambres","Dispo lits","chambres & lits","Surveillants"};
    
    private final JButton but[] = new JButton[4];
    private final String service[]= {"Cardiologie","Chirurgie generale","Reanimation et Traumatologie"};
    private final JComboBox servicesRooms = new JComboBox(service);
    private final JComboBox servicesBeds = new JComboBox(service);
    
    //Table for results
    private JTable tableau;
    JPanel result = new JPanel();
    JPanel cam = new JPanel();
    
    //charts
    private JFreeChart barChart;
    private ChartPanel chartPanel;
    
    
    /**
     * 
     * @param con
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ReportingRoom(Connexion con) throws SQLException, ClassNotFoundException
    {
        
        this.con=con;
        r = new Reporting(con);
        
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.setBackground(Color.LIGHT_GRAY);

        tableau = new JTable(r.reportingRoom(),r.tableColumnsName());
        tableau.setAutoCreateRowSorter(true);
        
        servicesRooms.addActionListener(new StateListenerRooms());
        servicesBeds.addActionListener(new StateListenerBeds());
        
        cam.setLayout(new BorderLayout());
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
        
//        
               
        
        this.add(p1);
        this.add(p3);
//        
        this.add(p2);  
       
    }
    
    /**
     * 
     * @param service
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ChartPanel RoomsAvaibility(String service) throws SQLException, ClassNotFoundException       
    {
        DefaultPieDataset dataset=new DefaultPieDataset(); 
       
        //selon ce que retourne notre JComboBox on affiche le graphique qui lui correspond
        switch(service)
        {
            case "Cardiologie" :
                
                dataset.setValue("occupées", Double.parseDouble(r.roomsAndBedsAvaibility()[0][4]));
                dataset.setValue("libres", Double.parseDouble(r.roomsAndBedsAvaibility()[0][5]));
                break;
            case "Chirurgie generale" :
                dataset.setValue("occupées", Double.parseDouble(r.roomsAndBedsAvaibility()[1][4]));
                dataset.setValue("libres", Double.parseDouble(r.roomsAndBedsAvaibility()[1][5]));
                break;
            case "Reanimation et Traumatologie" :
                dataset.setValue("occupées", Double.parseDouble(r.roomsAndBedsAvaibility()[2][4]));
                dataset.setValue("libres", Double.parseDouble(r.roomsAndBedsAvaibility()[2][5]));
                break;
            default:break;
                
        }
        
        barChart = ChartFactory.createPieChart(
         "Pourcentage répartition surveillants par service",                      
         dataset,                    
         true, true, false);
        
        chartPanel = new ChartPanel( barChart );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 400, 400 ) );        
        return chartPanel; 
        
    }
    
    /**
     * 
     * @param service
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ChartPanel BedsAvaibility(String service) throws SQLException, ClassNotFoundException       
    {
        DefaultPieDataset dataset=new DefaultPieDataset(); 
       
        //selon ce que retourne notre JComboBox on affiche le graphique qui lui correspond
        switch(service)
        {
            case "Cardiologie" :
                
                dataset.setValue("occupées", Double.parseDouble(r.roomsAndBedsAvaibility()[0][6]));
                dataset.setValue("libres", Double.parseDouble(r.roomsAndBedsAvaibility()[0][7]));
                break;
            case "Chirurgie generale" :
                dataset.setValue("occupées", Double.parseDouble(r.roomsAndBedsAvaibility()[1][6]));
                dataset.setValue("libres", Double.parseDouble(r.roomsAndBedsAvaibility()[1][7]));
                break;
            case "Reanimation et Traumatologie" :
                dataset.setValue("occupées", Double.parseDouble(r.roomsAndBedsAvaibility()[2][6]));
                dataset.setValue("libres", Double.parseDouble(r.roomsAndBedsAvaibility()[2][7]));
                break;
            default:break;
                
        }
        
        barChart = ChartFactory.createPieChart(
         "Pourcentage de lis disponibles",                      
         dataset,                    
         true, true, false);
        
        chartPanel = new ChartPanel( barChart );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 400, 400 ) );        
        return chartPanel; 
        
    }

    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ChartPanel WardenDistribution() throws SQLException, ClassNotFoundException{
        
        DefaultPieDataset dataset=new DefaultPieDataset();
        
        for(int i=0;i<r.wardenDistribution().length;i++)
        {
             dataset.setValue(r.wardenDistribution()[i][1], Double.parseDouble(r.wardenDistribution()[i][2]));
        }
        
         barChart = ChartFactory.createPieChart(
         "Distribution des surveillants",                      
         dataset,                    
         true, true, false);
        
        chartPanel = new ChartPanel( barChart );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 400, 400 ) );        
        return chartPanel; 
    }
    
    /**
     * 
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public ChartPanel RoomsandBedsDistribution() throws SQLException, ClassNotFoundException
    {
       DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        
            for(int j=0;j<r.reportingRoom().length;j++)
            {
                dataset.addValue( Double.parseDouble(r.reportingRoom()[j][3]) , "Lits", r.reportingRoom()[j][1]); 
                dataset.addValue( Double.parseDouble(r.reportingRoom()[j][4]) , "Chambres", r.reportingRoom()[j][1]); 
                
            }
        
         barChart = ChartFactory.createBarChart(
         "Nombe de lits et de chambres",           
         "Service",            
         "Quantité",            
         dataset,          
         PlotOrientation.VERTICAL,           
         true, true, false);
        
        chartPanel = new ChartPanel( barChart );        
        chartPanel.setPreferredSize(new java.awt.Dimension( 400 , 400 ) );        
        return chartPanel;  
    }
    
    
    /**
     * 
     */
    class BoutonListener implements ActionListener{
      /**
       * 
       * @param e 
       */
        @Override
      public void actionPerformed(ActionEvent e)
      {
        //on supprimme tout
        result.removeAll();
        result.validate();
        
          try {
              
              if(e.getSource()==but[0])
              {
                  result.add(servicesRooms, BorderLayout.NORTH);
              }
              else if(e.getSource()==but[1])
              {
                  
                  result.add(servicesBeds, BorderLayout.NORTH);
              }
              else if(e.getSource()==but[2])
              {
                  result.add(RoomsandBedsDistribution());
              }
              else if(e.getSource()==but[3])
              {
                  result.add(WardenDistribution());
              }
          } catch (SQLException ex) {
              Logger.getLogger(ReportingRoom.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(ReportingRoom.class.getName()).log(Level.SEVERE, null, ex);
          }
        result.revalidate();
        result.repaint();
          
      }
  }
    
      class StateListenerRooms implements ActionListener{
      /**
       * 
       * @param e 
       */
          @Override
      public void actionPerformed(ActionEvent e)
      {
          
          cam.removeAll();
          cam.validate();
          try {
                  cam.add(RoomsAvaibility((String)servicesRooms.getSelectedItem()));
          } catch (SQLException ex) {
              Logger.getLogger(ReportingRoom.class.getName()).log(Level.SEVERE, null, ex);
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(ReportingRoom.class.getName()).log(Level.SEVERE, null, ex);
          }
           result.add(cam);
           cam.revalidate();
           cam.repaint();

      }
    }
      
    class StateListenerBeds implements ActionListener{
    /**
     * 
     * @param e 
     */
        @Override
    public void actionPerformed(ActionEvent e)
    {

        cam.removeAll();
        cam.validate();
        
        try {
                cam.add(BedsAvaibility((String)servicesBeds.getSelectedItem()));
        } catch (SQLException ex) {
            Logger.getLogger(ReportingRoom.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReportingRoom.class.getName()).log(Level.SEVERE, null, ex);
        }
         result.add(cam);
         cam.revalidate();
         cam.repaint();


    }
  }
    
   
}
