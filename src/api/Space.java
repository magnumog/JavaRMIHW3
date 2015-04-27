package api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * 
 * Space interface
 */
public interface Space extends Remote {
	public static int PORT = 8001;
	public static String SERVICE_NAME = "Space";
	
	public void putTask(Task<?> task) throws RemoteException;
	public void putTasks(List<Task<?>> tasks) throws RemoteException;
	
	public void putResult(Result<?> result) throws RemoteException;
	public void putResults(List<Result<?>> results) throws RemoteException;
	
	public List<Result<?>> takeResults();

	
}
