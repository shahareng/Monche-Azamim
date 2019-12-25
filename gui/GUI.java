package gui;
import dataStructure.*;
import utils.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener 
{
    private static DGraph graph;
    public static void main(String[] args)
    {
        graph=new DGraph();
        graph.addNode(new node(10,new Point3D(100,100,150),0));
        graph.addNode(new node(11,new Point3D(135,125,130),0));
        graph.addNode(new node(12,new Point3D(120,300,200),0));
        graph.addNode(new node(13,new Point3D(150,200,100),0));
        graph.addNode(new node(14,new Point3D(75,250,250),0));
        graph.connect(10,13,0);
        graph.connect(10,14,0);
        graph.connect(11,10,0);
        graph.connect(11,13,0);
        graph.connect(12,11,0);
        graph.connect(13,14,1);
        graph.connect(13,12,1.5);
        graph.connect(14,13,0);
        GUI gui = new GUI();
        gui.setVisible(true);
    }
    public GUI()
    {
        INITGUI();
    }
    private void INITGUI()
    {
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuBar MB=new MenuBar();this.setMenuBar(MB);
        Menu File = new Menu("File"); MB.add(File);
        MenuItem Save = new MenuItem("Save");
        MenuItem Load = new MenuItem("Load");
        File.add(Save);File.add(Load);
        Save.addActionListener(this);
        Load.addActionListener(this);
    }
    public void paint(Graphics g)
    {
        super.paint(g);

        for(node_data nodes : graph.getV()) 
        {
            Point3D nodes_src = nodes.getLocation();
            g.setColor(Color.BLUE);
            g.fillOval((int)nodes_src.x()-5, (int)nodes_src.y()-5, 10, 10);
            g.drawString(""+ nodes.getKey(), (int)nodes_src.x(),(int)(nodes_src.y()+20));

            for(edge_data edges : graph.getE(nodes.getKey())) 
            {
                g.setColor(Color.RED);
                Point3D nodes_dest = graph.getNode(edges.getDest()).getLocation();
                g.drawLine((int)nodes_src.x(), (int)nodes_src.y(), (int)nodes_dest.x(), (int)nodes_dest.y());
                int mid_of_edge_x=(int) ((nodes_src.x()+nodes_dest.x())/2);
                int mid_of_edge_y=(int) ((nodes_src.y()+nodes_dest.y())/2);
                g.drawString(""+ edges.getWeight(), mid_of_edge_x, mid_of_edge_y);


                /*g.setColor(Color.YELLOW);
                Point3D start, end;
                double z;
                if(nodes_src.x()<nodes_dest.x()) {
                    start = nodes_src;
                    end = nodes_dest;
                    z = 20;
                }
                else {
                    start = nodes_dest;
                    end = nodes_src;
                    z = -20;
                }
                double m = (end.y()-start.y()) / (end.x()-start.x());
                double nn = m*(-start.x()) + start.y();
                int y = (int)(m*(nodes_src.x()+z)+nn);
                int x = (int)(nodes_src.x()+z);
                g.fillOval(x, y, 10, 10);*/
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String str=e.getActionCommand();
        if(str.equals("Save"))
        {
            repaint();
            System.out.println("Graph Saved!");
        }
        if(str.equals("Load"))
        {
            repaint();
            System.out.println("Graph Loaded!");
        }
    }
}
