package com.gridnine.testing;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate; 
public class PredicateFactory {
//	private static FilteringRule rule;
	public static Predicate getFilter(FilteringRule rule) throws NonCorrectRuleException {
//		PredicateFactory.rule = rule;
		switch (rule.getGlobalFilterType()) {
	    	case DURATIONGROUND:
	    		return getFilterDurationGround(rule);
		    case DURATIONSKY:;
		    case DATATIME:
	    		return getFilterDataTime(rule);
		    case SELF:
    		    return getFilterSelf(rule);
		}
		return null;
	}
	
	public static Predicate getFilterDurationGround(FilteringRule rule) throws NonCorrectRuleException {
        switch (rule.getSpecialFilterType()) {
            case MORE:
            	if (rule.getDuration1() != null) {
            		return new Predicate<Flight>() {
            	        @Override
            	        public boolean test(Flight f) { 
            	    		List<Segment> segments = f.getSegments();
            	    		if (segments == null || segments.size() < 2)
            	    			return false;
            	    		LocalDateTime start = segments.get(0).getArrivalDate();
            	    		LocalDateTime end = segments.get(0).getArrivalDate();
            	    		Duration resD = Duration.between(start, end);
            	    		for (int i = 1; i < segments.size(); i++) {
            	        		end = segments.get(i).getDepartureDate();
            	        		resD = resD.plus(Duration.between(start, end));
            	        		if (resD.compareTo(rule.getDuration1()) > 0) {
            	        			return true;
            	        		}
            	        		start = segments.get(i).getArrivalDate();
            	    		}
            	            return false;
            	        }
            	    }; 	
            	}
            case LESS:;
            case EQUAL:;
            case MOREANDEQUAL:;
            case LESSANDEQUAL:;
            case BETWEEN:;
        }		
        throw new NonCorrectRuleException();
	}
	public static Predicate getFilterSelf(FilteringRule rule) throws NonCorrectRuleException {
        switch (rule.getSpecialFilterType()) {
            case MORE:;
            case LESS:;
                if (rule.getDate1() == null && rule.getDate2() == null) {
            		return new Predicate<Flight>() {
            	        @Override
            	        public boolean test(Flight f) {
            	    		for (Segment seg : f.getSegments()) {
            	    			if (seg.getArrivalDate().isBefore(seg.getDepartureDate())) {
            	        			return true;
            	    			}
            	    		}

            	            return false;
            	        }
            	    }; 	
            	}
            		 
            case EQUAL:;
            case MOREANDEQUAL:;
            case LESSANDEQUAL:;
            case BETWEEN:;
        }		
        throw new NonCorrectRuleException();
	}
	public static Predicate getFilterDataTime(FilteringRule rule) throws NonCorrectRuleException {
        switch (rule.getSpecialFilterType()) {
            case MORE:;
                if (rule.getDate1() != null) {
            		return new Predicate<Flight>() {
            	        @Override
            	        public boolean test(Flight f) {
            	    		if (f.getSegments().get(0).getDepartureDate().isBefore(rule.getDate1())) {
            	    			return true;
            	    		}
            	            return false;
            	        }
            	    }; 	        	    	
                }
            case LESS:;
            case EQUAL:;
            case MOREANDEQUAL:;
            case LESSANDEQUAL:;
            case BETWEEN:;
        }		
        throw new NonCorrectRuleException();
	}
	
/*    public static FilteringRule getRule() {
    	return rule;
    }*/
	
	
	 
}
