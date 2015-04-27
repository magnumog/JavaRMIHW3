package system;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import util.BlockingList;
import api.RemoteComputer;
import api.Result;
import api.Space;
import api.Task;

public class SpaceImpl extends UnicastRemoteObject implements Space {
	
	private BlockingList<Task<?>> tasks;
	private BlockingList<Result<?>> results;
	private BlockingList<Task<?>> waitingTasks;
	
	protected SpaceImpl() throws RemoteException {
		super();
		tasks = new BlockingList<Task<?>>();
		waitingTasks = new BlockingList<Task<?>>();
	}

	@Override
	public void putTask(Task<?> task) throws RemoteException{
			tasks.addB(task);
	}
	@Override
	public <T> void putTasks(List<Task<T>> tasks) throws RemoteException {
		this.tasks.addAllB(tasks);	
	}

	
	@Override
	public void register(RemoteComputer comp) {
		new ComputerProxy(comp, tasks).run();
	}

	@Override
	public List<Result<?>> takeResults() {
		
		return null;
	}
	
	

	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new SecurityManager());
		LocateRegistry.createRegistry(Space.PORT).rebind(Space.SERVICE_NAME, new SpaceImpl());
	}





}
