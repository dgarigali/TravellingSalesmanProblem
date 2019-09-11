package event;

import sim.ISimulator;
import species.IColony;
import structure.IGraph;

/**
 * The EvaporationEvent class is a subclass of Event. 
 * This class controls the edge's pheromone level by reducing it and scheduling the next evaporation event, if that's the case.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public class EvaporationEvent extends Event {
    
	/**The two nodes' ID to which the edge is associated*/
    private final int node_id_src, node_id_dst;

    /**EvaporationEvent Constructor
     * @param timestamp - indicates the time of the event
     * @param sim - pointer to ISimulator
     * @param graph - pointer to IGraph
     * @param colony - pointer to IColony
     * @param node_id_src - source node's ID of the edge
     * @param node_id_dst - destiny node's ID of the edge
     */
    public EvaporationEvent(double timestamp, ISimulator sim, IGraph graph, IColony colony, int node_id_src, int node_id_dst) {
        super(timestamp, sim, graph, colony);
        this.node_id_src = node_id_src;
        this.node_id_dst = node_id_dst;
    }
    
    /**Run this specific event - reduces edge's pheromone level and schedules next evaporation event, if the level is greater than 0*/
    @Override
    public void runEvent() {
        
        //Increment number of evaporation events
        sim.incrementNumEvap();
                
        //decrease edge pheromone level
        double pl = graph.updateEdgePheromoneLevel(node_id_src, node_id_dst, false, sim.getRho());
        
        //schedule next evaporation event if pheromone level is greather than 0
        if (pl > 0)
            sim.scheduleEvaporationEvent(node_id_src, node_id_dst);    
    }  
}
