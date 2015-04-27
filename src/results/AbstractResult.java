package results;

import java.util.UUID;

import api.Result;

public class AbstractResult<T> implements Result<T> {
	
	private UUID owner;
	
	public AbstractResult(UUID uuid){
		this.owner = uuid;
	}
	

	public UUID getOwner(){
		return this.owner;
	}
	
}


