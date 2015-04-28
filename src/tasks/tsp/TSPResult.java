package tasks.tsp;

import java.util.List;
import java.util.UUID;

import results.AbstractResult;


public class TSPResult  extends AbstractResult<List<Integer>> {

	private double score = -1;
	public TSPResult(UUID uuid, List<Integer> res, double score) {
		super(uuid, res);
		this.score = score;
	}
	
	
	public double getScore(){
		return this.score;
	}

}
