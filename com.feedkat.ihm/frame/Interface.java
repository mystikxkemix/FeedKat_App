package frame;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import ConstantsAndMethods.ConstantsAndMethods;


public class Interface extends JPanel {
   /**
	* @author G.Nivert
	*/
	private static final long serialVersionUID = 1L;


	public void paintComponent(Graphics g){
    
		int x = 0;
		
		Dimension d = this.getSize();
		if (ConstantsAndMethods.cat==null){

			return;
		}
		
		super.paintComponent(g);
		
		try {
			Image img = ImageIO.read(new File(ConstantsAndMethods._Logo_FeedKat));
			g.drawImage(img, 25,10, 100,100, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        
		g.setFont(ConstantsAndMethods._Font_Arial_Rounded_MT_Bold_50);
    
		g.setColor(ConstantsAndMethods._Color_Orange);
		g.drawString("Cat",(d.width/2)-100,60);
    
		g.setColor(ConstantsAndMethods._Color_Blue);
		g.drawString("BLE",(d.width/2),60);
        
		g.setFont(ConstantsAndMethods._Font_Arial_Rounded_MT_Bold_20);
		g.setColor(Color.black);
		g.drawString("Collar n° : "+ ConstantsAndMethods._Collar_ID,150,70);
		g.drawString("Name : " + ConstantsAndMethods.cat.getName(),200,170);
		g.drawString("Birthday : " + ConstantsAndMethods.cat.getBirthDate(),500,170);
		g.drawString("Battery level",200,120);
		g.drawString(ConstantsAndMethods._Battery_Value + "%", 810,122);
		g.drawString("Weight : " + ConstantsAndMethods.cat.getWeight(),800,170);
		g.drawString("Activity : "+ ConstantsAndMethods._Activity, 800, 230);
    
		if (ConstantsAndMethods._Battery_Value >= 0 && ConstantsAndMethods._Battery_Value <= 100){
			x = ConstantsAndMethods._Battery_Value * 4;
			//System.out.println("x = :" + x);
		}
        
		g.drawRoundRect(389,104,401,21,10,10);
    
		if (ConstantsAndMethods._Battery_Value > 40){
			g.setColor(Color.green);
		} else if (ConstantsAndMethods._Battery_Value <= 40 && ConstantsAndMethods._Battery_Value >= 15) {
			g.setColor(Color.orange);
		} else {
			g.setColor(Color.red);
		}
		g.fillRoundRect(390,105,x,20,10,10);
    }
	
}
