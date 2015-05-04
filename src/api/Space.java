package api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

/**
 * 
 * Space interface
 */
public interface Space extends Remote {
	public static int PORT = 8001;
	public static String SERVICE_NAME = "Space";
	
	public void putTask(Task<?> task) throws RemoteException;
	public <T> void putTasks(List<Task<T>> tasks) throws RemoteException;
	
	public void putUnfinishedTasks(List<Task<?>> tasks) throws RemoteException;
	
	public void putWaitingTask(Task<?> task) throws RemoteException;
	
	public <A extends Result<?>> void registerResult(A result) throws RemoteException;

	public Result<?> takeResult(UUID uuid) throws RemoteException;
	
	public void register(RemoteComputer comp) throws RemoteException;
	
}
