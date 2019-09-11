package sim;

import event.EvaporationEvent;
import event.IEvent;
import event.IPEC;
import event.MoveEvent;
import event.ObservationEvent;
import event.PEC;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import species.Colony;
import species.IColony;
import structure.Graph;
import structure.IGraph;

/**
 * The Simulator class implements the ISimulator interface. 
 * This class is the core of the entire program as it schedules all events, as well running them. It also controls the duration of the simulation.
 * 
 * @author Maria Carolina Roque, nº 81398
 * @author Daniel Pestana, nº 93883
 * @author Carlos Simões, nº 93921
 */

public class Simulator implements ISimulator {
    
	/**Input parameters concerning the ant move event*/
    private double alpha, beta, delta;
    /**Input parameters concerning the pheromone evaporation event*/
    private double eta, rho;
    /**Input parameter concerning pheromone level*/
    private double plevel;
    /**Parameter for generating random number between 0 and 1*/
    private Random simRandom;
    /**Total number of observations (default value is 20)*/
    private final int numberObservations = 20;
    /**Final instant of the simulation*/
    private double finalinst;
    /**Current simulation timestamp. Initialized to 0*/
    private double current_timestamp = 0;
    /**Ant colony size*/
    private int antcolsize; 
    /**Observation pointer*/
    private final Observation observation;
    /**PEC pointer*/
    private IPEC pec;
    /**Colony pointer*/
    private IColony colony;
    /**Graph pointer*/
    private IGraph graph;

    /**Simulator Constructor*/
    public Simulator() {
        observation = new Observation();
    }
    
    /**Set simulation parameters
     * @param finalinst - final instant of the simulation
     * @param antcolsize - ant colony size
     * @param plevel - pheromone level
     */
    @Override
    public void setSimulationParameters(double finalinst, int antcolsize, double plevel) {
        this.finalinst = finalinst;
        this.antcolsize = antcolsize;
        this.plevel = plevel;
    }
    
    /**Set move event parameters
     * @param alpha - Input parameter concerning the ant move event
     * @param beta - Input parameter concerning the ant move event
     * @param delta - Input parameter concerning the ant move event
     */
    @Override
    public void setMoveParams(double alpha, double beta, double delta) {
        this.alpha = alpha;
        this.beta = beta;
        this.delta = delta;
        simRandom = new Random();
        pec = new PEC(antcolsize + graph.getNumEdges() + numberObservations);
    }
    
    /**Set evaporation event parameters
     * @param eta - Input parameter concerning the pheromone evaporation event
     * @param rho - Input parameter concerning the pheromone evaporation event
     */
    @Override
    public void setEvapParms(double eta, double rho) {
        this.eta = eta;
        this.rho = rho;
    }
    
    /**Initializes colony and graph objects
     * @param nbnodes - number of nodes
     * @param nestnode - nest node ID
     */
    @Override
    public void setGraphParameters(int nbnodes, int nestnode) {
        colony = new Colony(antcolsize, nbnodes, nestnode);
        graph = new Graph(nbnodes, 1);
    }
    
    /**Getter of rho parameter (for evaporation event)
     * @return rho
     */
    @Override 
    public double getRho() {
        return rho;
    }
       
    /**Add node if doesn't exist
     * @param id - ID of the node
     */
    @Override
    public void addNode(int id) {
        graph.addNode(id);
    }
    
    /**Add an edge to the graph
     * @param id_src - ID of the source node
     * @param id_dst - ID of the destiny node
     * @param weight - weight of the edge
     */
    @Override
    public void addEdge(int id_src, int id_dst, int weight) {
        graph.addEdge(id_src, id_dst, weight);
    }
    
    /**Getter of graph's number of nodes
     * @return number of nodes
     */
    @Override
    public int getNumNodes() {
        return graph.getNumNodes();
    }
    
    /**Checks if given node exists
     * @param id_src - ID of the source node
     * @return boolean which indicates if the node exists or not
     */
    @Override
    public boolean nodeExists(int id_src) {
        return graph.nodeExists(id_src);
    }
    
    /**Checks if the given edge exists
     * @param id_src - ID of the source node
     * @param id_dst - ID of the destiny node
     * @return boolean which indicates if the edge exists or not
     */
    @Override
    public boolean edgeExists(int id_src, int id_dst) {
        return graph.edgeExists(id_src, id_dst);
    }
    
    /**Run simulation. 
     * Schedules observation and ant move events.
     * Then, until reaching simulation final time, it picks up and runs next event from PEC
     */
    @Override
    public void run() {
        
        //Set 20 observation events on PEC
        for (int i = 0; i < numberObservations; i++) {
            pec.addEvPEC(new ObservationEvent(finalinst*(i+1)/20, this, graph, colony));
        }
        
        //Set ant move events
        for (int i = 0; i < antcolsize; i++) {
            colony.addAnt();
            scheduleMoveEvent(i);
        }
        
        //Run simulation
        while(current_timestamp < finalinst) {
            IEvent currentEvent = pec.nextEVPEC();
            current_timestamp = currentEvent.getTimestamp();
            currentEvent.runEvent();
        }  
    }
    
    /**Prints simulation state
     * @param obs_num - number of the observation
     * @param instant - instant of the observation
     */
   @Override
   public void print(int obs_num, double instant) {
       observation.print(obs_num, instant);
   }
   
   /**Calculates the duration of the ant move event.
    * Hence, it must randomly choose next node to visit, based on determined probability (which differs depending if all adjacent nodes were already visited or not)
    * @param ant_id - ID of the ant
    * @return move event timestamp
    */
   double getMoveEventTimestamp(int ant_id) {
       
      //Get ant path and adjacent nodes
       int[] ant_path = colony.getAntPath(ant_id);
       int[] adjacent_nodes = graph.getAdjacentNodes(ant_path[ant_path.length-1]);
       
       //Local variables
       double[] prob_array;
       Random random = new Random();
       int weight;
       
       //Check if adjacent nodes were already visited
       List<Integer> valid_nodes = new ArrayList<>();
       outer_loop: 
            for (int i = 0; i < adjacent_nodes.length; i++) {
                for (int j = 0; j < ant_path.length; j++) {
                    if (ant_path[j] == adjacent_nodes[i]) 
                        continue outer_loop;
                }
                valid_nodes.add(adjacent_nodes[i]);
            }
       
       //Calculate probabilities vector
       if (valid_nodes.isEmpty()) {
           
           //Uniform distribution
           prob_array = new double[adjacent_nodes.length];
           for (int i = 0; i < prob_array.length; i++) {
               prob_array[i] = (double) (i+1)/prob_array.length;
            }   
       } else {
           
           //Calculate individual accumulated probability
           double sum = 0, pheromone_level_local; 
           int weight_local;
           prob_array = new double[valid_nodes.size()];          
           for (int i = 0; i < prob_array.length; i++) {
               weight_local = graph.getEdgeWeight(ant_path[ant_path.length-1], valid_nodes.get(i));
               pheromone_level_local = graph.getEdgePheromoneLevel(ant_path[ant_path.length-1], valid_nodes.get(i));
               prob_array[i] = Formula.getMoveProbability(weight_local, alpha, beta, pheromone_level_local);
               sum += prob_array[i];
           }
           
           //Normalize probabilities
           double former_prob = 0;
           for (int i = 0; i < prob_array.length; i++) {
               prob_array[i] = (prob_array[i]/sum) + former_prob;
               former_prob = prob_array[i];
           }
           
       }

        //Choose next node randomly
        double rand = random.nextDouble();        
        int idx;
        for (idx = 0; idx < prob_array.length; idx++) {
            if (rand <= prob_array[idx]) 
                break;
        }
        
        //Check all adjacent nodes were already visited
        if (valid_nodes.isEmpty()) {
            
            //If is Hamiltonian
            if (adjacent_nodes[idx] == ant_path[0] && ant_path.length == colony.getNumNodes())
                colony.addToPath(ant_id, adjacent_nodes[idx]);
            else 
                colony.removeFromPath(ant_id, adjacent_nodes[idx]); //Remove cycle
            weight = graph.getEdgeWeight(ant_path[ant_path.length-1], adjacent_nodes[idx]); 

        } else {
            
            //Add to path
            colony.addToPath(ant_id, valid_nodes.get(idx));
            weight = graph.getEdgeWeight(ant_path[ant_path.length-1], valid_nodes.get(idx));
        }

       return Formula.getMoveEventTimestamp(weight, delta, simRandom);
   }
   
   /**Schedules ant move event in PEC
    * @param ant_id - ID of the ant
    */
   @Override
   public void scheduleMoveEvent(int ant_id) {
        //Check if event is done before end of simulation
        double nextTimestamp = getMoveEventTimestamp(ant_id) + current_timestamp;
        if (nextTimestamp <= finalinst)
            pec.addEvPEC(new MoveEvent(nextTimestamp, this, graph, colony, ant_id));  
   }
   
   /**Calculates the duration of the edge evaporation event.
    * @return evaporation event timestamp 
    */ 
   double getEvaporationEventTimestamp() {
        return Formula.getEvaporationEventTimestamp(eta, simRandom);
    }    
   
   /**Schedules evaporation event to PEC
    * @param node_id_src - ID of the source node
    * @param node_id_dst - ID of the destiny node
    */
   @Override
   public void scheduleEvaporationEvent(int node_id_src, int node_id_dst) {
        //Check if event is done before end of simulation
        double nextTimestamp = getEvaporationEventTimestamp() + current_timestamp;
        if (nextTimestamp <= finalinst)
            pec.addEvPEC(new EvaporationEvent(nextTimestamp, this, graph, colony, node_id_src, node_id_dst));  
   }
   
   /**Getter of pheromone level increment value
    * @param cycleWeight - weight of the cycle
    * @param graphWeight - total weight of the graph
    * @return pheromone level increment value
    */
   @Override 
   public double getPheromoneLevelIncrement(int cycleWeight, int graphWeight) {
       return Formula.getPheromoneLevelIncrement(plevel, cycleWeight, graphWeight);
   }

   /**Increments number of move events*/
   @Override 
   public void incrementNumMoves() {
       observation.incrementNumMoves();
   }
   
    /**Increments number of evaporation events*/
    @Override 
    public void incrementNumEvap() {
        observation.incrementNumEvap();
    }

    /**Getter of best Hamiltonian cycle weight 
     * @return best weight
     */
    @Override 
    public int getBestWeight() {
        return observation.getBestWeight();
    }
    
    /**Setter of best Hamiltonian cycle weight 
     * @param best_weight - defines the best weight
     */
    @Override 
    public void setBestWeight(int best_weight) {
        observation.setBestWeight(best_weight);
    }
    
    /**Setter of best Hamiltonian cycle 
     * @param hamiltonian_cycle - defines the Hamiltonian cycle
     */
    @Override 
    public void setHamiltonianCycle(String hamiltonian_cycle) {
        observation.setHamiltonianCycle(hamiltonian_cycle);
    }  
}