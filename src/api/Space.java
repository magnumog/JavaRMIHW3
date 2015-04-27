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
	public <T> void putTasks(List<Task<T>> tasks) throws RemoteException;
	
	
	public void putWaitingTask(Task<?> task) throws RemoteException;
	public <A extends Result<?>> void registerResult(A result) throws RemoteException;

	public List<Result<?>> takeResults();
	
	
	public void register(RemoteComputer comp);
	
}
