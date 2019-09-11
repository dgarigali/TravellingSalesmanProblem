package structure;

import java.util.HashMap;
import java.util.Map;

/**
 * The Graph class implements the IGraph interface. 
 * This class manages all the graph operations regarding the manipulation of the nodes and respective edges that form the graph.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public class Graph implements IGraph{
    
	/**Group of nodes*/
    private final Map<Integer, Node> nodes;
    /**Total weight of the graph. Initialized to 0*/
    private int GraphWeight = 0;
    /**Total number of the edges of the graph. Initialized to 0*/
    private int numEdges = 0;
    /**Total number of nodes*/
    private final int numNodes;
   
    /**Graph Constructor
     * @param numNodes - number of nodes
     * @param loadFactor - indicates from which value (relative to the total size) the hashmap should be resized
     */
    public Graph(int numNodes, float loadFactor) {
        this.numNodes = numNodes;
        nodes = new HashMap<>(numNodes, loadFactor);
    }

    /**Getter of total graph weight
     * @return graph weight
     */
    @Override
    public int getGraphWeight() {
        return GraphWeight;
    }
    
    /**Getter of total number of edges of the graph
     * @return number of edges
     */
    @Override
    public int getNumEdges() {
        return numEdges;
    }
    
    /**Adds node if it does not already exists
     * @param id - ID of the source node
     */
    @Override
    public void addNode(int id) {
        if(!nodes.containsKey(id))
            nodes.put(id, new Node(id, numNodes-1, 1));
    }
    
    /**Checks if node exists
     * @param id_src - ID of the source node 
     * @return boolean which indicates if there is a node or not
     */
    @Override
    public boolean nodeExists(int id_src) {
        return nodes.containsKey(id_src);
    }
    
    /**Getter of the total number of nodes
     * @return number of nodes
     */
    @Override
    public int getNumNodes() {
        return nodes.size();
    }
    
    /**Checks if edge exists
     * @param id_src - ID of the source node 
     * @param id_dst - ID of the destination node
     * @return boolean which indicates if there is an edge or not
     */
    @Override
    public boolean edgeExists(int id_src, int id_dst) {
        return nodes.get(id_src).edgeExists(id_dst);
    }
    
    /**Adds an edge. Updates total number of edges
     * @param id_src - ID of the source node 
     * @param id_dst - ID of the destination node
     * @param weight - weight of the edge
     */
    @Override
    public void addEdge(int id_src, int id_dst, int weight) {
        Node src_node = nodes.get(id_src); 
        Node dest_node = nodes.get(id_dst); 
        src_node.addEdge(weight, dest_node);
        dest_node.addEdge(weight, src_node);
        
        //Update graph weight and number of edges
        GraphWeight += weight;
        numEdges++;
    }
    
    /**Getter of adjacent node IDs from given source node
     * @param id_src - ID of the source node 
     * @return array of adjacent nodes
     */
    @Override
    public int[] getAdjacentNodes(int id_src) {
        return nodes.get(id_src).getDestinationNodes();
    }
    
    /**Getter of given edge weight
     * @param id_src - ID of the source node 
     * @param id_dst - ID of the destination node 
     * @return the edge weight
     */
    @Override
    public int getEdgeWeight(int id_src, int id_dst) {
        return nodes.get(id_src).getEdgeWeight(id_dst);
    }
    
    /**Getter of given edge pheromone level
     * @param id_src - ID of the source node 
     * @param id_dst - ID of the destination node
     * @return the edge pheromone level
     */
    @Override
    public double getEdgePheromoneLevel(int id_src, int id_dst) {
        return nodes.get(id_src).getEdgePheromoneLevel(id_dst);
    }
    
    /**Updates given edge pheromone level
     * @param id_src - ID of the source node 
     * @param id_dst - ID of the destination node 
     * @param up_down - indicates if the update increments or decrements level of pheromones
     * @param pheromones_level_rho - value to be incremented/decremented to level of pheromones
     * @return the pheromone level
     */
    @Override
    public double updateEdgePheromoneLevel(int id_src, int id_dst, boolean up_down, double pheromones_level_rho) {
        Node src_node = nodes.get(id_src); 
        Node dest_node = nodes.get(id_dst); 
        src_node.updateEdgePheromoneLevel(id_dst, up_down, pheromones_level_rho);
        return dest_node.updateEdgePheromoneLevel(id_src, up_down, pheromones_level_rho);
    }
}