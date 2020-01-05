package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
import utils.Point3D;

public class Graph_AlgoTest {

	static Graph_Algo G = new Graph_Algo();
	static graph graph =new DGraph();

	@BeforeClass
	public static void insert()
	{
		graph.addNode(new node(10,new Point3D(100,100,150),0));
		graph.addNode(new node(12,new Point3D(120,300,200),0));
		graph.addNode(new node(15,new Point3D(300,300,300),0));
		graph.addNode(new node(16,new Point3D(500,100,100),0));

		graph.connect(16, 15, 1);
		graph.connect(16, 10, 5);
		graph.connect(15, 16, 10);
		graph.connect(15, 12, 3);
		graph.connect(12, 15, 10);
		graph.connect(10, 16, 10);
		graph.connect(10, 12, 10);
		graph.connect(12, 10, 10);
	}

	@AfterClass
	public static void reset()
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

	@Before
	public void set()
	{

	}

	@After
	public void finish()
	{

	}

	@Test
	public void TestInit()
	{
		G.init(graph);
	}

	@Test
	public void TestCopy()
	{
		graph copy = G.copy();
		assertEquals(copy, G);
	}

	@Test
	public void TestInitStringFile()
	{
		String fileTest = "initTest.txt";
		G.init(fileTest);

		String fileTest2 = "initError.txt";
		G.init(fileTest);

	}

	@Test
	public void TestSave()
	{
		String file_name  = "saveTest.txt";
		G.save(file_name);
	}

	@Test
	public void TestIsConnected()
	{
		assertTrue(G.isConnected());
	}

	@Test
	public void TestShortestPathDist()
	{
		int path = 4;
		assertEquals(path, G.shortestPathDist(16, 12), 0.00001);
	}

	@Test
	public void TestShortestPath()
	{
		List<node_data> path = new ArrayList<>();
		node_data nd1 = new node(16,new Point3D(500,100,100),0);
		node_data nd2 = new node(15,new Point3D(300,300,300),0);
		node_data nd3 = new node(12,new Point3D(120,300,200),0);
		path.add(nd1);
		path.add(nd2);
		path.add(nd3);
		List<node_data> path2 = G.shortestPath(16,12);
		for(int i=0; i<path.size(); i++)
		{
			assertEquals(path.get(i), path2.get(i));
		}
	}

	@Test
	public void TestTSP()
	{
		List<Integer> tsp =  new ArrayList<Integer>();
		tsp.add(15);
		tsp.add(12);
		tsp.add(16);
		List<node_data> path = new ArrayList<>();
		node_data nd1 = new node(16,new Point3D(500,100,100),0);
		node_data nd2 = new node(15,new Point3D(300,300,300),0);
		node_data nd3 = new node(12,new Point3D(120,300,200),0);
		path.add(nd1);
		path.add(nd2);
		path.add(nd3);
		List<node_data> TSP = G.TSP(tsp);

		for(int i=0; i<path.size(); i++)
		{
			assertEquals(path.get(i).getKey(), TSP.get(i));
		}
	}
}
