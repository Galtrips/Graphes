/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sae_tp_etu;

/**
 *
 * @author educe
 */

import javax.swing.JDialog;
import org.graphstream.algorithm.generator.BarabasiAlbertGenerator;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.GridGenerator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author Eric
 */
public class GrapheFabrique {
    
    static Graph createPersonalGraph()  
    {
        
        Graph graph = new SingleGraph("PersonalGraph");
        
        graph.addNode("A" );
        graph.addNode("B" );
        graph.addNode("C" );
        graph.addNode("D" );
        
        graph.addEdge("AB", "A", "B");
        graph.addEdge("BC", "B", "C");
        graph.addEdge("CD", "C", "D");
        graph.addEdge("DA", "D", "A");
        
        graph.addNode("E" );
        
        graph.addEdge("AE", "A", "E");
        graph.addEdge("DE", "D", "E");
        
        return graph;
        
    }    
    
      static Graph createChaine(int nb){
        
        Graph g = new SingleGraph("Chaine");
        
        g.addNode("0");
        
        for (Integer i = 1; i < nb; i++) {
            g.addNode(i.toString());
            Integer j = new Integer(i-1);
            g.addEdge(j.toString()+i.toString(),i-1,i);
        }
        return g;
    }
    
    static public Graph createCycle(int nb)
    {
        Graph g = new SingleGraph("Cycle");
        
        g.addNode("0");
        
        for (Integer i = 1; i < nb; i++) {
            g.addNode(i.toString());
            Integer j = new Integer(i-1);
            g.addEdge(j.toString()+i.toString(),i-1,i);
        }
        
        g.addEdge(new Integer(nb).toString()+"0",nb-1,0);
        
        return g;
    }
    
    static Graph createRandom(int n, int avg){
        
        Graph g = new SingleGraph("Random");
        Generator gen = new RandomGenerator(avg);
        
        gen.addSink(g);
        gen.begin();
        for(int i=0; i<n; i++)
            gen.nextEvents();
        gen.end();
        
        return g;        
    }
  
    
    static public Graph createGrid(int dim1, int dim2, boolean torus)
    {
        Graph g = new SingleGraph("Grid");
        Integer cpt = new Integer(1);
        
        for (int i = 0; i < dim1; i++) {
            for (int j = 0; j < dim2; j++) {
                g.addNode(cpt.toString());
                cpt++;
            }
        }
       
       

        return g;
    }
    
    
    
}

