package system;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import api.Result;
import api.Space;
import api.Task;

public class SpaceImpl extends UnicastRemoteObject implements Space {

	protected SpaceImpl() throws RemoteException {
		super();
	}

	@Override
	public void putTask(Task<?> task) throws RemoteException{

		// TODO Auto-generated method stub
		
	}

	@Override
	public void putTasks(List<Task<?>> tasks) throws RemoteException {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void putResult(Result<?> result) throws RemoteException {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void putResults(List<Result<?>> results) throws RemoteException {

		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Result<?>> takeResults() {

		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new SecurityManager());
		LocateRegistry.createRegistry(Space.PORT).rebind(Space.SERVICE_NAME, new SpaceImpl());
	}
}
