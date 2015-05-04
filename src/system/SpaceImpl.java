package system;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import util.BlockingList;
import api.RemoteComputer;
import api.Result;
import api.Space;
import api.Task;

import java.util.concurrent.LinkedBlockingQueue;

public class SpaceImpl extends UnicastRemoteObject implements Space {
	
	private LinkedBlockingQueue<Task<?>> tasks;
	private BlockingList<Task<?>> waitingTasks;
	private BlockingList<Result<?>> results;
	private HashMap<UUID, Task<?>> waitingTaskMap;
	private HashMap<UUID, Result<?>> resultMap;
	int i = 0;
	int j = 0;
	protected SpaceImpl() throws RemoteException {
		super();
		tasks = new LinkedBlockingQueue<Task<?>>();
		waitingTasks = new BlockingList<Task<?>>();
		results = new BlockingList<Result<?>>();
		waitingTaskMap = new HashMap<UUID, Task<?>>();
		resultMap = new HashMap<UUID, Result<?>>();
	}

	@Override
	public void putTask(Task<?> task) throws RemoteException{
			tasks.add(task);
			i += 1;
	}
	@Override
	public <T> void putTasks(List<Task<T>> tasks) throws RemoteException {
		this.tasks.addAll(tasks);
		i += tasks.size();
	}

	@Override
	public void putWaitingTask(Task<?> task) throws RemoteException {
		this.waitingTaskMap.put(task.getUUID(), task);
	}
	

	@Override
	public <A extends Result<?>> void registerResult(A result) throws RemoteException {
			boolean isSubgoal = false;
			synchronized(waitingTaskMap){
			if (waitingTaskMap.containsKey(result.getOwner())){
				Task<?> task = waitingTaskMap.get(result.getOwner());
				if (task.registerResult(result)){
					isSubgoal = true;
					synchronized(tasks){
						tasks.add(task);
					}
					waitingTaskMap.remove(task.getUUID());
					System.out.println(waitingTaskMap.size());
				}
			}
			}

			if (isSubgoal == false){
				resultMap.put(result.getOwner(), result);
			}
	}
	
	
	@Override
	public void register(RemoteComputer comp) throws RemoteException {
		System.out.println("got computer");
		(new Thread(new ComputerProxy(comp, this.tasks))).start();
	}

	@Override
	public Result<?> takeResult(UUID uuid) {
		Result<?> result = resultMap.get(uuid);
		if (result != null)
			return result;
		return null;
	}
	
	

	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new SecurityManager());
		LocateRegistry.createRegistry(Space.PORT).rebind(Space.SERVICE_NAME, new SpaceImpl());
	}

	@Override
	public void putUnfinishedTasks(List<Task<?>> tasks) throws RemoteException {
		System.out.println("computer crashed, got unfinished tasks");
		System.out.println(tasks);
		this.tasks.addAll(tasks);		
	}




}
