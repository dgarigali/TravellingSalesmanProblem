package species;

import java.util.ArrayList;
import java.util.List;

/**
 * The ant class manages the ant and its path in the graph.
 * This class is only accessible in this package.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

class Ant {
    
	/**Ant path*/
    private final List<Integer> path;
    
    /**Ant Constructor - already adds nest node to ant path
     * @param nbnodes - number of the nodes in the graph
     * @param nestnode - indicates which is the nest node
     */
    public Ant(int nbnodes, int nestnode) {
        path = new ArrayList<>(nbnodes);
        path.add(nestnode);
    }
    
    /**Adds an element to the ant path
     * @param node_id - ID of the node that is added
     */
    void addToPath(int node_id) {
        path.add(node_id);
    }
    
    /**Getter of ant path
     * @return ant path
     */
    int[] getAntPath() {
        int[] local_path = new int[path.size()];
        for (int i = 0; i < path.size(); i++) {
            local_path[i] = path.get(i);
        }
        return local_path;
    }
    
    /**Check if the path is a Hamiltonian cycle
     * @param nbnodes - Number of nodes in the graph
     * @return boolean which indicates if the path is Hamiltonian or not
     */
    boolean checkHamiltonian(int nbnodes) {
        return (path.size() == nbnodes + 1) && (path.get(0).equals(path.get(path.size()-1)));
    }
    
    /**Removes elements from the path until a given node is reached
     * @param node_id - ID of the node
     */
    void removeFromPath(int node_id) {
        for (int i = path.size() - 1; i > 0; i--) {
            if (path.get(i) == node_id)
                return;
            else
                path.remove(i);
        }
    } 
}