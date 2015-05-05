package system;

import java.util.List;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import api.RemoteComputer;
import api.Space;
import api.Task;

public class Computer extends UnicastRemoteObject implements RemoteComputer {
	private final Space space;
	private boolean multicore;
	private boolean acm;
	public int availableProcessors;
	private final LinkedBlockingQueue<Task<?>> tasks;
	private ArrayList<TaskExecutor> taskExecutors;

	public Computer(String spaceDomain, boolean multicore, boolean acm) throws RemoteException, NotBoundException, MalformedURLException{
		String url = "rmi://" + spaceDomain + ":" + Space.PORT + "/" + Space.SERVICE_NAME;
		space = (Space) Naming.lookup( url );
		space.register(this);
		this.multicore = multicore;
		tasks = new LinkedBlockingQueue<Task<?>>();
		taskExecutors = new ArrayList<TaskExecutor>();
		this.acm = acm;
		
		
		if (multicore){
			availableProcessors = Runtime.getRuntime().availableProcessors();
		}
		else {
			availableProcessors = 1;
		}
		
		for (int i = 0; i < availableProcessors; i++){
			TaskExecutor te = new TaskExecutor(this.space, this.tasks);
			taskExecutors.add(te);
			new Thread(te).start();
		}
		
		
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable(){
			Space shutspace = space;
			LinkedBlockingQueue<Task<?>> shutTasks = tasks;
			public void run(){
				List<Task<?>> tasksToSend = new ArrayList<Task<?>>();
				for (Task<?> task : shutTasks){
					tasksToSend.add(task);
				}
				
				for (TaskExecutor te : taskExecutors){
					Task t = te.getCurrentTask();
					if (t != null)
						tasksToSend.add(te.getCurrentTask());
				}
				
				try {
					shutspace.putUnfinishedTasks(tasksToSend);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}));
		
	}
	
	


	public <T> void execute(Task<T> task) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println(task);
		task.execute().apply(left -> {
			try {
				space.putWaitingTask(task);
				space.putTasks(left);
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
	}
	
	
	public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException{
		
		System.setSecurityManager( new SecurityManager() );
        
        String domain;
        boolean multicore = false;
        boolean acm = false;
        if(args.length == 0){
        	domain = "localhost";
        }
        else {
        	domain = args[0];
        	multicore = ComputerHelper.boolInputHelper(args[1]);
        	acm = ComputerHelper.boolInputHelper(args[2]);
        	
        }
        
        System.out.println(multicore);
        System.out.println(acm);
        
        final Computer client = new Computer(domain, multicore, acm);	
    }

	public <T> void addToComputerQueue(Task<T> task) throws RemoteException {
		tasks.add(task);
	}

	@Override
	public <T> void handleTask(Task<T> task) throws RemoteException {
		if (acm){
			addToComputerQueue(task);
		}
		else {
			this.execute(task);
		}
	}
}
