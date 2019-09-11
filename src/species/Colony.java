package species;

/**
 * The Colony class implements the IColony interface. This class manages the ants and their paths in the graph.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public class Colony implements IColony {
    
    /**Array of ants*/
	private final Ant[] ants;
	/**Number of ants. Initialized to 0*/
    private int ants_counter = 0;
    /**Number of nodes in the graph and the nest node*/
    private final int nbnodes, nestnode;
    
    /**Colony Constructor
     * @param antcolsize - size of the colony
     * @param nbnodes - number of the nodes in the graph
     * @param nestnode - indicates which is the nest node
     */
    public Colony(int antcolsize, int nbnodes, int nestnode) {
        ants = new Ant[antcolsize];
        this.nbnodes = nbnodes; 
        this.nestnode = nestnode;
    }
    
    /**Adds an ant to the array of ants and updates ant counter*/
    @Override
    public void addAnt() {
        ants[ants_counter++] = new Ant(nbnodes, nestnode);
    }
    
    /**Getter of given ant path
     * @param ant_id - ID of an ant
     * @return the path of that ant
     */
    @Override
    public int[] getAntPath(int ant_id) {
        return ants[ant_id].getAntPath();
    }
    
    /**Checks if the ant path is a Hamiltonian cycle
     * @param ant_id - ID of an ant
     * @return boolean that indicates if the path is a Hamiltonian cycle or not
     */
    @Override
    public boolean checkHamiltonian(int ant_id) {
        return ants[ant_id].checkHamiltonian(nbnodes);
    }
    
    /**Removes elements from the ant path until a given node ID
     * @param ant_id - ID of an ant
     * @param node_id - ID of the node 
     */
    @Override
    public void removeFromPath(int ant_id, int node_id) {
        ants[ant_id].removeFromPath(node_id);
    }
    
    /**Adds a node to the ant path
     * @param ant_id - ID of an ant
     * @param node_id - ID of the node 
     */
    @Override
    public void addToPath(int ant_id, int node_id) {
        ants[ant_id].addToPath(node_id);
    }
    
    /**Getter of number of nodes
     * @return number of nodes
     */
    @Override
    public int getNumNodes() {
        return nbnodes;
    }
}
