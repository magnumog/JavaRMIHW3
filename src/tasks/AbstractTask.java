package tasks;
import java.util.UUID;

import api.Task;

public abstract class AbstractTask<T> implements Task<T>{
	private UUID uuid;
	
	public AbstractTask(){
		uuid = UUID.randomUUID();
	}
	
	public UUID getUUID(){
		return this.uuid;
	}
	
	
}
