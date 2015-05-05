package tasks;
import java.util.UUID;

import tasks.AbstractTask;
import api.Result;
import api.Task;

import java.util.concurrent.LinkedBlockingQueue;


public abstract class AbstractTask<T> implements Task<T>{
	
	
	
	protected UUID parentuuid;
	
	protected UUID uuid;
	
	
	protected LinkedBlockingQueue<Result<T>> results;

		
	public AbstractTask(UUID uuid){
		this.parentuuid = uuid;
		this.uuid = UUID.randomUUID();
		this.results = new LinkedBlockingQueue<Result<T>>();

	}
	

	
	
	
	public AbstractTask(){
		this.uuid = UUID.randomUUID();
		this.parentuuid = UUID.randomUUID();
		this.results = new LinkedBlockingQueue<Result<T>>();
	}
	
	public UUID getUUID(){
		return this.uuid;
	}
	
	public UUID getParentUUID(){
		return this.parentuuid;
	}
	

}
