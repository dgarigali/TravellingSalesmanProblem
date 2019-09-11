package structure;

import java.util.HashMap;
import java.util.Map;

/**
 * The Node class manages all operations regarding the nodes of the graph.
 * Each node contains an unique identifier and the group of its adjacent nodes (edges).
 * This class is only accessible in this package.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

class Node {

    /**ID of the node*/
	private final int id;
    /**Group of edges containing the adjacent nodes*/
    private final Map<Integer,Edge> edges;
    
    /**Node Constructor
     * @param id - ID of the node
     * @param numEdges - number of edges
     * @param loadFactor - indicates from which value (relative to the total size) the hashmap should be resized
     */
    public Node(int id, int numEdges, float loadFactor) {
        this.id = id;
        edges = new HashMap<>(numEdges, loadFactor);
    }
    
    /**Adds an edge
     * @param weight - weight of the edge that will be added 
     * @param node - destination node of the edge
     */
    void addEdge(int weight, Node node) {
        edges.put(node.getID(), new Edge(weight, node));
    }
    
    /**Checks if edge exists
     * @param id_dst - ID of the destination node
     * @return boolean which indicates if there is an edge or not
     */
    boolean edgeExists(int id_dst) {
        return edges.containsKey(id_dst);
    }
    
    /**Getter of node ID
     * @return ID of the node
     */
    int getID() {
        return id;
    }
    
    /**Getter of destination nodes from given source node
     * @return array of node IDs
     */
    int[] getDestinationNodes() {
        int[] nodes = new int[edges.size()];
        int i = 0;
        for (Edge edge: edges.values())
            nodes[i++] = edge.getEndNode().getID();
        return nodes;
    }
    
    /**Getter of given edge weight
     * @param id_dst - ID of the destination node
     * @return edge weight
     */
    int getEdgeWeight(int id_dst) {
        return edges.get(id_dst).getWeight();
    }
    
    /**Getter of given edge pheromone level
     * @param id_dst - ID of the destination node
     * @return edge pheromone level
     */
    double getEdgePheromoneLevel(int id_dst) {
        return edges.get(id_dst).getPheromonesLevel();
    }
    
    /**Updates given edge pheromone level
     * @param id_dst - ID of the destination node
     * @param up_down - boolean which indicates if the pheromone level is to be increased or reduced
     * @param pheromones_level_rho - value to be incremented/decremented to level of pheromones
     * @return the edge pheromone level
     */
    double updateEdgePheromoneLevel(int id_dst, boolean up_down, double pheromones_level_rho) {
        return edges.get(id_dst).updateEdgePheromoneLevel(up_down, pheromones_level_rho);
    }
    
    /**Rewrites the node's hashCode method
     * @return hash code
     */    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.id;
        return hash;
    }

    /**Rewrites the node's equals method
     * @param obj - Object to be compared
     * @return boolean which indicates if the objects are equal or not
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        return this.id == other.id;
    }   
}