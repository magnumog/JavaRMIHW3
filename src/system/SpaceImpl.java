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
	}

	@Override
	public void putTasks(List<Task<?>> tasks) {
	}

	@Override
	public void putResult(Result<?> result) {
	}

	@Override
	public void putResults(List<Result<?>> results) {
		
	}

	@Override
	public List<Result<?>> takeResults() {
		return null;
	}

}
