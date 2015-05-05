package tasks.fibonnaci;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import tasks.Client;



public class FibonnaciClient extends Client<FibonacciResult> {

	public FibonnaciClient(String title, String domain) throws RemoteException, NotBoundException, MalformedURLException {
		super(title, domain);
	}
	
	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new SecurityManager());
		String domain;
		if(args.length==0) {
			domain = "localhost";
		} else {
			domain = args[0];
		}
		final FibonnaciClient client = new FibonnaciClient("Fibonacci", domain);
		client.begin();
		final FibonnaciTask task = new FibonnaciTask(14);
		System.out.println(client.processTask(task));
		client.end();
		
//		client.add();
	}

}
