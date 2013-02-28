package com.jgaap.distances;
/**
 * WED Divergence
 * Weighted Euclidean Distance Divergence
 * d = sqrt( sum( wi * (xi - yi)^2 ))
 * 
 * if(xi == 0)
 * 	wi = 1
 * else
 * 	wi = xi
 * 
 * @author Adam Sargent
 * @version 1.0
 */

import java.util.Set;

import com.google.common.collect.Sets;
import com.jgaap.generics.DistanceCalculationException;
import com.jgaap.generics.DistanceFunction;
import com.jgaap.util.Event;
import com.jgaap.util.EventMap;

public class WEDDivergence extends DistanceFunction {

	@Override
	public String displayName() {
		return "WED Divergence";
	}

	@Override
	public String tooltipText() {
		return "Weighted Euclidean Distance (WED) Divergence";
	}

	@Override
	public boolean showInGUI() {
		return true;
	}

	@Override
	public double distance(EventMap unknownEventMap, EventMap knownEventMap)
			throws DistanceCalculationException {
		Set<Event> events = Sets.union(unknownEventMap.uniqueEvents(), knownEventMap.uniqueEvents());
		
		double distance = 0.0;
		
		for(Event event : events){
			double unknown = unknownEventMap.relativeFrequency(event);
			double known = knownEventMap.relativeFrequency(event);
			distance += (unknown == 0 ? 1 : unknown) * (unknown - known) * (unknown - known);  
		}
		
		return Math.sqrt(distance);
	}

}
