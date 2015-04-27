package api;

import java.util.List;

<<<<<<< HEAD
=======
import java.util.List;

>>>>>>> master
import util.Either;
/**
 * 
 * The interface Task which takes inn, 
 * @param <T>
 */
public interface Task<T> {
	
	public <A extends Task<T>,B extends Result<T>> Either<A, B> execute();
	

}

