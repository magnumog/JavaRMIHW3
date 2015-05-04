package system;
import java.rmi.RemoteException;
import java.util.concurrent.LinkedBlockingQueue;

import api.Space;
import api.Task;

public class TaskExecutor extends Thread implements Runnable {

	Space space;
	private LinkedBlockingQueue<Task<?>> tasks;
	private Task<?> currentTask;
	public TaskExecutor(Space space, LinkedBlockingQueue<Task<?>> tasks){
		this.space = space;
		this.tasks = tasks;
	}
	
	public <T> void execute(Task<T> task) {
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
		
		currentTask = null;
	}
	
	
	
	public Task<?> getCurrentTask(){
		return this.currentTask;
	}
	
	
	
	@Override
	public void run() {
		while(true){
			if (!tasks.isEmpty()){
				try {
					currentTask = tasks.poll();
					if (currentTask != null)
						this.execute(currentTask);
				}
				
				catch(Exception e){
					currentTask = null;
					e.printStackTrace();
				}
			}
		}
	}

}
