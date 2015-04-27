package api;

import java.util.concurrent.CompletableFuture;

/**
 * 
 * The interface Task which takes inn, 
 * @param <T>
 */
public interface Task<T> {
	public CompletableFuture<Result<T>> execute();
	
}
