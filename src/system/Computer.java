package system;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import api.RemoteComputer;
import api.Space;
import api.Task;

public class Computer extends UnicastRemoteObject implements RemoteComputer {
	
	

	private Space space;
	public Computer(String spaceDomain) throws RemoteException, NotBoundException, MalformedURLException{
		String url = "rmi://" + spaceDomain + ":" + Space.PORT + "/" + Space.SERVICE_NAME;
		space = (Space) Naming.lookup( url );
		space.register(this);
	}

	@Override
	public <T> void execute(Task<T> task) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(task);
		task.execute().apply(left -> {
			try {
				space.putTasks(left);
				space.putWaitingTask(task);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, 
		right -> {
			try {
				space.registerResult(right);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	
	
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException{
		System.setSecurityManager( new SecurityManager() );
        
        String domain;
        if(args.length == 0){
        	domain = "localhost";
        }
        else {
        	domain = args[0];
        }
        
        
        final Computer client = new Computer(domain);	
    }
}
