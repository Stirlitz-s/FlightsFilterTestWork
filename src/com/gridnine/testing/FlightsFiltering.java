package com.gridnine.testing;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors; 

public class FlightsFiltering {
    public static List<Flight> getPredicateFilteredFlights(final List<Flight> flights, final List<FilteringRule> rules) throws NonCorrectRuleException {
        List<Flight> res = flights;
        List<Predicate<Flight>> predicates = new ArrayList<>();
        for (final FilteringRule rule : rules) {
    		res = (List<Flight>) res.stream().filter(PredicateFactory.getFilter(rule)).collect(Collectors.toList());
        }
        return res;
    }
}