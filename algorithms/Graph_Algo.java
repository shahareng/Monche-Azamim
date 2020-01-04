package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.node;
import dataStructure.node_data;
/**
 * This empty class represents the set of graph-theory algorithms
 * which should be implemented as part of Ex2 - Do edit this class.
 * @author 
 *
 */
public class Graph_Algo implements graph_algorithms
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
		for(node_data nodes : g.getV()) // isWay(first,all the nodes)
		{
			isCon = isWay(first.getKey(), nodes.getKey());
			if (isCon == true)
			{
				count++;
			}
		}
		if (count != g.nodeSize()) // count != number of all the nodes
		{
			return false;
		}
		for(node_data nodes : g.getV()) // isWay(all the nodes,first)
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
		node_data minWnode = null;
		for(Iterator<node_data> iter=g.getV().iterator();iter.hasNext();) 
		{
			// init the first node to zero
			node_data nd=iter.next();
			if(nd.getKey()==src)
				nd.setWeight(0);
			else // init the other nodes to inifinity
				nd.setWeight(Double.POSITIVE_INFINITY);
		}

		if(!isWay(src,dest)) // if there is no way 
		{
			return Double.POSITIVE_INFINITY;
		}

		Collection<node_data> nodes = g.getV();
		while(!nodes.isEmpty())
		{
			double minW=Double.POSITIVE_INFINITY; // return this value
			minWnode=null; // node with min weigth
			for(Iterator<node_data> iter = nodes.iterator();iter.hasNext();) { //find min node
				node_data nd=iter.next();
				if(nd.getWeight() < minW) {
					minW=nd.getWeight();
					minWnode=nd;
				}
			}
			try // if the node doesn't have edge
			{
				for(Iterator<edge_data> iter=g.getE(minWnode.getKey()).iterator();iter.hasNext();)
				{
					edge_data ed=iter.next();
					for(Iterator<node_data> iterNode=nodes.iterator();iter.hasNext();) 
					{ //find the dest node
						node_data nd=iterNode.next();
						if(nd.getKey() == ed.getDest())
						{
							if(nd.getWeight() < (minWnode.getWeight()+ed.getWeight())) 
							{
								nd.setWeight(minWnode.getWeight()+ed.getWeight());
								nd.setInfo(minWnode.getKey()+"");
								nd.setTag(minWnode.getKey());
								iterNode.remove();
							}
						}
					}
				}
				nodes.remove(minWnode);
			} catch (Exception e) {

			}

			if(minWnode.getKey() == dest) // check if we arrived to dest and finish the path
			{
				return minWnode.getWeight();
			}
		}
		return minWnode.getWeight();
	}

	@Override
	public List<node_data> shortestPath(int src, int dest)
	{
		List<node_data> node = new ArrayList<node_data>();
		List<node_data> node2 = new ArrayList<node_data>();
		shortestPathDist(src, dest);
		node_data des = null, nd = null, prev = null;
		for(Iterator<node_data> iter=g.getV().iterator();iter.hasNext();) 
		{
			nd=iter.next();
			if(nd.getKey() == src)
				node.add(nd);
			if(nd.getKey() == dest)
				des = nd;
		}
		nd = des;
		while (nd.getKey() != src) {
			node2.add(nd);
			for(Iterator<node_data> iter=g.getV().iterator();iter.hasNext();) 
			{
				prev = iter.next();
				if(prev.getKey() == nd.getTag())
				{
					nd = prev;
				}
			}
		}
		int size = node2.size();
		while(0 <= size)
		{
			node.add(node2.get(size));
			size--;
		}
		return node;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) 

	{
		List<node_data> path = new ArrayList<node_data>();
		if (this.isConnected() == false)
		{
			return null;
		}
		int rand = (int) (Math.random() * targets.size());
		int src = targets.get(rand), dest = 0;
		path.add(g.getNode(src));
		targets.remove(src);
		double minWay = Double.POSITIVE_INFINITY;
		while (!targets.isEmpty())
		{
			for (int i=0; i<targets.size(); i++)
			{
				if(this.shortestPathDist(src, targets.get(i)) < minWay)
				{
					minWay = this.shortestPathDist(src, targets.get(i));
					dest = targets.get(i);
				}
			}
			path.addAll(this.shortestPath(src, dest));
			src = dest;
			targets.remove(dest);
		}
		return path;
	}

	@Override
	public graph copy()
	{
		Graph_Algo copy = new Graph_Algo();
		String file_name = "copy.txt";
		this.save(file_name);
		copy.init(file_name);
		return copy.g;
	}

}
