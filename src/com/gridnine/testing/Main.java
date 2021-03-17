package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
    public static void main (String[] args) {
    	List<Flight> flights = FlightBuilder.createFlights();
    	System.out.println(flights);
		Duration twoHours = Duration.ofHours(2);
		LocalDateTime localDataTime = LocalDateTime.now();
		List<FilteringRule> rules = new ArrayList<>();
		try {
			System.out.println("Filter: \"A flight that departs before it arrives\"");
			rules.add(new FilteringRule(GlobalFilterType.SELF, SpecialFilterType.LESS));
			System.out.println(FlightsFiltering.getPredicateFilteredFlights(flights, rules));

			rules.clear();
			System.out.println("Filter: \"A flight departing in the past\"");
			rules.add(new FilteringRule(GlobalFilterType.DATATIME, SpecialFilterType.MORE, localDataTime));
			System.out.println(FlightsFiltering.getPredicateFilteredFlights(flights, rules));

			rules.clear();
			System.out.println("Filter: A flight with more than two hours ground time");
	        rules.add(new FilteringRule(GlobalFilterType.DURATIONGROUND, SpecialFilterType.MORE, twoHours));
			System.out.println(FlightsFiltering.getPredicateFilteredFlights(flights, rules));
		} catch (NonCorrectRuleException e) {
			e.printStackTrace();
		}

    }
}
