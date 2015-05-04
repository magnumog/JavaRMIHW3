package tasks.fibonnaci;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import api.Result;
import tasks.AbstractTask;
import util.Either;

public class FibonnaciTask extends AbstractTask<Integer> {

	
	private int n;
	
	public FibonnaciTask(UUID parentUUID, int n){
		super(parentUUID);
		this.n = n;

	}
	public FibonnaciTask(int n){
		super();
		this.n = n;
		
	}
	

	@Override
	public Either<List<FibonnaciTask>, FibonacciResult> execute() {
		if (results.size() == 2){
			FibonacciResult res = new FibonacciResult(this.parentuuid, results.get(0).value() + results.get(1).value());
			return Either.right(res);
		}
		
		if (n <= 2){
			FibonacciResult res = new FibonacciResult(this.parentuuid, 1);
			return Either.right(res);
		}
		
		else {		
			FibonnaciTask f1 = new FibonnaciTask(this.uuid, n-1);
			FibonnaciTask f2 = new FibonnaciTask(this.uuid, n-2);
			ArrayList<FibonnaciTask> l = new ArrayList<FibonnaciTask>(Arrays.asList(f1, f2));
			return Either.left(l);
		}
	}
	@Override
	public boolean registerResult(Result<?> result) {
		this.results.add((Result<Integer>) result);
		if (this.results.size() == 2){
			return true;
		}
		return false;
	}
	
	
	public String toString(){
		return ""+this.n;
	}

}
