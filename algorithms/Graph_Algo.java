package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms,Serializable
{
	private graph g;

	public Graph_Algo()
	{
		;
	}
	@Override
	public void init(graph g)
	{
		this.g = g;

	}

	@Override
	public void init(String file_name) 
	{
		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream objInput = new ObjectInputStream(file); 
			g = (graph)objInput.readObject(); 
			objInput.close(); 
			file.close(); 
		} 
		catch(IOException e) 
		{ 
			e.printStackTrace();
		} 
		catch(ClassNotFoundException e) 
		{ 
			e.printStackTrace(); 
		} 

	}

	@Override
	public void save(String file_name) 
	{
		try
		{    
			FileOutputStream file = new FileOutputStream(file_name); 
			ObjectOutputStream out = new ObjectOutputStream(file); 

			out.writeObject(g); 
			out.close(); 
			file.close(); 

			System.out.println("Object has been serialized"); 
		}   
		catch(IOException ex) 
		{ 
			System.out.println("IOException is caught"); 
		} 

	}

	@Override
	public boolean isConnected()
	{
		boolean isCon = false;
		int count = 0;
		node_data first = g.getV().iterator().next();
		for(node_data nodes : g.getV()) // isway(first,all the nodes)
		{
			isCon = isWay(first.getKey(), nodes.getKey());
			if (isCon == true)
			{
				count++;
			}
		}
		if (count != g.nodeSize()) // num= number of all the nodes
		{
			return false;
		}
		for(node_data nodes : g.getV()) //isWay(all the nodes,first)
		{
			isCon = isWay(nodes.getKey(), first.getKey());
			if (isCon == false)
			{
				return false;
			}
		}
		return true;
	}

	public boolean isWay(int src,int dest)
	{
		for(Iterator<edge_data> iter=g.getE(src).iterator();iter.hasNext();) 
		{
			edge_data ed = iter.next();
			if(ed.getDest() == dest)
			{
				return true;
			}
			else if(isWay(ed.getDest(),dest))
			{
				return true;
			}
		}
		return false;
	}
	@Override
	public double shortestPathDist(int src, int dest) 
	{
		for(Iterator<node_data> iter=g.getV().iterator();iter.hasNext();) 
		{
			node_data nd=iter.next();
			if(nd.getKey()==src)
				nd.setWeight(0);
			else
				nd.setWeight(Double.POSITIVE_INFINITY);
		}

		if(!isWay(src,dest))
		{
			return Double.POSITIVE_INFINITY;
		}
		while(true)
		{
			double minW=Double.POSITIVE_INFINITY;
			node_data minWnode=null;
			for(Iterator<node_data> iter=g.getV().iterator();iter.hasNext();) {//find min 
				node_data nd=iter.next();
				if(nd.getWeight()<minW) {
					minW=nd.getWeight();
					minWnode=nd;
				}
			}
			try 
			{
				for(Iterator<edge_data> iter=g.getE(minWnode.getKey()).iterator();iter.hasNext();) 
				{
					edge_data ed=iter.next();
					for(Iterator<node_data> iterNode=g.getV().iterator();iter.hasNext();) 
					{//find the dest node
						node_data nd=iterNode.next();
						if(nd.getKey()==ed.getDest())
						{
							if(nd.getWeight()< (minWnode.getWeight()+ed.getWeight()))
							{
								nd.setWeight(minWnode.getWeight()+ed.getWeight());
								nd.setInfo(minWnode.getKey()+"");
							}
						}
					}
				}
			}catch (Exception e) {};

			if(minWnode.getKey() == dest)
			{
				return minWnode.getWeight();
			}
		}
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() 
	{
		graph copy = new DGraph();

		return copy;

	}

}
