package event;

import sim.ISimulator;
import species.IColony;
import structure.IGraph;

/**
 * The Event class implements the IEvent interface. This abstract class is a superclass that manages operations regarding events.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public abstract class Event implements IEvent {
    
	/**Scheduled time of the event*/
    protected double timestamp;
    /**Pointer to ISimulator*/
    protected ISimulator sim;
    /**Pointer to IGraph*/
    protected IGraph graph;
    /**Pointer to IColony*/
    protected IColony colony;

    /**Event Constructor
     * @param timestamp - defines the time of the event
     * @param sim - pointer to ISimulator
     * @param graph - pointer to IGraph
     * @param colony - pointer to IColony
     */
    public Event(double timestamp, ISimulator sim, IGraph graph, IColony colony) {
        this.timestamp = timestamp;
        this.sim = sim;
        this.graph = graph;
        this.colony = colony;
    }
    
    /**Returns timestamp associated to the events
     * @return timestamp
     */
    @Override
    public double getTimestamp() {
        return timestamp;
    }
    
    /**Run the event*/
    @Override
    public abstract void runEvent();   
}