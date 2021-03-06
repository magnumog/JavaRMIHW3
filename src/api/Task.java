package api;

import java.io.Serializable;
import java.util.List;
import java.util.List;
import java.util.UUID;

import util.Either;
/**
 * 
 * The interface Task which takes inn, 
 * @param <T>
 */
public interface Task<T> extends Serializable {
	
	public <A extends List<Task<T>>, B extends Result<T>> Either<A, B> execute();

	public  boolean registerResult(Result<?> result);
	
	public UUID getUUID();
	public UUID getParentUUID();
}


