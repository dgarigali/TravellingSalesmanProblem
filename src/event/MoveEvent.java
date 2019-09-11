package event;

import sim.ISimulator;
import species.IColony;
import structure.IGraph;

/**
 * The MoveEvent class is a subclass of Event. 
 * This class controls the movement of the ant by finding the Hamiltonian cycle (storing the cycle with the least total edges weight), 
 * updates the edges pheromone level, resets the ant's path and schedules the next move event.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public class MoveEvent extends Event {
    
	/**ID of an ant*/
	private final int ant_id;

	/**MoveEvent Constructor
     * @param timestamp - indicates the time of the event
     * @param sim - pointer to ISimulator
     * @param graph - pointer to IGraph
     * @param colony - pointer to IColony
     * @param ant_id - indicates the ID of the ant
     */
    public MoveEvent(double timestamp, ISimulator sim, IGraph graph, IColony colony, int ant_id) {
        super(timestamp, sim, graph, colony);
        this.ant_id = ant_id;
    }
    
    /**Run this specific event - checks Hamiltonian cycle, updates edges' pheromone level and schedules next move event*/
    @Override
    public void runEvent() {
        
        //Increment number of move events
        sim.incrementNumMoves();
        
        //Check if path is Hamiltonian
        if (colony.checkHamiltonian(ant_id)) {
            
            int[] ant_path = colony.getAntPath(ant_id);
            int cycleWeight = 0;
            String bestHamiltonian = "{";
            
            //Calculate cycle weight
            for (int i = 0; i < ant_path.length - 1; i++) {
                cycleWeight += graph.getEdgeWeight(ant_path[i], ant_path[i+1]);
                bestHamiltonian += ant_path[i] + ",";
                        
                //Schedule pheromone level if no evaporation event for the edge
                if (graph.getEdgePheromoneLevel(ant_path[i], ant_path[i+1]) == 0)
                    sim.scheduleEvaporationEvent(ant_path[i], ant_path[i+1]);
            }

            //Check if it is the best Hamiltonian so far
            if (sim.getBestWeight() == -1 || cycleWeight < sim.getBestWeight()) {
                sim.setBestWeight(cycleWeight);
                sim.setHamiltonianCycle(bestHamiltonian.substring(0, bestHamiltonian.length() - 1) + "}");
            }   

            //Obtain graph weight
            int graphWeight = graph.getGraphWeight();
            
            //Calculate increment
            double increment = sim.getPheromoneLevelIncrement(cycleWeight, graphWeight);
            
            //Update edges pheromone level
            for (int i = 0; i < ant_path.length - 1; i++) {
                graph.updateEdgePheromoneLevel(ant_path[i], ant_path[i+1], true, increment);          
            }
            
            //Remove Hamiltonian cycle
            colony.removeFromPath(ant_id, ant_path[ant_path.length-2]);
            colony.removeFromPath(ant_id, ant_path[0]);             
        }
        
        //Schedule next move event
         sim.scheduleMoveEvent(ant_id);
    }  
}