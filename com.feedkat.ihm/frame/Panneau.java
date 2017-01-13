package frame;

import javax.swing.JPanel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import ConstantsAndMethods.ConstantsAndMethods;


public class Panneau extends JPanel {
   /**
	* @author G.Nivert
	*/
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
    
		int x = 0;
		
		try {
			Image img = ImageIO.read(new File(ConstantsAndMethods._Logo_FeedKat));
			g.drawImage(img, 25,10, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
    
		Graphics2D g2D = (Graphics2D)g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        
		g.setFont(ConstantsAndMethods._Font_Arial_Rounded_MT_Bold_50);
    
		g.setColor(ConstantsAndMethods._Color_Orange);
		g.drawString("Blue",370,60);
    
		g.setColor(ConstantsAndMethods._Color_Blue);
		g.drawString("Tooth",490,60);
        
		g.setFont(ConstantsAndMethods._Font_Arial_Rounded_MT_Bold_20);
		g.setColor(Color.black);
		g.drawString("Collier n° : "+ ConstantsAndMethods._Collar_ID,80,160);
		g.drawString("Nom : " + ConstantsAndMethods._Cat_Name,80,280);
		g.drawString("Âge : " + ConstantsAndMethods._Cat_Age,400,280);
		g.drawString("Niveau de Pile",80,220);
		g.drawString(ConstantsAndMethods._Battery_Value + "%", 670,220);
		g.drawString("Poids : " + ConstantsAndMethods._Cat_Weight + " Kg",700,280);
    
		if (ConstantsAndMethods._Battery_Value >= 0 && ConstantsAndMethods._Battery_Value <= 100){
			x = ConstantsAndMethods._Battery_Value * 4;
			//System.out.println("x = :" + x);
		}
        
		g.drawRoundRect(249,204,401,21,10,10);
    
		if (ConstantsAndMethods._Battery_Value > 40){
			g.setColor(Color.green);
		} else if (ConstantsAndMethods._Battery_Value <= 40 && ConstantsAndMethods._Battery_Value >= 15) {
			g.setColor(Color.orange);
		} else {
			g.setColor(Color.red);
		}
		g.fillRoundRect(250,205,x,20,10,10);
    }
	
}
