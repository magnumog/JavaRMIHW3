package system;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
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
	int i = 0;
	protected SpaceImpl() throws RemoteException {
		super();
		tasks = new LinkedBlockingQueue<Task<?>>();
		waitingTasks = new BlockingList<Task<?>>();
		results = new BlockingList<Result<?>>();
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
		this.waitingTasks.addB(task);
	}
	

	@Override
	public <A extends Result<?>> void registerResult(A result) throws RemoteException {
		synchronized(waitingTasks){
			ArrayList<Task<?>> tasksToRemove = new ArrayList<Task<?>>();
			boolean isSubgoal = false;
			for (Task<?> task: waitingTasks){
				if (result.getOwner().toString().equals(task.getUUID().toString())){
					isSubgoal = true;
					if (task.registerResult(result)){
						tasksToRemove.add(task);
						tasks.add(task);
					}
				}
			}
			
			if (isSubgoal == false){
				results.add(result);
			}
			waitingTasks.removeAll(tasksToRemove);
		}
	}
	
	
	@Override
	public void register(RemoteComputer comp) throws RemoteException {
		(new Thread(new ComputerProxy(comp, this.tasks))).start();
	}

	@Override
	public Result<?> takeResult(UUID uuid) {
		synchronized(results){
			for (Result<?> result: results){
				if (result.getOwner().equals(uuid)){
					System.out.println(i);
					return result;
				}
			}
		}
		return null;
	}
	
	

	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new SecurityManager());
		LocateRegistry.createRegistry(Space.PORT).rebind(Space.SERVICE_NAME, new SpaceImpl());
	}




}
