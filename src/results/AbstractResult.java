package results;

import java.util.UUID;

import api.Result;

public class AbstractResult<T> implements Result<T> {
	
	private UUID owner;
	private T res;

	public AbstractResult(UUID uuid, T res){
		this.owner = uuid;
		this.res = res;
	}
	
	
	public T value(){
		return this.res;
	}

	public UUID getOwner(){
		return this.owner;
	}
	
}


