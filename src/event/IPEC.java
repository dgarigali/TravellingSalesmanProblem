package event;

/**
 * The PEC interface (IPEC) defines operations that specify the PEC. 
 * 
 * @author Maria Carolina Roque, n� 81398
 * @author Daniel Pestana, n� 93883
 * @author Carlos Sim�es, n� 93921
 */

public interface IPEC {
	
    /**Add event to the PEC
     * @param ev - event that is added to the PEC
     */
    public void addEvPEC(Event ev);
    
    /**Remove an event from the PEC
     * @return next event to happen
     */
    public Event nextEVPEC();
}
