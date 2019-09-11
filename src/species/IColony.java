package species;

/**
 * The Colony interface (IColony) defines the operations that are used to manage the colony.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public interface IColony {
       
	/**Adds an ant to the array of ants and updates ant counter*/
    public void addAnt();
    
    /**Getter of given ant path
     * @param ant_id - ID of an ant
     * @return the path of that ant
     */
    public int[] getAntPath(int ant_id);
    
    /**Checks if the ant path is a Hamiltonian cycle
     * @param ant_id - ID of an ant
     * @return boolean that indicates if the path is a Hamiltonian cycle or not
     */
    public boolean checkHamiltonian(int ant_id);
    
    /**Removes elements from the ant path until a given node ID
     * @param ant_id - ID of an ant
     * @param node_id - ID of the node 
     */
    public void removeFromPath(int ant_id, int node_id);
    
    /**Adds a node to the ant path
     * @param ant_id - ID of an ant
     * @param node_id - ID of the node 
     */
    public void addToPath(int ant_id, int node_id);
    
    /**Getter of number of nodes
     * @return number of nodes
     */
    public int getNumNodes();
}
