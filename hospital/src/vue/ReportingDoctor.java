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
public class ReportingDoctor extends JPanel {

    private final Reporting r;
    private final Connexion con;

    //bouton pour demander la requête
    private final String txt_boutons[] = {"Effectif", "Répartition employés", "Malades/doc"};

    private final JButton but[] = new JButton[txt_boutons.length];

    //Table for results
    private final JTable tableau;
    JPanel result = new JPanel();

    //charts
    private JFreeChart barChart;
    private ChartPanel chartPanel;

    /**
     *
     * @param con
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ReportingDoctor(Connexion con) throws SQLException, ClassNotFoundException {
        this.con = con;
        r = new Reporting(con);

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        this.setBackground(Color.LIGHT_GRAY);

        tableau = new JTable(r.reportingDoctor(), r.tableColumnsName());
        tableau.setAutoCreateRowSorter(true);

        result.setLayout(new BorderLayout());

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.PAGE_AXIS));
        p1.add(tableau.getTableHeader());
        p1.add(tableau);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
        p2.add(new JScrollPane(result));

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));
        for (int i = 0; i < but.length; i++) {
            but[i] = new JButton(txt_boutons[i]);
            but[i].addActionListener(new BoutonListener());
            p3.add(but[i]);
        }

        this.add(p1);
        this.add(p3);
        this.add(p2);

    }

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public ChartPanel doctorWorkforce() throws SQLException, ClassNotFoundException {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (int i = 0; i < r.reportingDoctor().length; i++) {
            dataset.setValue(r.reportingDoctor()[i][0], Double.parseDouble(r.reportingDoctor()[i][1]));
        }

        barChart = ChartFactory.createPieChart(
                "Pourcentage répartition docteurs",
                dataset,
                true, true, false);

        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        return chartPanel;
    }

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public ChartPanel nbPatientsperDoc() throws SQLException, ClassNotFoundException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int j = 0; j < r.reportingDoctor().length; j++) {
            dataset.addValue(Double.parseDouble(r.reportingDoctor()[j][3]), r.reportingDoctor()[j][0], r.reportingDoctor()[j][0]);

        }

        barChart = ChartFactory.createBarChart(
                "Nombre de patients et de docteurs",
                "Spécialité",
                "Effectif",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        return chartPanel;
    }

    /**
     *
     * @return @throws SQLException
     * @throws ClassNotFoundException
     */
    public ChartPanel employeeWorkforce() throws SQLException, ClassNotFoundException {
        DefaultPieDataset dataset = new DefaultPieDataset();

        dataset.setValue("Docteurs", Double.parseDouble(r.reportingEmployee()[0][1]));
        dataset.setValue("Infirmiers", Double.parseDouble(r.reportingEmployee()[0][0]));

        barChart = ChartFactory.createPieChart(
                "Pourcentage répartition employés",
                dataset,
                true, true, false);

        chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
        return chartPanel;
    }

    /**
     *
     */
    class BoutonListener implements ActionListener {

        /**
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            //on supprimme tout
            result.removeAll();
            result.validate();

            try {
                if (e.getSource() == but[0]) {
                    result.add(doctorWorkforce());
                } else if (e.getSource() == but[1]) {
                    result.add(employeeWorkforce());
                } else if (e.getSource() == but[2]) {
                    result.add(nbPatientsperDoc());
                }

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ReportingDoctor.class.getName()).log(Level.SEVERE, null, ex);
            }
            result.revalidate();
            result.repaint();

        }
    }
}
