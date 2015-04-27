package api;

import java.util.UUID;

/**
 * 
 * interface Result
 * @param <T>
 */
public interface Result<T> {
	public UUID getOwner();
	public T value();

}
