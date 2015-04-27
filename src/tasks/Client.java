package tasks;

import java.awt.BorderLayout;
import java.awt.Container;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class Client<V> extends JFrame {
	private long clientStartTime;
	
	public Client() throws RemoteException,NotBoundException,MalformedURLException {
		
	}
	
	public void begin() {
		clientStartTime = System.nanoTime();
	}
	
	public void end() {
		Logger.getLogger(Client.class.getCanonicalName()).log(Level.INFO, "Client time: {0}", System.nanoTime()-clientStartTime/1000000);
	}
	
	public List<Integer> processJob() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void add(JLabel jlabel) {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add( new JScrollPane(jlabel),BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
}
