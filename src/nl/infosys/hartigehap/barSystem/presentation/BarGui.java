/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.infosys.hartigehap.barSystem.presentation;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import nl.infosys.hartigehap.barSystem.domain.Bestelling;
import nl.infosys.hartigehap.barSystem.domain.ImmutableBestelling;
import nl.infosys.hartigehap.barSystem.domain.ImmutableProduct;
import nl.infosys.hartigehap.barSystem.businesslogic.BedieningManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import nl.infosys.hartigehap.barSystem.domain.ImmutableMedewerker;

/**
 *
 * @author iVP4C2
 */
public class BarGui {

    private final JPanel panel = new JPanel();
    private final BedieningManager manager;
    private JComboBox box;
    private JScrollPane scrollPane;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public BarGui(BedieningManager manager, CardLayout layout, JPanel panel) {
        this.manager = manager;

        cardLayout = layout;
        cardPanel = panel;

        makeFrame();
    }

    private JPanel medewerkerscodePaneel() {
        JPanel paneel = new JPanel();

        paneel.add(new JLabel("Medewekerscode:"));

        box = new JComboBox(manager.getMedewerkersCodes());
        paneel.add(box);

        return paneel;
    }

    private JPanel serverenPaneel(final Bestelling.Soort soort) {

        JPanel paneel = new JPanel();
        paneel.setLayout(new BoxLayout(paneel, BoxLayout.PAGE_AXIS));

        paneel.add(medewerkerscodePaneel());

        JPanel serverenPaneel = new JPanel();

        serverenPaneel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0, 0, 0, 20);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        serverenPaneel.add(new JLabel("Tafelnummer:"), c);

        c.gridx = 1;
        serverenPaneel.add(new JLabel("Product:"), c);

        c.gridx = 2;
        serverenPaneel.add(new JLabel("Aantal:"), c);

        c.gridx = 3;
        c.insets = new Insets(0, 0, 0, 0);
        serverenPaneel.add(new JLabel("Bevestig:"), c);

        ArrayList<ImmutableBestelling> bestellingen = manager.getOpenBestellingen(soort);

        int row = 1;

        for (final ImmutableBestelling bestelling : bestellingen) {

            if (!bestelling.getProducten().isEmpty()) {

                int i = 1;

                for (ImmutableProduct product : bestelling.getProducten()) {

                    c.gridy = row;
                    c.gridx = 0;
                    c.insets = new Insets(10, 0, 0, 20);
                    serverenPaneel.add(new JLabel(Integer.toString(bestelling.getTafelNummer())), c);

                    c.gridx = 1;
                    serverenPaneel.add(new JLabel(product.getNaam()), c);

                    c.gridx = 2;
                    serverenPaneel.add(new JLabel(Integer.toString(product.getAantal())), c);

                    c.gridx = 3;
                    c.insets = new Insets(10, 0, 0, 0);

                    if (i == bestelling.getAantalProducten()) {

                        JButton button = new JButton("Bevestig");

                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                try {
                                    if (manager.bestellingUitserveren((int) box.getSelectedItem(), bestelling.getId())) {

                                        // Refresh het scherm
                                        setInhoudScrollPane(serverenPaneel(soort));
                                    } else {
                                        /*
                                         Het uitserveren is niet gelukt, gooi een exception
                                         zodat deze opgevangen wordt en het bericht wordt
                                         weergegeven.
                                         */
                                        throw new Exception();
                                    }

                                } catch (Exception n) {
                                    // Geef een melding als er een fout is opgetreden
                                    JOptionPane.showMessageDialog(null, "Er is iets fout gegaan, probeer het opnieuw", "Fout", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        });

                        serverenPaneel.add(button, c);
                    } else {
                        serverenPaneel.add(new JLabel(""), c);
                    }

                    i++;
                    row++;
                }
            }
        }

        paneel.add(serverenPaneel);

        return paneel;
    }

    private JPanel betalingPaneel() {
        final JPanel paneel = new JPanel();
        paneel.setLayout(new BoxLayout(paneel, BoxLayout.PAGE_AXIS));

        paneel.add(medewerkerscodePaneel());

        JPanel betalingPaneel = new JPanel();

        betalingPaneel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(0, 0, 0, 20);

        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.gridy = 0;
        betalingPaneel.add(new JLabel("Tafelnummer:"), c);

        c.gridx = 1;
        betalingPaneel.add(new JLabel("Totaalprijs:"), c);

        c.gridx = 2;
        betalingPaneel.add(new JLabel("Bon?"), c);

        c.gridx = 3;
        c.insets = new Insets(0, 0, 0, 0);
        betalingPaneel.add(new JLabel("Bevestig:"));

        //Zet de tafelnummers in een arraylist
        ArrayList<ImmutableBestelling> Bestellingen = manager.getBestellingen(Bestelling.Status.WILTBETALEN, Bestelling.Soort.VOLLEDIG);

        int row = 1;

        //Zuidpaneel
        for (final ImmutableBestelling bestelling : Bestellingen) {

            c.gridy = row;
            c.gridx = 0;
            c.insets = new Insets(10, 0, 0, 20);

            betalingPaneel.add(new JLabel(Integer.toString(bestelling.getTafelNummer())), c);

            c.gridx = 1;
            betalingPaneel.add(new JLabel(bestelling.getTotaalPrijsFormat()), c);

            c.gridx = 2;
            final JCheckBox bon = new JCheckBox();
            betalingPaneel.add(bon, c);

            c.gridx = 3;
            c.insets = new Insets(10, 0, 0, 0);
            JButton button = new JButton("ok");
            betalingPaneel.add(button, c);

            row++;

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (manager.updateBestellingStatus(bestelling.getTafelNummer(), (int) box.getSelectedItem(), Bestelling.Status.BETAALD)) {
                            if (bon.isSelected()) {
                                manager.printBon(bestelling);
                            }

                            // Refresh het scherm
                            setInhoudScrollPane(betalingPaneel());
                        } else {
                            /*
                             Het veranderen van de status is niet gelukt, gooi een exception
                             zodat deze opgevangen wordt en het bericht wordt
                             weergegeven.
                             */
                            throw new Exception();
                        }
                    } catch (Exception n) {
                        JOptionPane.showMessageDialog(null, "Er is iets fout gegaan, probeer het opnieuw", "Fout", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        paneel.add(betalingPaneel);

        return paneel;
    }

    private JPanel aanwezigenPaneel() {
        final JPanel paneel = new JPanel();
        paneel.setLayout(new BoxLayout(paneel, BoxLayout.PAGE_AXIS));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        mainPanel.add(createEmployeeList());

        // Add mainPanel to Paneel
        paneel.add(mainPanel);

        return paneel;
    }

    public JPanel createEmployeeList() {

        final JPanel paneel = new JPanel();
        paneel.setLayout(new BoxLayout(paneel, BoxLayout.PAGE_AXIS));

        JPanel medewerkersPaneel = new JPanel();

        medewerkersPaneel.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        ArrayList<ImmutableMedewerker> employees = manager.getAllMedewerkers();

        int row = 1;

        //Zuidpaneel
        for (final ImmutableMedewerker employee : employees) {

            c.gridy = row;
            c.gridx = 0;
            c.insets = new Insets(10, 0, 0, 20);

            medewerkersPaneel.add(new JLabel(employee.getAchternaam() + ", " + employee.getVoornaam()), c);

            /**
             * @todo: Knop toevoegen om medewerker aanwezig of afwezig te zetten
             */
            c.gridx = 1;
            c.insets = new Insets(10, 0, 0, 0);

            String status = "afwezig";
            if (employee.getStatus().equals("ingelogd")) {
                status = "aanwezig";
            }

            final JToggleButton toggleButton = new JToggleButton(status);
            Dimension dimension = new Dimension(90, 25);
            toggleButton.setPreferredSize(dimension);

            if (status.equals("aanwezig")) {
                toggleButton.setSelected(true);
            }
            
            ActionListener actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    AbstractButton abstractButton = (AbstractButton) actionEvent.getSource();
                    boolean selected = abstractButton.getModel().isSelected();
                    if (selected == false) {
                        if (manager.updateMedewerkerStatus(employee.getCode(), "uitgelogd")) {
                            toggleButton.setText("afwezig");
                        }
                    } else {
                        if (manager.updateMedewerkerStatus(employee.getCode(), "ingelogd")) {
                            toggleButton.setText("aanwezig");
                        }
                    }
                }
            };
            toggleButton.addActionListener(actionListener);
            medewerkersPaneel.add(toggleButton, c);

            row++;
        }

        paneel.add(medewerkersPaneel);

        return paneel;

    }

    public void updateTable() {
//        scrollPane.setViewportView(createOrderList());
//        scrollPane2.setViewportView(createTotalList());
    }

    private JPanel homePaneel() {
        JPanel paneel = new JPanel();
        paneel.setLayout(new BoxLayout(paneel, BoxLayout.Y_AXIS));

        Dimension dimension = new Dimension(200, 200);

        JButton keuken = new JButton("Keukenbestellingen");
        keuken.setMaximumSize(dimension);

        keuken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInhoudScrollPane(serverenPaneel(Bestelling.Soort.KEUKEN));
            }
        });

        paneel.add(keuken);

        paneel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton bar = new JButton("Barbestellingen");
        bar.setMaximumSize(dimension);

        bar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInhoudScrollPane(serverenPaneel(Bestelling.Soort.BAR));
            }
        });

        paneel.add(bar);

        paneel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton gereed = new JButton("Gereed voor betaling");
        gereed.setMaximumSize(dimension);

        gereed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInhoudScrollPane(betalingPaneel());
            }
        });

        paneel.add(gereed);

        paneel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton aanwezigen = new JButton("Aanwezigen");
        aanwezigen.setMaximumSize(dimension);

        aanwezigen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInhoudScrollPane(aanwezigenPaneel());
            }
        });

        paneel.add(aanwezigen);

        paneel.add(Box.createRigidArea(new Dimension(10, 10)));

        return paneel;
    }

    private void setInhoudScrollPane(JPanel paneel) {
        /*
         Er wordt een panel over het paneel toegevoegd met een girdbaglayout.
         Dit zorgt ervoor de het paneel gecentreerd wordt weergegeven.       
         */
        JPanel centerPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(20, 0, 0, 0);
        c.anchor = GridBagConstraints.NORTH;

        centerPanel.add(paneel, c);

        scrollPane.setViewportView(centerPanel);
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    private JPanel header() {
        JPanel header = new JPanel();

        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setMaximumSize(new Dimension(999999, 50));

        // Create Top panel
        JPanel headerTop = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel companyname = new JLabel("Eetcafe Hartige Hap");
        companyname.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Add Label to headerTop
        headerTop.add(companyname);

        JPanel headerBottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerBottom.setBorder(BorderFactory.createEmptyBorder(0, 20, 5, 0));

        JButton backButton = new JButton();
        backButton.setText("< keer terug");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInhoudScrollPane(homePaneel());
            }
        });

        // Add Button to headerBottom
        headerBottom.add(backButton, FlowLayout.LEFT);

        // Add Top Panel to Panel
        header.add(headerTop);
        header.add(headerBottom);

        return header;
    }

    private JPanel bottom() {
        JPanel bottom = new JPanel();
        bottom.setAlignmentY(FlowLayout.CENTER);
        bottom.setMaximumSize(new Dimension(900, 50));

        JLabel poweredby = new JLabel("Powered by InfoSys");
        poweredby.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // Add Label to Bottom Panel
        bottom.add(poweredby);

        return bottom;
    }

    public void makeFrame() {

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(header());

        scrollPane = new JScrollPane();

        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        setInhoudScrollPane(homePaneel());

        panel.add(scrollPane);

        // Add Bottom Panel to Panel
        panel.add(bottom());

        //panel.add(contentpane);
    }

    public JPanel getPanel() {
        return panel;
    }
}
