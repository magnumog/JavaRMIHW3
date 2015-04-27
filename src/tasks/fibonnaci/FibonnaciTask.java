package tasks.fibonnaci;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import api.Result;
import tasks.AbstractTask;
import util.Either;

public class FibonnaciTask extends AbstractTask<Integer> {

	
	private int n;
	
	public FibonnaciTask(Optional<UUID> parentUUID, int n){
		super(parentUUID);
		this.n = n;
	}
	public FibonnaciTask(int n){
		super();
		this.n = n;
	}
	

	@Override
	public Either<List<FibonnaciTask>, FibonacciResult> execute() {
		
		if (n < 2){
			FibonacciResult res = new FibonacciResult(this.uuid, 1);
			return Either.right(res);
		}
		else {		
			FibonnaciTask f1 = new FibonnaciTask(Optional.of(this.uuid), n-1);
			FibonnaciTask f2 = new FibonnaciTask(Optional.of(this.uuid), n-2);
			ArrayList<FibonnaciTask> l = new ArrayList<FibonnaciTask>(Arrays.asList(f1, f2));
			return Either.left(l);
		}
	}
	@Override
	public <A extends Result<Integer>> boolean registerResult(A result) {
		
		return false;
	}
	
	
	
	
	
	

}
