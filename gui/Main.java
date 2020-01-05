package gui;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import utils.Point3D;

public class Main
{

	public static void main(String[] args) 
	{
		Graph_Algo G = new Graph_Algo();
		graph graph =new DGraph();

		graph.addNode(new node(10,new Point3D(100,100,150),0));
		graph.addNode(new node(11,new Point3D(135,125,130),0));
		graph.addNode(new node(12,new Point3D(120,300,200),0));
		graph.addNode(new node(13,new Point3D(150,200,100),0));
		graph.addNode(new node(14,new Point3D(75,250,250),0));
		graph.addNode(new node(15,new Point3D(300,300,300),0));
		graph.addNode(new node(16,new Point3D(500,100,100),0));

		graph.connect(10,13,3);
		graph.connect(10,14,4);
		graph.connect(11,10,5);
		graph.connect(11,13,6);
		graph.connect(12,11,7);
		graph.connect(13,14,1);
		graph.connect(13,12,1.5);
		graph.connect(14,13,3.5);
		graph.connect(12,15,8);
		graph.connect(11,15,10);
		graph.connect(15, 16,20);
		graph.connect(11,16,15);
		G.init(graph);
		

		//System.out.println(G.isConnected());
		System.out.println(G.shortestPathDist(10, 14));
		//System.out.println(G.shortestPath(10, 14));
		GUI window = new GUI(graph);
		window.setVisible(true);

	}
}
