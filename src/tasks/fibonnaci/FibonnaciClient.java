package tasks.fibonnaci;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import tasks.Client;

public class FibonnaciClient extends Client {

	public FibonnaciClient() throws RemoteException, NotBoundException, MalformedURLException {
		super();
	}
	
	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new SecurityManager());
		String domain;
		if(args.length==0) {
			domain = "localhost";
		} else {
			domain = args[0];
		}
		
		final FibonnaciClient client = new FibonnaciClient();
		client.begin();
		final List<Integer> value = client.processJob();
//		client.add();
		client.end();
	}

}
