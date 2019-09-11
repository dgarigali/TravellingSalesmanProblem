package sim;

import java.util.Random;

/**
 * The Formula class contains all the mathematical expressions that were used. 
 * Contains only static methods.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

final class Formula {
 
	/**Defines the duration of given ant move event
     * @param weight - weight of the edge that the ant is going to transverse
     * @param delta - given parameter from XML file
     * @param random - creates random number between 0 and 1
     * @return the result of the mathematical expression
     */
    static double getMoveEventTimestamp(int weight, double delta, Random random) {
        return -weight*delta*Math.log(1.0 - random.nextDouble());
    }
    
    /**Defines the duration of given edge evaporation event
     * @param eta - given parameter from XML file
     * @param random - creates random number between 0 and 1
     * @return the result of the mathematical expression
     */
    static double getEvaporationEventTimestamp(double eta, Random random) {
        return -eta*Math.log(1.0 - random.nextDouble());
    }
    
    /**Defines the probability of given non-visited edge to be transverse by ant  
     * @param weight - weight of the edge that the ant is going to transverse
     * @param alpha - given parameter from XML file
     * @param beta - given parameter from XML file
     * @param pheromoneLevel - current pheromone level of the given edge
     * @return the result of the mathematical expression
     */
    static double getMoveProbability(int weight, double alpha, double beta, double pheromoneLevel) {
        return ((alpha + pheromoneLevel) / (beta + weight));
    }
  
    /**Defines the value of the increment of all Hamiltonian cycle's edges
     * @param plevel - given parameter from XML file
     * @param cycleWeight - sum of all cycle edge's weight
     * @param graphWeight - sum of all cycle graph's weight
     * @return the result of the mathematical expression
     */
    static double getPheromoneLevelIncrement(double plevel, int cycleWeight, int graphWeight) {
        return (plevel*graphWeight)/cycleWeight;
    }
}