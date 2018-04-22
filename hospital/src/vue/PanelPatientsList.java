/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author User
 */
public class PanelPatientsList extends JPanel {

    private final int fieldsNumber = 7;
    private final Search r;

    //champs d'entrée de texte
    private final JFormattedTextField tab[] = new JFormattedTextField[fieldsNumber];
    private final TextPrompt tp[] = new TextPrompt[fieldsNumber];
    private final String txt[] = {"Nom", "Prénom", "Mutuelle", "Code secteur", "n° chambre", "n° lit", "Docteur"};

    //bouton pour demander la requête
    private final JButton b = new JButton("Chercher");

    //Table for results
    private JTable tableau;
    JPanel result = new JPanel();

    /**
     *
     * @param r
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public PanelPatientsList(Search r) throws SQLException, ClassNotFoundException {
        this.setBackground(Color.LIGHT_GRAY);
        this.r = r;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        b.addActionListener(new BoutonListener());

        JPanel form = new JPanel();
        GridLayout g = new GridLayout(5, 2);
        //g.setHgap(60); g.setVgap(100);
        form.setLayout(g);
        form.setBorder(new EmptyBorder(50, 30, 30, 30));
        form.setBackground(Color.LIGHT_GRAY);

        //initialisation des cases d'entrée de texte
        for (int i = 0; i < tab.length; i++) {
            tab[i] = new JFormattedTextField();
            tab[i].setPreferredSize(new Dimension(150, 30));
            form.add(tab[i]);

            //là c'est pour qu'on écrive un texte préféfini dedans
            tp[i] = new TextPrompt(txt[i], tab[i]);
            tp[i].setForeground(Color.GRAY);
            tp[i].changeStyle(Font.ITALIC);
        }

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
        p1.add(form);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
        p2.setPreferredSize(new Dimension(100, 300));
        p2.add(new JScrollPane(result));

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));
        p3.add(b);

        this.add(p1);
        this.add(p3);
        this.add(p2);

    }

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
                tableau = new JTable(r.SearchPatient(tab[0].getText(), tab[1].getText(), tab[2].getText(), tab[3].getText(), tab[4].getText(), tab[5].getText(), tab[6].getText()), r.tableColumnsName());
            } catch (SQLException ex) {
                Logger.getLogger(PanelEmployeeList.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(PanelEmployeeList.class.getName()).log(Level.SEVERE, null, ex);
            }

            tableau.setRowHeight(20);
            tableau.setAutoCreateRowSorter(true);

            //on l'affiche en l'ajoutant au panel
            result.setLayout(new BorderLayout());
            result.add(tableau.getTableHeader(), BorderLayout.NORTH);
            result.add(tableau);

            result.revalidate();
            result.repaint();

        }
    }
}
