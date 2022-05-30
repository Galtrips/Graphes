package sae_tp_etu;

/**
 *
 * @author educe
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import org.graphstream.algorithm.Algorithm;
import org.graphstream.algorithm.Kruskal;
import org.graphstream.algorithm.Toolkit;
import org.graphstream.algorithm.coloring.WelshPowell;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.stream.file.FileSinkImages;
import org.graphstream.ui.swingViewer.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.graphstream.ui.swingViewer.*;

//import org.graphstream.ui.view.Camera;
//import org.graphstream.ui.view.View;
//import org.graphstream.ui.view.Viewer;

/**
 *
 * @author Eric
 */
public class FenetrePrincipale extends JFrame {

    JPanel panneauDessin;
    PanneauChoixGraphe panneauChoixG;
    PanneauAlgo panneauAlgo;
    Graph grapheCourant = null;

    private Viewer graphViewer;
    private View graphView;

    public FenetrePrincipale() {
        panneauDessin = new JPanel();
        panneauChoixG = new PanneauChoixGraphe();
        panneauAlgo = new PanneauAlgo();
        panneauDessin.setLayout(new BorderLayout());
        panneauDessin.setPreferredSize(new Dimension(800, 600));
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = gc.gridy = 0;
        gc.gridheight = 3;
        gc.fill = GridBagConstraints.HORIZONTAL;
        this.getContentPane().add(panneauDessin, gc);
        gc.gridx = 1;
        gc.gridheight = 1;
        this.getContentPane().add(panneauChoixG, gc);
        gc.gridy = 1;
        this.getContentPane().add(panneauAlgo, gc);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
    }

    public void updateDessin() {

        if (grapheCourant != null) {
            try {
                panneauDessin.remove((Component) graphViewer.getView(Viewer.DEFAULT_VIEW_ID));
            } catch (Exception ex) {
            }
            graphViewer = new Viewer(grapheCourant, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
            graphViewer.enableAutoLayout();
            graphView = graphViewer.addDefaultView(false);
            panneauDessin.add((Component) graphView);
            graphView.revalidate();
            for(Node n:grapheCourant) {
                //n.setAttribute("ui.style", "fill-color: rgb(0,100,255); shape:box; size:20px;");
                n.setAttribute("ui.label", n);
            }
            
        }
        
    }

    class PanneauChoixGraphe extends JPanel {

        JComboBox<TypeGraphe> combo;
        JButton generer;
        JTextField jtf1 = new JTextField(15);
        JTextField jtf2 = new JTextField(15);
        JLabel dim1 = new JLabel("Dimension 1");
        JLabel dim2 = new JLabel("Dimension 2");

        public PanneauChoixGraphe() {
            this.setLayout(new GridLayout(3, 2));

            generer = new JButton("Generer");
            combo = new JComboBox();
            combo.addItem(TypeGraphe.PERSO);
            combo.addItem(TypeGraphe.CHAINE);
            combo.addItem(TypeGraphe.RANDOM);
            combo.addItem(TypeGraphe.GRILLE);
            combo.addItem(TypeGraphe.TORE);
            combo.addItem(TypeGraphe.CYCLE);

            this.setBorder(BorderFactory.createTitledBorder("Choix du graphe"));
            this.add(combo);
            this.add(generer);
            this.add(dim1);
            this.add(jtf1);
            this.add(dim2);
            this.add(jtf2);

            generer.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    TypeGraphe type;
                    int dim2;
                    try {
                        int dim1;
                        switch (combo.getSelectedIndex()) {
                            case 0:
                                grapheCourant = GrapheFabrique.createPersonalGraph();
                                break;
                            case 1:
                                dim1 = Integer.parseInt(jtf1.getText());
                                grapheCourant = GrapheFabrique.createChaine(dim1);
                                break;
                            case 2:
                                dim1 = Integer.parseInt(jtf1.getText());
                                dim2 = Integer.parseInt(jtf2.getText());
                                grapheCourant = GrapheFabrique.createRandom(dim1, dim2);
                                break;
                            case 3:
                                dim1 = Integer.parseInt(jtf1.getText());
                                dim2 = Integer.parseInt(jtf2.getText());
                                grapheCourant = GrapheFabrique.createGrid(dim1,dim2, false);
                                break;
                            case 4:
                                dim1 = Integer.parseInt(jtf1.getText());
                                dim2 = Integer.parseInt(jtf2.getText());
                                grapheCourant = GrapheFabrique.createGrid(dim1,dim2, true);
                                break;
                            case 5:
                                dim1 = Integer.parseInt(jtf1.getText());
                                grapheCourant = GrapheFabrique.createCycle(dim1);
                                break;
                        }

                        updateDessin();
                    } catch (Exception exc) {
                    }
                }

            });

        }

    }

    class PanneauAlgo extends JPanel {

        JButton distance;
        JButton dominant;
        JLabel dominantl;
        JButton glouton;
        JLabel gloutonl;
        JButton astar;
        JTextField astarf;

        public PanneauAlgo() {
            this.setBorder(BorderFactory.createTitledBorder("Algorithmes"));
            this.setLayout(new GridBagLayout());
            GridBagConstraints gc = new GridBagConstraints();
            distance = new JButton("Distances");
            dominant = new JButton("Dominant");
            dominantl = new JLabel("");
            glouton = new JButton("Coloration gloutonne");
            gloutonl = new JLabel("");
            gc.fill = GridBagConstraints.HORIZONTAL;
            gc.gridx = gc.gridy = 0;
            gc.weightx = .8;
            gc.insets = new Insets(5, 5, 5, 5);
            this.add(distance, gc);
            gc.gridy = 1;
            this.add(dominant, gc);
            gc.gridx = 1;
            gc.weightx = .2;
            this.add(dominantl, gc);
            gc.gridy = 2;
            gc.gridx = 0;
            gc.weightx = .8;
            this.add(glouton, gc);
            gc.gridx = 1;
            gc.weightx = .2;
            this.add(gloutonl, gc);

            distance.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    Node s=grapheCourant.getNode(0);
                    Algos.distance(grapheCourant, s);
                    updateDessin();
                }

                
            });

            dominant.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    Algos.ensembleDominant(grapheCourant);
                    updateDessin();
                }

                
            });

            glouton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    Algos.ColorationGloutonne(grapheCourant);
                    updateDessin();
                }

            });
        }
    }
}
