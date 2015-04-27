package tasks;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import tasks.AbstractTask;
import api.Result;
import api.Task;

public abstract class AbstractTask<T> implements Task<T>{
	
	
	protected Optional<Result<T>> result;
	
	protected Optional<UUID> parentuuid;
	
	protected UUID uuid;
	
	
	protected ArrayList<Result<T>> results;

		
	public AbstractTask(UUID uuid){
		this.uuid = uuid;
	}
	
	public AbstractTask(Optional<UUID> parentuuid){
		this();
		this.parentuuid = parentuuid;
	}
	
	
	
	public AbstractTask(){
		this.uuid = UUID.randomUUID();
		this.results = new ArrayList<Result<T>>();
	}
	
	public UUID getUUID(){
		return this.uuid;
	}
	
	public UUID getParentUUID(){
		return this.parentuuid.get();
	}
	
	
	public Result<T> result(){
		return result.get();
	}
}
