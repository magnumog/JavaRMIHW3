package api;

import java.util.List;


import java.util.List;


import util.Either;
/**
 * 
 * The interface Task which takes inn, 
 * @param <T>
 */
public interface Task<T> {
	
	public <A extends Task<T>,B extends Result<T>> Either<A, B> execute();
	

}

