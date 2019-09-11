package event;

import sim.ISimulator;
import species.IColony;
import structure.IGraph;

/**
 * The ObservationEvent class is a subclass of Event. 
 * This class prints the observations that informs the user about the state of the simulation.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public class ObservationEvent extends Event {
    
	/**This is a class variable that counts the number of observations printed so far. It's initialized to 1*/
	private static int obs_num = 1;
    
    /**ObservationEvent Constructor
     * @param timestamp - indicates the time of the event
     * @param sim - pointer to ISimulator
     * @param graph - pointer to IGraph
     * @param colony - pointer to IColony
     */
    public ObservationEvent(double timestamp, ISimulator sim, IGraph graph, IColony colony) {
        super(timestamp, sim, graph, colony);
    }
    
    /**Run this specific event - prints to the user the state of the simulation*/
    @Override
    public void runEvent() {
        sim.print(obs_num, this.timestamp);
        obs_num++;
    }  
}