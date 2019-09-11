package structure;

/**
 * The Graph interface (IGraph) defines the operations that are used to manage the graph.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public interface IGraph {
	
	/**Adds node if it does not already exists
     * @param id - ID of the source node
     */
    public void addNode(int id);
    
    /**Adds an edge. Updates total number of edges
     * @param id_src - ID of the source node 
     * @param id_dst - ID of the destination node
     * @param weight - weight of the edge
     */
    public void addEdge(int id_src, int id_dst, int weight);
    
    /**Checks if node exists
     * @param id_src - ID of the source node 
     * @return boolean which indicates if there is a node or not
     */
    public boolean nodeExists(int id_src);
    
    /**Checks if edge exists
     * @param id_src - ID of the source node 
     * @param id_dst - ID of the destination node
     * @return boolean which indicates if there is an edge or not
     */
    public boolean edgeExists(int id_src, int id_dst);
    
    /**Getter of the total number of nodes
     * @return number of nodes
     */
    public int getNumNodes();
    
    /**Getter of total graph weight
     * @return graph weight
     */
    public int getGraphWeight();
    
    /**Getter of total number of edges of the graph
     * @return number of edges
     */
    public int getNumEdges();
    
    /**Getter of adjacent node IDs from given source node
     * @param id_src - ID of the source node 
     * @return array of adjacent nodes
     */
    public int[] getAdjacentNodes(int id_src);
    
    /**Getter of given edge weight
     * @param id_src - ID of the source node 
     * @param id_dst - ID of the destination node 
     * @return the edge weight
     */
    public int getEdgeWeight(int id_src, int id_dst);
    
    /**Getter of given edge pheromone level
     * @param id_src - ID of the source node 
     * @param id_dst - ID of the destination node
     * @return the edge pheromone level
     */
    public double getEdgePheromoneLevel(int id_src, int id_dst);
    
    /**Updates given edge pheromone level
     * @param id_src - ID of the source node 
     * @param id_dst - ID of the destination node 
     * @param up_down - indicates if the update increments or decrements level of pheromones
     * @param pheromones_level_rho - value to be incremented/decremented to level of pheromones
     * @return the pheromone level
     */
    public double updateEdgePheromoneLevel(int id_src, int id_dst, boolean up_down, double pheromones_level_rho);
}
