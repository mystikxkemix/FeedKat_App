package frame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


import ConstantsAndMethods.ConstantsAndMethods;

public class Fenetre extends JFrame {
    /**
	 * @author G.Nivert
	 */
	private static final long serialVersionUID = 1L;
	
	Interface interFace;
	DynamicGraphCat chart;
	ImageIcon img;
	
	public Fenetre()
	{
		img = new ImageIcon(ConstantsAndMethods._IconApp_FeedKat);

		interFace = new Interface();
		interFace.setPreferredSize(new Dimension(1200,250));
		
		chart = new DynamicGraphCat("Cat Activities");
		
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		cp.add(interFace);
		cp.add(chart);
		this.setIconImage(img.getImage());
		this.setTitle(ConstantsAndMethods._Window_Title);
		this.setSize(1200,800);
		this.setLocationRelativeTo(null);
		//this.setAlwaysOnTop(true);
		//fenetre.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.pack();
		this.setVisible(true);
    
    }
	

}

