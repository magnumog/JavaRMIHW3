package tasks.fibonnaci;

import api.Result;

public class FibonacciResult implements Result<Integer> {
	
	Integer res;
	public FibonacciResult(Integer res){
		this.res = res;
	}
	
}
