package tasks;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

public class Client<V,T> extends JFrame {
	private long clientStartTime;
	
	public Client() throws RemoteException,NotBoundException,MalformedURLException {
		
	}
	
	public void begin() {
		clientStartTime = System.nanoTime();
	}
	
	public void end() {
		Logger.getLogger(Client.class.getCanonicalName()).log(Level.INFO, "Client time: {0}", System.nanoTime()-clientStartTime/1000000);
	}
	
	
}
