package tasks.fibonnaci;

import java.util.UUID;

import results.AbstractResult;

public class FibonacciResult extends AbstractResult<Integer> {
	
	Integer res;
	public FibonacciResult(UUID owner, Integer res){
		super(owner);
		this.res = res;
	}
	
}
