package system;


import util.BlockingList;
import api.Task;
import api.RemoteComputer;
import api.Result;

public class ComputerProxy implements Runnable{
	private RemoteComputer computer;
	private BlockingList<Task<?>> taskList;
	
	public ComputerProxy(RemoteComputer computer, BlockingList<Task<?>> taskList){
		this.computer =  computer;
		this.taskList = taskList;
	}

	public void run(){
		while(true){
			if (!taskList.isEmpty()){
				Task<?> task = taskList.get(0);
				try {
					this.taskList.removeB(task);
					computer.execute(task);
				}
				catch(Exception e){
					this.taskList.addB(task);
					System.out.println(e);
					return;
				}
			}
		}
	}
}