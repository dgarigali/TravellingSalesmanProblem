package event;

/**
 * The Event interface (IEvent) defines the operations that specify the event.
 * 
 * @author Maria Carolina Roque, n� 81398
 * @author Daniel Pestana, n� 93883
 * @author Carlos Sim�es, n� 93921
 */

public interface IEvent {
	
	/**Returns timestamp associated to the events
     * @return timestamp
     */
    public double getTimestamp();
    
    /**Run the event*/
    public abstract void runEvent();
}
