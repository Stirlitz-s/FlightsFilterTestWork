package com.gridnine.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;



class SortingTest {

	@Test
	void test() {
		final int numForEachFightsToCreate = 10000;
    	List<Flight> flights = FlightBuilder.createTestFlights(numForEachFightsToCreate);
		Duration twoHours = Duration.ofHours(2);
		LocalDateTime localDataTime = LocalDateTime.now();
		List<FilteringRule> rules = new ArrayList<>();
		long start;
		long finish;
		try {
			System.out.println("Number of flights of each type: " + numForEachFightsToCreate);
			System.out.println("Filter: \"A flight that departs before it arrives\"");
			rules.add(new FilteringRule(GlobalFilterType.SELF, SpecialFilterType.LESS));
			start = System.currentTimeMillis();			
			assertEquals(FlightsFiltering.getPredicateFilteredFlights(flights, rules), FlightBuilder.getDepartBeforeArrivial());
			finish = System.currentTimeMillis();
			System.out.println("Execution time, ms: " + (finish-start));
			
            rules.clear();
			System.out.println("Filter: \"A flight departing in the past\"");
			rules.add(new FilteringRule(GlobalFilterType.DATATIME, SpecialFilterType.MORE, localDataTime));			
			start = System.currentTimeMillis();			
			assertEquals(FlightsFiltering.getPredicateFilteredFlights(flights, rules), FlightBuilder.getDepartInThePast());
			finish = System.currentTimeMillis();
			System.out.println("Execution time, ms: " + (finish-start));

			
            rules.clear();
			System.out.println("Filter: A flight with more than two hours ground time");
	        rules.add(new FilteringRule(GlobalFilterType.DURATIONGROUND, SpecialFilterType.MORE, twoHours));
			start = System.currentTimeMillis();			
			assertEquals(FlightsFiltering.getPredicateFilteredFlights(flights, rules), FlightBuilder.getTwoHoursGroundFlights());
			finish = System.currentTimeMillis();
			System.out.println("Execution time, ms: " + (finish-start));
			
		} catch (NonCorrectRuleException e) {
			e.printStackTrace();
		}
	}

}
