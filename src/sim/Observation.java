package sim;

/**
 * The Observation class contains all fields and methods regarding the observation event.
 * This class is only accessible in this package.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

class Observation {
    
	/**Total number of move events. Initialized to 0*/
    private int num_move = 0;
    /**Total number of evaporations. Initialized to 0*/
    private int num_evap = 0;
    /**Contains the best Hamiltonian cycle found so far. Initially empty*/
    private String hamiltonian_cycle = "";
    /**Total weight of the best Hamiltonian cycle. Initialized to -1 as a way of checking first Hamiltonian cycle found */
    private int best_weight = -1;
    
    /**Increment the total number of move events*/
    void incrementNumMoves() {
        num_move++;
    }
    
    /**Increment the total number of evaporation events*/
    void incrementNumEvap() {
        num_evap++;
    }
    
    /**Print observations about simulation state to the user
     * @param obs_num - Number of the observation
     * @param instant - Current time of the simulation
     */
    void print(int obs_num, double instant) {
        System.out.println("Observation number: " + obs_num);
        System.out.println("\tPresent instant: " + instant);
        System.out.println("\tNumber of move events: " + num_move);
        System.out.println("\tNumber of evaporation events: " + num_evap);
        System.out.println("\tHamiltonian cycle: " + hamiltonian_cycle + "\n");
    }
    
    /**Getter of best Hamiltonian cycle weight
     * @return best weight
     */
    int getBestWeight() {
        return best_weight;
    }
    
    /**Setter of best Hamiltonian cycle weight
     * @param best_weight - set the best weight
     */
    void setBestWeight(int best_weight) {
        this.best_weight = best_weight;
    }
    
    /**Setter of best Hamiltonian cycle
     * @param hamiltonian_cycle - sets the best Hamiltonian cycle
     */
    void setHamiltonianCycle(String hamiltonian_cycle) {
        this.hamiltonian_cycle = hamiltonian_cycle;
    }    
}