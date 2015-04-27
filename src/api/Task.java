package api;

/**
 * 
 * The interface Task which takes inn, 
 * @param <T>
 */
public interface Task<T> {
	
	public Result<T> execute();
	
	
}
