package Tests;

import static org.junit.Assert.*;

import java.util.Collection;

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

	graph graph = new DGraph();

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
		try {
			for (node_data node : graph.getV())
			{
				graph.removeNode(node.getKey());
			}
		}
		catch (Exception e)
		{
			System.out.println(e);

		}
	}

	@Test
	public void getNodeTest()
	{ // return the node with this key
		int key = 12;
		int key2 = 4;
		for (node_data node : graph.getV())
		{
			node_data n = graph.getNode(key);
			assertEquals(key, n.getKey());
			try {
				node_data n2 = graph.getNode(key2);
				assertEquals(key2, n2.getKey());
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
		}
	}

	@Test
	public void getEdgeTest()
	{ // return the data of the edge (src,dest)
		int src = 10;
		int dest = 13;
		int dest2 = 4;
		edge_data e = graph.getEdge(src, dest);
		assertEquals(e.getSrc(), src);
		assertEquals(e.getDest(), dest);
		try
		{
			edge_data e2 = graph.getEdge(src, dest2);
			assertEquals(e2.getSrc(), src);
			assertEquals(e2.getDest(), dest2);
		}
		catch (Exception err)
		{
			System.out.println(err);
		}
	}

	@Test
	public void addNodeTest()
	{
		node_data n = new node(4,new Point3D(70,110,130),0);
		int size = graph.getV().size();
		graph.addNode(n);
		int newSize = graph.getV().size();
		assertEquals(size+1, newSize);

	}

	@Test
	public void connectTest()
	{
		int weight = 2;
		graph.connect(10,12,2);
		edge_data e = graph.getEdge(10, 12);
		assertEquals(e.getWeight(), weight, 0.000001);
	}

	@Test
	public void getVTest()
	{
		Collection <node_data> col = graph.getV();
		assertEquals(graph.nodeSize(), col.size());
	}

	@Test
	public void getETest()
	{
		Collection <edge_data> col = null;
		try
		{
		for (node_data node : graph.getV())
		{
			col.addAll(graph.getE(node.getKey()));
		}
		assertEquals(graph.edgeSize(), col.size());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	@Test
	public void removeNodeTest()
	{
		int key = 10;
		int key2 = 4;
		int size = graph.nodeSize();
		graph.removeNode(key);
		assertEquals(graph.nodeSize(), size-1);
		try {
			int size2 = graph.nodeSize();
			graph.removeNode(key2);
			assertEquals(graph.nodeSize(), size2);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	@Test
	public void removeEdgeTest()
	{
		int src = 10;
		int dest = 13;
		int dest2 = 3;
		edge_data e = graph.getEdge(src, dest);
		int size = graph.edgeSize();
		graph.removeEdge(src, dest);
		assertEquals(graph.edgeSize(), size-1);
		try
		{
			int size2 = graph.edgeSize();
			edge_data e2 = graph.getEdge(src, dest2);
			graph.removeEdge(src, dest2);
			assertEquals(graph.edgeSize(), size2);
		}
		catch (Exception err)
		{
			System.out.println(err);
		}
	}

	@Test
	public void nodeSizeTest()
	{
		assertEquals(graph.getV().size(), graph.nodeSize());
	}

	@Test
	public void edgeSizeTest()
	{
		int numOfConnects = 12;
		assertEquals(graph.edgeSize(), numOfConnects);
	}

	@Test
	public void getMCTest()
	{
		int MC = graph.getMC();
		node_data n = new node(4,new Point3D(70,110,130),0);
		graph.addNode(n);
		assertEquals(graph.getMC(), MC+1);
	}

}
