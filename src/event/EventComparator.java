package event;

import java.util.Comparator;

/**
 * The Event Comparator class implements the Java's Comparator interface by comparing the timestamps of two events.
 * This will be used to know which of the events of the PEC will run first.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public class EventComparator implements Comparator<Event> {
    
	/**Compares two events by their timestamp
     * @param e1 - one of the events to compare
     * @param e2 - the other event to compare
     * @return value that indicates if the timestamp is bigger, smaller or equal
     */
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getTimestamp() < e2.getTimestamp()) return -1;
        if (e1.getTimestamp() > e2.getTimestamp()) return 1;
        return 0;   
    }  
}