package system;


import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import util.BlockingList;
import api.Task;
import api.RemoteComputer;
import api.Result;

public class ComputerProxy implements Runnable{
	private RemoteComputer computer;
	private LinkedBlockingQueue<Task<?>> taskList;
	public ComputerProxy(RemoteComputer computer, LinkedBlockingQueue<Task<?>> taskList){
		this.computer =  computer;
		this.taskList = taskList;
	}

	public void run(){
		while(true){
			if (!taskList.isEmpty()){
				Task<?> task = null;	
					try {
						task = taskList.poll();
						if (task != null){
							computer.handleTask(task);
						}
					}
					catch(Exception e){
						taskList.add(task);
						System.out.println(e);
						return;
					}
			}
		}
	}
}