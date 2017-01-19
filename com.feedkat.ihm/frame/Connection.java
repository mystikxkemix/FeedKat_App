package frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ConstantsAndMethods.ConstantsAndMethods;
import Model.CatBasics;
import WebRequest.FeedKatHttpRequest;
import WebRequest.HttpRequest;
import WebRequest.onResponse;
import WebRequest.onResponse.SuccessListener;

public class Connection extends JFrame implements ActionListener, MouseListener {
	   /**
		* @author G.Nivert
		*/
		private static final long serialVersionUID = 1L;

	private static int BLEisConnect = 0;
		
		JButton connection;
		JLabel connect;
		
		
		public Connection (){
			
			//connect = new JLabel("Click to connect on BLE", SwingConstants.CENTER);
			connection = new JButton("Connection");
			
			Icon icon = new ImageIcon(ConstantsAndMethods._Bluetooth_Gif);
			JLabel BLE = new JLabel(icon);

			JPanel p1;
			
			p1 = new JPanel();
			p1.setLayout(new BorderLayout());
			p1.add(BLE, BorderLayout.NORTH);
			//p1.add(connect);
			p1.add(connection, BorderLayout.SOUTH);
			
			connection.addActionListener(this);
			BLE.addMouseListener(this);
			
			Container c = this.getContentPane();
			c.add(p1);
			
			this.setTitle("Connection BLE");
			this.setSize(400,364);
			this.setVisible(true);
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			BLEisConnect = 1;
			if(e.getSource()==connection)
			{
				System.out.println("attempting to connect");
				//Connection au module BLE
				if(BLEisConnect == 1)
				{	
				
					FeedKatHttpRequest.getCatBasics("FKC001", new SuccessListener<CatBasics>() {
						@Override
						public void onSuccess(CatBasics result) {
							System.out.println("Cat : " + result);
							
						}
					});
					
					System.out.println("BLE Connected");
					JOptionPane.showMessageDialog(null, "Connected !");
					this.dispose();
					Fenetre f = new Fenetre();
					f.setVisible(true);
				}
				else
					JOptionPane.showMessageDialog(null, "Connection failed !");
			}
		}
		@Override
		public void mousePressed(MouseEvent e){
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			System.out.println("CLICK");
			System.exit(0);
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
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		
}