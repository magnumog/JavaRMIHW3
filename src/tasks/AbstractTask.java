package tasks;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import tasks.AbstractTask;
import api.Result;
import api.Task;

public abstract class AbstractTask<T> implements Task<T>{
	private UUID uuid;
	protected ArrayList<CompletableFuture<Result<T>>> futures;
		
	public AbstractTask(){
		uuid = UUID.randomUUID();
		futures = new ArrayList<CompletableFuture<Result<T>>>();
	}
	
	public UUID getUUID(){
		return this.uuid;
	}
	
}
