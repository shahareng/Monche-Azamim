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
		this.V.put( n.getKey(), n);
	}

	@Override
	public void connect(int src, int dest, double w) 
	{
		if(w < 0)
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
		
		edge_data e = new edge(src,dest,w);
		
		
		
	}

	@Override
	public Collection<node_data> getV() 
	{
		
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) 
	{
		
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

}
