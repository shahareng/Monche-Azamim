package dataStructure;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class DGraph implements graph
{
	
	//Empty constructor
	public DGraph()
	{
		ArrayList<node_data> V = new ArrayList<node_data>();
		ArrayList<node_data> E = new ArrayList<node_data>();
	}

	@Override
	public node_data getNode(int key) 
	{
		if(key < 0 || key > this.getV().size())
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
	}

	@Override
	public edge_data getEdge(int src, int dest) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNode(node_data n) 
	{
		this.g
		// TODO Auto-generated method stub
		
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
	}

	@Override
	public Collection<node_data> getV() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public node_data removeNode(int key) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize()
{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
