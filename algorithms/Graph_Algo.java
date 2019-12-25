package algorithms;

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
public class Graph_Algo implements graph_algorithms
{
	private graph g;
	@Override
	public void init(graph g)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void init(String file_name) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void save(String file_name) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isConnected()
	{
		// TODO Auto-generated method stub
		return false;
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
		DGraph copy = new DGraph(this.V,this.E);

		return copy;

	}

}
