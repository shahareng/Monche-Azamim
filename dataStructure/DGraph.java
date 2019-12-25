package dataStructure;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class DGraph implements graph
{
	HashMap<Integer,node_data> V = new HashMap<>();
	HashMap<Integer,HashMap<Integer,edge_data>> E = new HashMap<>();
	int MC;
	int ESize;
	//Empty constructor
	public DGraph()
	{
		;
	}

	public DGraph(HashMap<Integer,node_data>V,HashMap<Integer,HashMap<Integer,edge_data>>E)
	{
		this.V.putAll(V);
		this.E.putAll(E);
	}

	@Override
	public node_data getNode(int key) 
	{
		if(key < 0 && !this.V.containsKey(key))
		{
			try 
			{
				throw new IOException();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return this.V.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) 
	{
		if( !E.containsKey(src) && !E.containsValue(E.get(dest)))
		{
			try 
			{
				throw new IOException();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		return this.E.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) 
	{
		this.V.put(n.getKey(), n);
	}

	@Override
	public void connect(int src, int dest, double w) 
	{
		boolean b=true;
		
		if (src == dest) 
		{
			System.out.println("src and dest are the same");
			b = false;
		}
		if (b||(!(V.get(src) == null) || !(V.get(dest) == null))) 
		{
			if (E.containsKey(src))
			{
				if (E.get(src).get(dest) != null) 
				{
					throw new RuntimeException("The edge is already exist!");
				}
				else 
				{
					edge_data edge = new edge(src, dest, w);
					this.E.get(src).put(dest, edge);
					MC++;
					ESize++;
				}
			} 
			else 
			{
				edge_data edge = new edge(src, dest, w);
				E.put(src, new HashMap<>());
				this.E.get(src).put(dest, edge);
				MC++;
				ESize++;
			}
		} 
		else if(b)
		{
			throw new RuntimeException("src/dest does not exist!");
		}
	}

	@Override
	public Collection<node_data> getV() 
	{	
		return this.V.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) 
	{
		
		return this.E.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) 
	{
		if(!V.containsKey(key))
		{
			try 
			{
				throw new IOException();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		node_data n = V.get(key);
		V.remove(key);
		E.remove(key);
		E.get(key).remove(key);
		return n;
	}

	@Override
	public edge_data removeEdge(int src, int dest)
	{
		if(!E.containsKey(src) || !E.get(src).containsKey(dest))
		{
			try 
			{
				throw new IOException();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		edge_data e = E.get(src).get(dest);
		E.remove(src, E.get(dest));
		ESize--;
		return e;
	}

	@Override
	public int nodeSize() 
	{
		return V.size();
	}

	@Override
	public int edgeSize()
	{
		return E.size();
	}

	@Override
	public int getMC() 
	{
		return this.MC;
	}

}
