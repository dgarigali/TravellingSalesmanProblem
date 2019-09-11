package event;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * The PEC class implements the IPEC interface. This class contains all future events. 
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public class PEC implements IPEC {
    
	/**Event queue*/
	private final Queue<Event> events;

	/**PEC Constructor, initializes the queue of events
     * @param initialNum - initial size of the priority queue 
     */
    public PEC(int initialNum) {
        events = new PriorityQueue<>(initialNum, new EventComparator());
    }
    
    /**Add an event to the PEC
     * @param ev - event that is added to the PEC
     */
    @Override
    public void addEvPEC(Event ev) {
        events.add(ev);
    }

    /**Remove an event from the PEC
     * @return next event to happen
     */
    @Override
    public Event nextEVPEC() {
        return events.poll();
    } 
}