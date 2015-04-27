package api;

import java.io.Serializable;
import java.util.UUID;

/**
 * 
 * interface Result
 * @param <T>
 */
public interface Result<T> extends Serializable {
	public UUID getOwner();
	public T value();

}
