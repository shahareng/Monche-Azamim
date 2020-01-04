package dataStructure;

import utils.Point3D;

public class node implements node_data
{
	int key;
	Point3D location;
	double weight;
	String info;
	int tag;
//	node_data prev;
	
	public node(int key, Point3D p,double w)
	{
		this.key = key;
		this.location = p;
		this.weight = w;
	}
	
	@Override
	public int getKey()
	{
		return this.key;
	}

	@Override
	public Point3D getLocation() 
	{
		return this.location;
	}

	@Override
	public void setLocation(Point3D p) 
	{
		this.location = p;
	}

	@Override
	public double getWeight() 
	{
		return this.weight;
	}

	@Override
	public void setWeight(double w) 
	{
		this.weight = w;
	}

	@Override
	public String getInfo() 
	{
		return this.info;
	}

	@Override
	public void setInfo(String s)
	{
		this.info = s;
	}

	@Override
	public int getTag() 
	{
		return this.tag;
	}

	@Override
	public void setTag(int t)
	{
		this.tag = t;
	}
	
//	public node_data getPrev() 
//	{
//		return this.prev;
//	}
//
//	public void setPrev(node_data p)
//	{
//		this.prev = p;
//	}

}
