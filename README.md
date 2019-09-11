# Travelling salesman problem by ant colony optimization in Java

This project was developed for the Object Oriented Programming (OOP) course. The travelling salesman problem (TSP) aims to find the shortest cycle in a weighted graph that passes through all its nodes (only once). The ant colony optimization (ACO) algorithm is a possible approach for solving optimization problems on graphs like the TSP. It simulates ants that randomly go through the graph and lay down pheromone trails after finding a solution to the problem.

## Approach

During the simulation, an ant will go through the graph to find a Hamiltonian cycle (i.e. contains all nodes which occur only once in the cycle, except for the nest node which occurs twice) and return to the nest. The goal is to find the shortest Hamiltonian cycle discovered until ending the simulation time. The simulation consists on a discrete stochastic simulation (i.e. based on a pending event container) guided through two discrete events: ant move (ant randomly chooses which node to go) and pheromone evaporation (after finding a Hamiltonian cycle).

## Input XML file

Describes the input of the simulation with the following elements and respective attributes:
```
simulation: contains the simulation time (finalinst), the ant colony size (antcolsize) and the parameter concerning the pheromone level(plevel)
graph: contains the number of nodes (nbnodes) and the nest node (nestnode)
node: contains the index of a node (nodeidx) 
weight: contains the index of the target node (targetnode) and the weight of the edge
events: contains the parameters of the ant move event (alpha, beta and delta) and the pheromone evaporation event (eta and rho)
```
## Results

The program prints to the terminal the result of observations of the system, 20 times across the simulation time, with the following format:
```
Observation number: [1...20]
	Present instant:                [instant]
	Number of move events:          [mevents]
	Number of evaporation events:   [eevents]
	Hamiltonian cycle:				[cycle]
```

## Data structures

**Graph:** HashMap where the key is the node ID and the value is the node itself. Initialized with the number of nodes (to avoid the resizing of the hashmap). Complexity is O(1) for adding and finding by key.

**Edges:** HashMap initialized with the number of nodes minus 1.

**Ant path:** ArrayList initialized with the number of nodes plus 1. Complexity is O(1) to add or remove elements at the end of the list and to access to any position of the list.

**PEC:** PriorityQueue initialized with the max number of events at any given instant (number of observations + number of nodes + number of edges). Complexity is O(log n) to add and remove the most priority element.

## Object oriented solution

The project follows some basic concepts of OOP:

**Open-close principle:** abstraction and polymorphism applied in the Event class with the runEvent() method

**Extensibility:** the program was designed with a total of 5 interfaces for the Event, Graph, Colony, Simulator and PEC classes given space for them to be implemented in other ways.

**Encapsulation principle:** classes' fields are not accessible outside the class (private or protected).

## Tests

5 different XML input files are present in the TESTS folder:

**test_1:** evaluates the performance of the ant and PEC data structures

**test_2:** evaluates the performance of the node and ant path data structures

**test_3:** evaluates the performance of the edge data structure

**test_4:** manipulates parameters regarding the evaporation event

**test_5:** case where it is difficult to find a Hamiltonian cycle

## Exceptions

The program has one exception for checking the coherency of the XML input file regarding: simulation parameters (must be positive), nest node (must exists in the graph), number of nodes (must correspond to the number of node IDs), duplicated edges and edges connecting the same nodes.

## To run the program

By default, the Makefile runs the program with the test_1.xml file:
```
make 
```

To change the input file (for example, for test_2.xml), run:
```
make TEST=test_2
```

## To clean repo
```
make clean
```