package ConnectionBluetooth;

import java.time.LocalDate;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.bluetooth.*;
import javax.microedition.io.Connection;

import org.bluez.Adapter1;
import org.bluez.Device1;
import org.bluez.GattCharacteristic1;
import org.bluez.GattService1;

import WebRequest.onResponse.SuccessListener;
import de.serviceflow.codegenj.ObjectManager;
import tinyb.BluetoothDevice;
import tinyb.BluetoothGattService;
import tinyb.BluetoothManager;

public class CollarBluetooth{

	    protected ArrayList<RemoteDevice> devicesDiscovered = new ArrayList();
	    protected final Object inquiryCompletedEvent = new Object();
	    private LocalDevice local;
	    private DiscoveryAgent discoveryAgent;

	    public CollarBluetooth()
	    {
	    	try {
	    		BluetoothDevice collar = getDevice("FeedKat_C001");
	    		getService(collar, "0xA000");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	    }

	    static BluetoothDevice getDevice(String address) throws InterruptedException  {
	        BluetoothManager manager = BluetoothManager.getBluetoothManager();
	        BluetoothDevice sensor = null;
	        for (int i = 0; (i < 15) && true; ++i) {
	            List<BluetoothDevice> list = manager.getDevices();
	             for (BluetoothDevice device : list) {
	                System.out.println("Name "+device.getName());
	                /*
	                 * Here we check if the address matches.
	                 */
	                if (device.getAddress().equals(address))
	                    sensor = device;
	            }
	            if (sensor != null) {
	                return sensor;
	            }
	            Thread.sleep(4000);
	        }
	        return null;
	    }
	    
	    static BluetoothGattService getService(BluetoothDevice device, String 
	    		 UUID) throws InterruptedException {
	    		    System.out.println("Services exposed by device:");
	    		    BluetoothGattService tempService = null;
	    		    List<BluetoothGattService> bluetoothServices = null;
	    		    do {
	    		        bluetoothServices = device.getServices();
	    		        for (BluetoothGattService service : bluetoothServices) {
	    		            System.out.println("UUID: " + service.getUUID());
	    		            if (service.getUUID().equals(UUID))
	    		                tempService = service;
	    		        }
	    		        Thread.sleep(4000);
	    		    } while (bluetoothServices != null && bluetoothServices.isEmpty() && true);
	    		    return tempService;
	    		}
	    
	} // class RemoteDeviceDiscovery