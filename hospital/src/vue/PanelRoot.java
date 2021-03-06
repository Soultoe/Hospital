/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import hospital.Connexion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author romain
 */
public class PanelRoot extends JPanel {

    private final PanelWork workPane;
    private final JButton quit;

    /**
     *
     * @param con
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public PanelRoot(Connexion con) throws SQLException, ClassNotFoundException {
        this.setBackground(Color.LIGHT_GRAY);

        workPane = new PanelWork(con);
        quit = new JButton("Quit");
        quit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        this.setLayout(new BorderLayout());
        this.add(workPane, BorderLayout.CENTER);
        this.add(quit, BorderLayout.SOUTH);
    }
}
