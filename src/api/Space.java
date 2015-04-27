package api;

import java.util.List;

/**
 * 
 * Space interface
 */
public interface Space {
	
	public void putTask(Task<?> task);
	public void putTasks(List<Task<?>> tasks);
	
	public void putResult(Result<?> result);
	public void putResults(List<Result<?>> results);
	
	public List<Result<?>> takeResults();

	
}
