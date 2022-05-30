/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae_tp_etu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;

/**
 *
 * @author educe
 */
public class Algos {
    
    public static void ensembleDominant(Graph g) {
        
        for (Node n : g) {   
            n.setAttribute("ui.style", "fill-color: rgb(0,0,0); shape:box; size: 10px;");  
        }
        
        //Ajout de l'attribut dominant à tous les points
        for (Node n : g) {
            n.addAttribute("dominant", 0);
        }
        
        for (Node n : g) {
            AtomicBoolean test = new AtomicBoolean(true);
            Iterator it = n.getNeighborNodeIterator();
            
            while (it.hasNext()) {
                Node u = (Node) it.next();
             
                if (u.getAttribute("dominant").equals(1)) {
                    test.set(false);
                }
               
                if (u.getAttribute("dominant").equals(1)) {
                    test.set(false);
                }            
           }
            
           if (test.get() == true) {
               n.setAttribute("dominant", 1);
           }
        }
        
        for (Node n : g) {   
            if (n.getAttribute("dominant").equals(1)) {
                n.setAttribute("ui.style", "fill-color: rgb(255,100,0); shape:box; size: 30px;");
            }
        }
        
    }

    //calcule les distances de tous les noeuds à la source s
    public static void distance(Graph g, Node s) {
        
      
    }

    public static void ColorationGloutonne(Graph g) {
        for (Node n : g) {
            n.addAttribute("couleur", 0);
        }
            
        for (Node n : g) {
            Iterator it = n.getNeighborNodeIterator();  
            Node tab[] = new Node [n.getDegree()];
            int cpt = 0;
            while (it.hasNext()) {
                Node u = (Node) it.next();
                tab[cpt] = u;
                cpt++;
            }
            
            boolean drapeau = true;
            
            while (drapeau == true) {
                drapeau = false;
                for (int i = 0; i < n.getDegree() - 1; i++) {
                    if (Integer.parseInt(tab[i].getAttribute("couleur").toString()) > Integer.parseInt(tab[i+1].getAttribute("couleur").toString()) ) {
                        Node tmp = tab[i+1];
                        tab[i+1] = tab[i];
                        tab[i] = tmp;
                        drapeau = true;
                    }
                }
            }
            int coul = 1;
            for (int i = 0; i < n.getDegree(); i++) {
                 if (tab[i].getAttribute("couleur").equals(coul)) {
                     coul++;
                 }
            }
            n.setAttribute("couleur", coul);
            System.out.println(n + " " + coul);
            coul = 1;
        }   
        System.out.println("--------------------------------");
        colorierGraphe(g, "couleur");
            
    }
    
    public static void colorierGraphe(Graph g, String attribut) {
        int max = g.getNodeCount();
        Color[] cols = new Color[max + 1];
        for (int i = 0; i <= max; i++) {
            cols[i] = Color.getHSBColor((float) (Math.random()), 0.8f, 0.9f);
        }
        
        for (Node n : g) {
            int col = n.getAttribute(attribut);
            if (n.hasAttribute("ui.style")) {
                n.setAttribute("ui.style", "fill-color:rgba(" + cols[col].getRed() + "," + cols[col].getGreen() + "," + cols[col].getBlue() + ",200);");
            } else {
                n.addAttribute("ui.style", "fill-color:rgba(" + cols[col].getRed() + "," + cols[col].getGreen() + "," + cols[col].getBlue() + ",200);");
            }
        }
    }
  
}
