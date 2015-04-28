package tasks.tsp;

import java.util.ArrayList;
import java.util.List;

public class TSPHelpers {
	
	public static double distanceBetweenCities(double[][] cities, Integer city1, Integer city2){
		return Math.abs(cities[city2][1] - cities[city1][1]) + Math.abs(cities[city2][0] - cities[city1][0]);
	}
	
	
	public static double calculateRoundTrip(double[][] cities, List<Integer> seq){
		
		double score = 0;
		for (int i = 0; i < seq.size() - 1; i++){
			Integer city1 = seq.get(i);
			Integer city2 = seq.get(i + 1);
			score += distanceBetweenCities(cities, city1, city2);
		}
		
		Integer city2 = seq.get(0);
		Integer city1 = seq.get(seq.size() - 1);
		score += distanceBetweenCities(cities, city1, city2);
		return score;
	}
	
	
	
	
	
}





