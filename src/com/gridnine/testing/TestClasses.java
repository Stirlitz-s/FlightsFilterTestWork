package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Factory class to get sample list of flights.
 */
class FlightBuilder {
	
	
    static List<Flight> createFlights() {
        LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        return Arrays.asList(
            //A normal flight with two hour duration
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)),
            //A normal multi segment flight
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)),
            //A flight departing in the past
            createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow),
            //A flight that departs before it arrives
            createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)),
            //A flight with more than two hours ground time
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)),
            //Another flight with more than two hours ground time
            createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
    }
    
   
    // ======================================
    // =           added for test           =
    // ======================================
    
    
	private static List<Flight> twoHoursGroundFlights;
	private static List<Flight> departBeforeArrivial;
	private static List<Flight> departInThePast;
    static void createDepartInThePastFlights(int n, LocalDateTime threeDaysFromNow) {
        ArrayList<Flight> departInThePast = new ArrayList<>();
        for (int i = 0; i < n; i++) {
        	departInThePast.add(createFlight(threeDaysFromNow.minusDays(6), threeDaysFromNow));
        }
        FlightBuilder.departInThePast = departInThePast;
    }
    static void createDepartBeforeArrivialFlights(int n, LocalDateTime threeDaysFromNow) {
        ArrayList<Flight> departBeforeArrivial = new ArrayList<>();
        for (int i = 0; i < n; i++) {
        	departBeforeArrivial.add(createFlight(threeDaysFromNow, threeDaysFromNow.minusHours(6)));
        }
        FlightBuilder.departBeforeArrivial = departBeforeArrivial;
    }
    static void createTwoHoursGroundFlights(int n, LocalDateTime threeDaysFromNow) {
        ArrayList<Flight> twoHoursGroundFlights = new ArrayList<>();
        for (int i = 0; i < n; i++) {
        	twoHoursGroundFlights.add(createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                    threeDaysFromNow.plusHours(5), threeDaysFromNow.plusHours(6)));
        	twoHoursGroundFlights.add(createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                    threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(4),
                    threeDaysFromNow.plusHours(6), threeDaysFromNow.plusHours(7)));
        }
        FlightBuilder.twoHoursGroundFlights = twoHoursGroundFlights;
    }
    
    public static List<Flight> createTestFlights(int n) {
    	LocalDateTime threeDaysFromNow = LocalDateTime.now().plusDays(3);
        ArrayList<Flight> res = new ArrayList<>();
        createTwoHoursGroundFlights(n, threeDaysFromNow);
        createDepartBeforeArrivialFlights(n, threeDaysFromNow);
        createDepartInThePastFlights(n, threeDaysFromNow);
        for (int i = 0; i < n; i++) {
        	res.add(createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2)));
        	res.add(createFlight(threeDaysFromNow, threeDaysFromNow.plusHours(2),
                    threeDaysFromNow.plusHours(3), threeDaysFromNow.plusHours(5)));
        }
        res.addAll(twoHoursGroundFlights);
        res.addAll(departBeforeArrivial);
        res.addAll(departInThePast);
        
    	return res;
    }

	public static List<Flight> getTwoHoursGroundFlights() {
		return twoHoursGroundFlights;
	}

	public static List<Flight> getDepartBeforeArrivial() {
		return departBeforeArrivial;
	}

	public static List<Flight> getDepartInThePast() {
		return departInThePast;
	}
    
    // ======================================
    // =          /added for test           =
    // ======================================
    
    
    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
}

/**
 * Bean that represents a flight.
 */
class Flight {
    private final List<Segment> segments;

    Flight(final List<Segment> segs) {
        segments = segs;
    }

    List<Segment> getSegments() {
        return segments;
    }

    @Override
    public String toString() {
        return segments.stream().map(Object::toString)
            .collect(Collectors.joining(" "));
    }
}

/**
 * Bean that represents a flight segment.
 */
class Segment {
    private final LocalDateTime departureDate;

    private final LocalDateTime arrivalDate;

    Segment(final LocalDateTime dep, final LocalDateTime arr) {
        departureDate = Objects.requireNonNull(dep);
        arrivalDate = Objects.requireNonNull(arr);
    }

    LocalDateTime getDepartureDate() {
        return departureDate;
    }

    LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt =
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        return '[' + departureDate.format(fmt) + '|' + arrivalDate.format(fmt)
            + ']';
    }
}