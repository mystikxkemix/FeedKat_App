package ConnectionBluetooth;

import java.time.LocalDate;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bluez.Adapter1;
import org.bluez.Device1;
import org.bluez.GattCharacteristic1;
import org.bluez.GattService1;

import de.serviceflow.codegenj.*;

public class CollarBluetooth implements Runnable{

	/** Constructor */
	public CollarBluetooth() {
	}

	@Override
	public void run() {
		try {
			waitForConnection();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void waitForConnection() throws InterruptedException {

	    ObjectManager m = ObjectManager.getInstance();
	    ObjectManager.getLogger().setLevel(Level.FINE);         

	    // Show what's on the bus:
	    m.dump();

	    List<Adapter1> adapters = m.getAdapters();
	    System.out.println(" ==> # = " + adapters.size());

	    // Find our bluetooth adapter, and start Discovery ...
	    Adapter1 defaultAdapter = null;
	    for (Adapter1 a : adapters) {
	        System.out.println(" ==> Adapter: " + a.getName());
	        try {
	            a.startDiscovery();
	        } catch (IOException e) {
	        	System.out.println(" ... ignored.");
	            continue;
	        }
	        defaultAdapter = a;
	    }
	    
	    if (defaultAdapter==null) {
	        System.out.println("no useable adapter found. Exit.");
	        return;
	    }

	    // Wait for devices to be discovered
	    Thread.sleep(5000);

	    Adapter1 a = defaultAdapter;
	        for (Device1 d : a.getDevices()) {
	            if ("FKC001".equals(d.getName())) {
	                System.out.println(" ==> Device: " + d.getName());
	                try {
	                    if (!d.getConnected()) {
	                        d.connect();
	                        System.out.println(" ... connected.");
	                    }
	                    else {
	                        System.out.println(" ... already connected.");
	                    }
	                } catch (IOException e) {
	                    System.out.println(" ... ignored: "+e.getMessage());
	                }
	            }
	            else {
	                System.out.println(" --> Device " + d.getName()+" skipped.");
	            }
	        }

	    // Use the API to traverse through the tree.
	    System.out.println("*** Object Tree:");
	    for (Adapter1 adap : adapters) {
	        System.out.println(" ... adapter "+adap.getObjectPath()+"  "+adap.getName());
	        for (Device1 d : adap.getDevices()) {
	            System.out.println("  .. device "+d.getObjectPath()+"  "+d.getName());
	            for (GattService1 s :  d.getServices()) {
	                System.out.println("   . service "+s.getObjectPath()+"  "+s.getUUID());
	                for (GattCharacteristic1 c :  s.getCharacteristics()) {
	                    System.out.println("    . char "+c.getObjectPath()+"  "+c.getUUID());
	                }
	            }
	        }
	    }

	    try {
	        defaultAdapter.stopDiscovery();
	        System.out.println(" ... stopped.");
	    } catch (IOException e) {
	        System.out.println(" ... ignored.");
	    }

	}
}