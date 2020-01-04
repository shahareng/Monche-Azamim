package gui;
import dataStructure.*;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import sun.security.provider.certpath.Vertex;
import utils.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.html.HTMLDocument.Iterator;

import algorithms.Graph_Algo;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import dataStructure.*;
public class GUI extends JFrame implements ActionListener , MouseListener , Observer
{
	graph graph; 
	Graph_Algo ga; 
	int Choice= 0 ; 
	public Collection<node_data> vertex;
	public Collection<edge_data> edges; 
	JFrame frame;
	Graph_Algo G = new Graph_Algo(); 
	List<node_data> vToPaint = new ArrayList<>();
	double shortPD = 0;

	public GUI() {}
	public GUI(graph g)
	{

		this.graph = g; 
		this.G.init(g);
		initGUI();

	}
	private void initGUI()
	{
		this.setSize(1000, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		MenuBar MB=new MenuBar();this.setMenuBar(MB);
		Menu File = new Menu("File");
		Menu algo = new Menu("algo");
		Menu op = new Menu("operation");


		//creating the bar tabs

		MB.add(File);
		MB.add(algo);
		MB.add(op);

		//file tab\\
		MenuItem Save = new MenuItem("Save");     
		Save.addActionListener(this);

		MenuItem Load = new MenuItem("Load"); 		
		Load.addActionListener(this);

		MenuItem gfactory = new MenuItem("gfactory");
		gfactory.addActionListener(this);


		//algo tab
		MenuItem ShortPath = new MenuItem("Shortest Path"); 
		ShortPath.addActionListener(this);
		
		MenuItem ShortPathDist = new MenuItem("Shortest Path Dist"); 
		ShortPath.addActionListener(this);
		
		MenuItem isConnected = new MenuItem("isConnected"); 
		isConnected.addActionListener(this);

		MenuItem TSP = new MenuItem("TSP");			
		TSP.addActionListener(this);
		
		//operation tab
		
		MenuItem addV = new MenuItem("add vertix");
		addV.addActionListener(this);
		
		MenuItem addEdge = new MenuItem("add edge");
		addEdge.addActionListener(this);
		
		MenuItem removeV = new MenuItem("remove vertix");
		removeV.addActionListener(this);

		MenuItem removeEdge = new MenuItem("remove edge");
		removeEdge.addActionListener(this);
		//adding to tabs;
		//file
		File.add(Save);
		File.add(Load);
		File.add(gfactory);

		//algo 
		algo.add(ShortPath); 
		algo.add(TSP);
		algo.add(isConnected);
		algo.add(ShortPathDist);
		
		//operation
		op.add(addV);
		op.add(addEdge);
		op.add(removeV);
		op.add(removeEdge);
	}

	public void paint(Graphics g)
	{
		super.paint(g);

		Collection<node_data> v = graph.getV();
		java.util.Iterator<node_data> i =  v.iterator(); 

		while(i.hasNext())
		{
			node_data temp = (node_data) i.next();
			Point3D nodes_src =temp.getLocation();

			g.setColor(Color.BLUE);
			g.fillOval((int)nodes_src.x(), (int)nodes_src.y(),10,10);
			g.drawString(Integer.toString(temp.getKey()),(int)nodes_src.x(),(int)(nodes_src.y())+5);


			this.edges = graph.getE(temp.getKey());
			if(this.edges == null) continue;
			java.util.Iterator<edge_data> j =  edges.iterator(); 


			while(j.hasNext())
			{
				//draw the edges line in Red
				g.setColor(Color.RED);

				edge temp1 = (edge) j.next();

				Point3D nodes_dest = graph.getNode(temp1.getDest()).getLocation();
				g.drawLine(temp.getLocation().ix(), temp.getLocation().iy(),
						nodes_dest.ix(), nodes_dest.iy());
				//draw a point of the edges direction in Yellow
				g.setColor(Color.YELLOW);
				int dir_of_edge_x=(int) ((nodes_src.x()+4*nodes_dest.x())/5);
				int dir_of_edge_y=(int) ((nodes_src.y()+4*nodes_dest.y())/5);

				g.fillOval(dir_of_edge_x , dir_of_edge_y,10,10);

				//draw the edge weight in Black
				int mid_of_edge_x=(int) ((nodes_src.x()+nodes_dest.x())/2);
				int mid_of_edge_y=(int) ((nodes_src.y()+nodes_dest.y())/2);
				g.setColor(Color.BLACK);
				g.drawString(temp1.getWeight()+"",mid_of_edge_x, mid_of_edge_y);
			}
		}

		if(Choice == 1)
		{
			g.setColor(Color.BLACK);
			
			if(shortPD != 0)
			{
				g.drawString("The Path Weight is: "+ shortPD, 300, 300);
			}
		}

		if(Choice == 2) 
		{
			g.setColor(Color.BLACK);
			if(G.isConnected())
			{
				g.drawString("The graph is connected", 300, 300);
			}
			else
			{
				g.drawString("The graph is NOT connected", 300, 300);
			}
			Choice = 0;
		}

		if(Choice == 3)
		{
			if(vToPaint.size() != 0)
			{
				for (int j = 0; j < vToPaint.size()-1; j++) 
				{
					g.setColor(Color.GREEN);
					double x1 = vToPaint.get(j).getLocation().x();
					double y1 = vToPaint.get(j).getLocation().y();
					double x2 = vToPaint.get(j+1).getLocation().x();
					double y2 = vToPaint.get(j+1).getLocation().y();
					g.fillOval((int)x1,(int) y1, 10, 10);
					g.drawLine((int)x1,(int) y1,(int) x2,(int) y2);
				} 
			}
			Choice = 0;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if(e.getActionCommand().equals("Save"))
		{
			FileDialog chooser = new FileDialog(frame, "Use a .png or .jpg extension", FileDialog.SAVE);
			chooser.setVisible(true);
			String filename =chooser.getDirectory()+ chooser.getFile();
			if (filename != null)
			{
				G.save(filename);
				System.out.println("Graph Saved");
			}
		}

		if(e.getActionCommand().equals("Load")) 
		{
			FileDialog chooser = new FileDialog(frame, "Use a .png or .jpg extension", FileDialog.LOAD);
			chooser.setVisible(true);
			String filename = chooser.getDirectory()+chooser.getFile();
			if (filename != null)
			{
				this.graph= init(filename);
				this.G.init(graph);
				Choice= 0; 
				repaint();
			}
		}

		if(e.getActionCommand().equals("gfactory"))
		{
			Choice = 0;
			this.graph = graphFactory();
			this.G.init(graph);
			Choice=0;
			repaint();
		}

		if(e.getActionCommand().equals("Shortest Path")) 
		{
			Choice = 0;
			repaint();

			vToPaint.clear();
			JFrame f1 = new JFrame();
			String v1 = JOptionPane.showInputDialog(f1,"Enter source vertix");
			int v_src = 0;
			int v_dest = 0;
			
			if(v1!="") 
			{
				String v2 = JOptionPane.showInputDialog(f1,"Enter destination vertix");
				if(v2!="") 
				{
					try
					{
						v_src = Integer.parseInt(v1);
						v_dest = Integer.parseInt(v2);
						if(v_src != v_dest && graph.getNode(v_src) != null&& graph.getNode(v_dest) != null)
						{							
							vToPaint.addAll(G.shortestPath(v_src, v_dest));
						}
						
					} catch(Exception ex) {};
				}
			}
			
			Choice = 3;
			repaint();
		}

		if(e.getActionCommand().equals("TSP")) 
		{
			Choice = 0;
			repaint();

			vToPaint.clear();
			List<Integer> tsp = new ArrayList<>();
			JFrame f1 = new JFrame();
			String s_TSP = "";
			while(!s_TSP.equals("done") && tsp.size()<graph.getV().size())
			{
				s_TSP = JOptionPane.showInputDialog(f1,"Enter a vertix for the TSP List or done to apply");
				try
				{
					int v_TSP = Integer.parseInt(s_TSP);
					if((graph.getNode(v_TSP) != null ) && !tsp.contains(v_TSP))
					{
						tsp.add(v_TSP);
					}
				} catch(Exception ex)
				{
					System.out.println("illegal input!");
				}
			}

			vToPaint.addAll(G.TSP(tsp));

			Choice =3;
			repaint();

		}

		if(e.getActionCommand().equals("isConnected"))
		{
			Choice = 2;
			repaint();

		}
		
		if(e.getActionCommand().equals("Shortest Path Dist"))
		{
			Choice = 0;
			repaint();

			vToPaint.clear();
			JFrame f1 = new JFrame();
			String v1 = JOptionPane.showInputDialog(f1,"Enter source vertix");
			int v_src = 0;
			int v_dest = 0;
			
			if(v1!="") 
			{
				String v2 = JOptionPane.showInputDialog(f1,"Enter destination vertix");
				if(v2!="") 
				{
					try
					{
						v_src = Integer.parseInt(v1);
						v_dest = Integer.parseInt(v2);
						if(v_src != v_dest && graph.getNode(v_src) != null&& graph.getNode(v_dest) != null)
						{							
							shortPD = G.shortestPathDist(v_src, v_dest);
						}
						
					} catch(Exception ex) {};
				}
			}
			
			Choice = 1;
			repaint();
		}
		
		if(e.getActionCommand().equals("add vertix"))
		{
			Choice = 0;
			repaint();
			int v1 = 0;
			
			JFrame f1 = new JFrame();
			String v = JOptionPane.showInputDialog(f1,"Enter vertix id");
			try
			{
				v1 = Integer.parseInt(v);
				Point3D p = new Point3D(1,1);
				node_data n = new node(v1,p);
				graph.addNode(n);
				
			}catch(Exception ex) {};
		}
		
		if(e.getActionCommand().equals("add edge"))
		{
			
		}
		
		if(e.getActionCommand().equals("remove vertix"))
		{
			
		}
		
		if(e.getActionCommand().equals("remve edge"))
		{
			
		}

//		public Point3D nodeCreator(double x,double y)
//		{
//				
//			return null;
//		}





	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public graph init(String file_name) 
	{
		try
		{    
			FileInputStream file = new FileInputStream(file_name); 
			ObjectInputStream objInput = new ObjectInputStream(file); 
			graph = (graph)objInput.readObject(); 
			objInput.close(); 
			file.close(); 
			return graph;
		} 
		catch(IOException e) 
		{ 
			e.printStackTrace();
		} 
		catch(ClassNotFoundException e) 
		{ 
			e.printStackTrace(); 
		}
		return null; 

	}
	//create random graph
	public graph graphFactory()
	{
		graph gr = new DGraph();
		int size = 10;

		for (int i = 0; i < size; i++) 
		{
			double p1 = Math.random()*500+70;
			double p2 = Math.random()*500+70;
			gr.addNode(new node(i,new Point3D(p1,p2),0));
		}

		for (int i = 0; i < size*3; i++) 
		{
			int src = (int)(Math.random()*size);
			int dest = (int)(Math.random()*size);
			if(src == dest && gr.getEdge(src, dest) != null)
			{
				break;
			}
			else
			{				
				gr.connect(src,dest, Math.random()*20+1);
			}
		}
		return gr;
	}
	@Override
	public void update(java.util.Observable o, Object arg) 
	{
		repaint();
		
	}
}
