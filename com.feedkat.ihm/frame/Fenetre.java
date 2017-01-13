package frame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ConstantsAndMethods.ConstantsAndMethods;

public class Fenetre extends JFrame {
    /**
	 * @author G.Nivert
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel GraphPane = new JPanel();
	Interface interFace;
	DynamicGraphCat chart;
	ImageIcon img = new ImageIcon(ConstantsAndMethods._IconApp_FeedKat);

	public Fenetre(){
		
		interFace = new Interface();
		interFace.setPreferredSize(new Dimension(1200,200));
		
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

