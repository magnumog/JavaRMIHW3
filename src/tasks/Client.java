package tasks;

import java.awt.BorderLayout;
import java.awt.Container;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import api.Result;
import api.Space;
import api.Task;

public class Client<V> extends JFrame {
	private static final long serialVersionUID = 1L;
	private long clientStartTime;
	private Space space;
	
	public Client(String title, String domainName) throws RemoteException,NotBoundException,MalformedURLException {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String url = "rmi://" + domainName + ":" + Space.PORT + "/" + Space.SERVICE_NAME;
		this.space = (Space)Naming.lookup(url);
	}
	
	public void begin() {
		clientStartTime = System.nanoTime();
	}
	
	public void end() {
		Logger.getLogger(Client.class.getCanonicalName()).log(Level.INFO, "Client time: {0}", System.nanoTime()-clientStartTime/1000000);
	}
	
	public V processJob() throws RemoteException {
		//HOW TO DO THIS
		return null;
	}
	
	
	public V processTask(Task<?> task) throws RemoteException{
		
		space.putTask(task);
		UUID uuid = task.getParentUUID();
		Result<?> res = null;
		while ((res = space.takeResult(uuid)) == null){
			
		}
		System.out.println(res);
		return (V) res.value();
		
	}
	
	public void add(JLabel jlabel) {
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		container.add( new JScrollPane(jlabel),BorderLayout.CENTER);
		pack();
		setVisible(true);
	}
}
