package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import utils.Point3D;

public class DGraphTest {

	graph graph;

	@Before
	public void insert()
	{
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
	}

	@After
	public void reset()
	{
		for (node_data node : graph.getV()) 
		{
			graph.removeNode(node.getKey());
		}
	}

	@Test
	public void getNodeTest()
	{ // return the node with this key
		fail("Not yet implemented");
	}

	@Test
	public void getEdgeTest()
	{ // return the data of the edge (src,dest)
		fail("Not yet implemented");
	}

	@Test
	public void addNodeTest()
	{
		node_data n = new node(20,new Point3D(100,100,150),0);
		graph.addNode(n);
	}

	@Test
	public void connectTest()
	{
		fail("Not yet implemented");
	}

	@Test
	public void getVTest()
	{
		fail("Not yet implemented");
	}

	@Test
	public void getETest()
	{
		fail("Not yet implemented");
	}

	@Test
	public void removeNodeTest()
	{
		fail("Not yet implemented");
	}

	@Test
	public void removeEdgeTest()
	{
		fail("Not yet implemented");
	}

	@Test
	public void nodeSizeTest()
	{
		fail("Not yet implemented");
	}

	@Test
	public void edgeSizeTest()
	{
		fail("Not yet implemented");
	}

	@Test
	public void getMCTest()
	{
		fail("Not yet implemented");
	}

}
