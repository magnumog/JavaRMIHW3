package system;

import java.rmi.RemoteException;
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
	public void putTask(Task<?> task) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void putTasks(List<Task<?>> tasks) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void putResult(Result<?> result) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public void putResults(List<Result<?>> results) {

		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Result<?>> takeResults() {

		// TODO Auto-generated method stub
		return null;
	}

}
