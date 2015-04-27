package tasks.fibonnaci;


<<<<<<< HEAD
=======

>>>>>>> master
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import api.Task;
import tasks.AbstractTask;
import util.Either;

public class FibonnaciTask extends AbstractTask<Integer> {

	
	private int n;
	
	public FibonnaciTask(int n){
		this.n = n;
	}

	@Override
	public Either<List<FibonnaciTask>, FibonacciResult> execute() {
		
		if (n < 2){
			FibonacciResult res = new FibonacciResult(1);
			return Either.right(res);
		}
		else {
			FibonnaciTask f1 = new FibonnaciTask(n-1);
			FibonnaciTask f2 = new FibonnaciTask(n-2);
			ArrayList<FibonnaciTask> l = new ArrayList<FibonnaciTask>(Arrays.asList(f1, f2));
			
			return Either.left(l);
		}
	}

}
