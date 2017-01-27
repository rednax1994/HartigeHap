/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.loginSystem.presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import nl.infosys.hartigehap.loginSystem.businesslogic.ImmutableLoginManager;

/**
 *
 * @author Sander van Belleghem
 */
public class LoginGui {

    private final ImmutableLoginManager loginManager;
    private final JPanel panel = new JPanel();
    private final JPanel mainPanel = new JPanel(new BorderLayout());
    private int counter = 0;
    private ImageIcon[] image = new ImageIcon[25];
    private JPanel background = new JPanel();
    private final JLabel images = new JLabel("Waiting for Image");

    private final JPanel loginPanel = new JPanel(new GridLayout(3, 2));
    private final JTextField inputFieldUser = new JTextField(10);
    private final JTextField inputFieldPass = new JTextField(10);
    private final JButton loginButton = new JButton("Login");
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public LoginGui(ImmutableLoginManager loginManagerObj, CardLayout layout, JPanel panel) {

        cardLayout = layout;
        cardPanel = panel;

        loginManager = loginManagerObj;

        initComponents();

    }

    private void initComponents() {

        LayoutManager overlay = new OverlayLayout(panel);
        panel.setLayout(overlay);

        
        images.setIcon(new ImageIcon("/images/slide1.jpg"));
        
        background.add(images);
        
        mainPanel.add(background);
        
        loginPanel.add(new JLabel("Gebruikersnaam: "));
        loginPanel.add(inputFieldUser);
        loginPanel.add(new JLabel("Wachtwoord: "));
        loginPanel.add(inputFieldPass);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);
        loginPanel.setBackground(Color.LIGHT_GRAY);
        loginPanel.setPreferredSize(new Dimension(300, 100));
        loginPanel.setMaximumSize(loginPanel.getPreferredSize());
        loginPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        // Add mainPanel to Panel
        panel.add(loginPanel);
        panel.add(mainPanel);

        loginButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
    }

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {

        String username = inputFieldUser.getText();
        String password = inputFieldPass.getText();

        if (username.length() > 0 && password.length() > 0) {
            if (loginManager.login(username, password)) {
                cardLayout.show(cardPanel, loginManager.getFunctie().toLowerCase());
            } else {
                JOptionPane.showMessageDialog(null, "Gebruikersnaam, wachtwoord combinatie niet gevonden.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Zowel de gebruikersnaam als wachtwoord dient ingevuld te zijn.");
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
