package gui;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class Main
{

	public static void main(String[] args) 
	{
		JMenuBar menu = new JMenuBar();
	//	menu = createMenuBar();
		Window window = new Window();
		window.setVisible(true);
	}

}
