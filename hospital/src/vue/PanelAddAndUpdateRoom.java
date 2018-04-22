/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import hospital.Connexion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Add;
import model.Update;

/**
 *
 * @author romain
 */
public class PanelAddAndUpdateRoom extends JPanel {

    //Contents of the panel and their content 
    private final JPanel form;
    private final JButton send;
    private final JTextField results;
    private JCheckBox update, add;
    private final String[] fields;

    //text fields fotr the user
    private final JFormattedTextField[] commons;
    private final String[] commonNames = {"dept code", "number", "warden", "bed number"};
    private final TextPrompt[] tp = new TextPrompt[commonNames.length];

    private final Update updateObj;
    private final Add addObj;

    /**
     *
     * @param con
     */
    public PanelAddAndUpdateRoom(Connexion con) {
        
        //inti of objects
        updateObj = new Update(con);
        addObj = new Add(con);

        results = new JTextField();
        results.setPreferredSize(new Dimension(100, 300));
        form = new JPanel();
        form.setBorder(new EmptyBorder(50, 30, 30, 30));
        form.setBackground(Color.LIGHT_GRAY);
        GridLayout g = new GridLayout(2, 2);
        //g.setHgap(60); g.setVgap(100);
        form.setLayout(g);
        send = new JButton("Send");
        send.addActionListener((ActionEvent event) -> {
            try {
                this.sendRequest();
            } catch (SQLException ex) {
                Logger.getLogger(PanelAddAndUpdateEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        add = new JCheckBox("add");
        add.addActionListener((ActionEvent event) -> {
            if (update.isSelected()) {
                update.setSelected(false);
            }
            if (!update.isSelected() && !add.isSelected()) {
                add.setSelected(true);
            }
        });

        update = new JCheckBox("update");
        update.addActionListener((ActionEvent event) -> {
            if (add.isSelected()) {
                add.setSelected(false);
            }
            if (!update.isSelected() && !add.isSelected()) {
                add.setSelected(true);
            }
        });

        commons = new JFormattedTextField[4];
        fields = new String[commons.length];
        for (int i = 0; i < commons.length; i++) {
            commons[i] = new JFormattedTextField();
            commons[i].setPreferredSize(new Dimension(150, 30));
            //commons[i].setText(commonNames[i]);
            commons[i].setBackground(Color.white);

            tp[i] = new TextPrompt(commonNames[i], commons[i]);
            tp[i].setForeground(Color.GRAY);
            tp[i].changeStyle(Font.ITALIC);

            form.add(commons[i]);

        }

        //creation of layout
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JPanel p1 = new JPanel();
        p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
        p1.add(form);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
        p2.add(results);

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));
        p3.add(send);

        JPanel p4 = new JPanel();
        p4.setLayout(new BoxLayout(p4, BoxLayout.LINE_AXIS));
        p4.add(add);
        p4.add(update);

        this.add(p4);
        this.add(p1);
        this.add(p3);
        this.add(p2);
    }

    /**
     *
     * @throws SQLException
     */
    public void sendRequest() throws SQLException {
        for (int i = 0; i < fields.length; i++) {
            fields[i] = commons[i].getText();
        }

        if (add.isSelected()) {
            results.setText(addObj.buildRequestRoom(fields));
        } else if (update.isSelected()) {
            results.setText(updateObj.buildRequestRoom(fields));
        }

    }

}
