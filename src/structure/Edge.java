package structure;

/**
 * The Edge class manages all operations regarding the edges of each node of the graph.
 * This class is only accessible in this package.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

class Edge {
    
    /**Weight of the edge*/
	private final int weight;
	/**Destination node of the edge*/
    private final Node end;
    /**Pheromones level of the edge. Initialized to 0*/
    private double pheromones_level = 0;
    
    /**Edge Constructor
     * @param weight - weight of the edge
     * @param end - end node of the edge
     */
    public Edge(int weight, Node end) {
        this.weight = weight;
        this.end = end;
    }
    
    /**Updates pheronomes level. It makes sure that pheromone level is never negative
     * @param up_down - boolean which indicates if the pheromone level is to be increased or reduced
     * @param pheromones_level_rho - value to be incremented/decremented to level of pheromones
     * @return pheronomes level
     */
    double updateEdgePheromoneLevel(boolean up_down, double pheromones_level_rho) {
        if (up_down) 
            this.pheromones_level += pheromones_level_rho;
        else { //Make sure pheromone level is never negative
            if (this.pheromones_level - pheromones_level_rho < 0)
                this.pheromones_level = 0;
            else
                this.pheromones_level -= pheromones_level_rho;
        }
        return this.pheromones_level;
    }
    
    /**Getter of edge's pheronomes level
     * @return pheronomes level
     */
    double getPheromonesLevel() {
        return pheromones_level;
    }
    
    /**Getter of edge's weight
     * @return weight
     */
    int getWeight() {
        return weight;
    }
    
    /**Getter of edge's end node
     * @return end node
     */
    Node getEndNode() {
        return end;
    }   
}