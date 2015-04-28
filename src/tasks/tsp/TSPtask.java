package tasks.tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import api.Result;
import api.Task;
import tasks.AbstractTask;
import tasks.fibonnaci.FibonacciResult;
import tasks.fibonnaci.FibonnaciTask;
import util.Either;
import util.Permutations;

public class TSPtask extends AbstractTask<List<Integer>>{
	
	private double[][] cities;
	private List<Integer> start;
	private List<Integer> perm;
	private static int N = 2;
	
	public TSPtask(double[][] cities, List<Integer> start){
		super();
		this.cities = cities;
		this.start = start;
		this.perm = IntStream.range(0, cities.length).boxed().collect(Collectors.toList());
		this.perm.removeAll(this.start);
		
	}
	
	public TSPtask(UUID parentUUID, double[][] cities, List<Integer> start){
		super(parentUUID);
		this.cities = cities;
		this.start = start;
		this.perm = IntStream.range(0, cities.length).boxed().collect(Collectors.toList());
		this.perm.removeAll(this.start);
	}


	@Override
	public boolean registerResult(Result<?> result) {
		results.add((Result<List<Integer>>) result);
		if (results.size() == this.cities.length - N){
			return true;
		}
		return false;
	}




	@Override
	public Either<List<TSPtask>, TSPResult> execute()  {
		
		
		if (results.size() == this.cities.length - N){
			TSPResult shortest = null;
			double score = Integer.MAX_VALUE;
			for (Result<List<Integer>> result : results){
				TSPResult res = (TSPResult) result;
				if (res.getScore() < score){
					score = res.getScore();
					shortest = res;
				}
			}
			
			return Either.right(new TSPResult(this.parentuuid, shortest.value(), shortest.getScore()));
			
		}
		if (this.start.size() == N){
			
			Permutations<Integer> perm = new Permutations<Integer>(this.perm);
			double best = Integer.MAX_VALUE;
			List<Integer> bestPerm = null;
			while (perm.hasNext()){
				
				ArrayList<Integer> tour = new ArrayList<Integer>(this.start);
				List<Integer> p = perm.next();
				tour.addAll(p);
				double curr = TSPHelpers.calculateRoundTrip(cities, tour);
				if (curr < best){
					List<Integer> a = new ArrayList<Integer>(start);
					best = curr;
					bestPerm = tour;
				}
			}			
			TSPResult res = new TSPResult(this.parentuuid, bestPerm, best);
			return Either.right(res);
		}
		
		
		else {
			ArrayList<TSPtask> tasks = new ArrayList<TSPtask>();
			for (Integer i : this.perm){
				ArrayList<Integer> ints = new ArrayList<Integer>(this.start);
				ints.add(i);
				TSPtask task = new TSPtask(this.uuid ,cities, ints);
				tasks.add(task);
			}
			
			return Either.left(tasks);
			
		}
			
	}
	
}
