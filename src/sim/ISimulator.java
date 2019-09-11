package sim;

/**
 * The Simulator interface (ISimulator) defines all the operations needed in the simulator.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public interface ISimulator { 
	
	/**Set simulation parameters
     * @param finalinst - final instant of the simulation
     * @param antcolsize - ant colony size
     * @param plevel - pheromone level
     */
    public void setSimulationParameters(double finalinst, int antcolsize, double plevel);
   
    /**Set move event parameters
     * @param alpha - Input parameter concerning the ant move event
     * @param beta - Input parameter concerning the ant move event
     * @param delta - Input parameter concerning the ant move event
     */   
    public void setMoveParams(double alpha, double beta, double delta);
    
    /**Set evaporation event parameters
     * @param eta - Input parameter concerning the pheromone evaporation event
     * @param rho - Input parameter concerning the pheromone evaporation event
     */
    public void setEvapParms(double eta, double rho);
    
    /**Initializes colony and graph objects
     * @param nbnodes - number of nodes
     * @param nestnode - nest node ID
     */
    public void setGraphParameters(int nbnodes, int nestnode);
    
    /**Getter of graph's number of nodes
     * @return number of nodes
     */
    public int getNumNodes();
    
    /**Add node if doesn't exist
     * @param id - ID of the node
     */
    public void addNode(int id);
    
    /**Add an edge to the graph
     * @param id_src - ID of the source node
     * @param id_dst - ID of the destiny node
     * @param weight - weight of the edge
     */
    public void addEdge(int id_src, int id_dst, int weight);
    
    /**Checks if given node exists
     * @param id_src - ID of the source node
     * @return boolean which indicates if the node exists or not
     */
    public boolean nodeExists(int id_src);
    
    /**Checks if the given edge exists
     * @param id_src - ID of the source node
     * @param id_dst - ID of the destiny node
     * @return boolean which indicates if the edge exists or not
     */
    public boolean edgeExists(int id_src, int id_dst);
    
    /**Getter of rho parameter (for evaporation event)
     * @return rho
     */
    public double getRho();
    
    /**Run simulation. 
     * Schedules observation and ant move events.
     * Then, until reaching simulation final time, it picks up and runs next event from PEC
     */
    public void run();
    
    /**Prints simulation state
     * @param obs_num - number of the observation
     * @param instant - instant of the observation
     */
    public void print(int obs_num, double instant);
    
    /**Getter of pheromone level increment value
     * @param cycleWeight - weight of the cycle
     * @param graphWeight - total weight of the graph
     * @return pheromone level increment value
     */
    public double getPheromoneLevelIncrement(int cycleWeight, int graphWeight);
    
    /**Schedules ant move event in PEC
     * @param ant_id - ID of the ant
     */
    public void scheduleMoveEvent(int ant_id);
    
    /**Schedules evaporation event to PEC
     * @param node_id_src - ID of the source node
     * @param node_id_dst - ID of the destiny node
     */
    public void scheduleEvaporationEvent(int node_id_src, int node_id_dst);
    
    /**Increments number of move events*/
    public void incrementNumMoves();
    
    /**Increments number of evaporation events*/
    public void incrementNumEvap();
    
    /**Getter of best Hamiltonian cycle weight 
     * @return best weight
     */
    public int getBestWeight();
    
    /**Setter of best Hamiltonian cycle weight 
     * @param best_weight - defines the best weight
     */
    public void setBestWeight(int best_weight);
    
    /**Setter of best Hamiltonian cycle 
     * @param hamiltonian_cycle - defines the Hamiltonian cycle
     */
    public void setHamiltonianCycle(String hamiltonian_cycle); 
}
