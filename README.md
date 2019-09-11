# Travelling salesman problem by ant colony optimization in Java

This project was developed for the Object Oriented Programming course. The travelling salesman problem (TSP) aims to find the shortest cycle in a weighted graph that passes through all its nodes (only once). The ant colony optimization (ACO) algorithm is a possible approach for solving optimization problems on graphs like the TSP. It simulates ants that randomly go through the graph and lay down pheromone trails after finding a solution to the problem.

## Approach

During the simulation, an ant will go through the graph to find a Hamiltonian cycle (i.e. contains all nodes which occur only once in the cycle, except for the nest node which occurs twice) and return to the nest. The goal is to find the shortest Hamiltonian cycle discovered until ending the simulation time. The simulation consists on a discrete stochastic simulation (i.e. based on a pending event container) guided through two discrete events: ant move (ant randomly chooses which node to go) and pheromone evaporation (after finding a Hamiltonian cycle).

## Input XML file
Describes the input of the simulation with the following elements and respective attributes:
```
**simulation:** contains the simulation time (finalinst), the ant colony size (antcolsize) and the parameter concerning the pheromone level(plevel)
**graph:** contains the number of nodes (nbnodes) and the nest node (nestnode)
**node:** contains the index of a node (nodeidx) 
**weight:** contains the index of the target node (targetnode) and the weight of the edge
**events:** contains the parameters of the ant move event (alpha, beta and delta) and the pheromone evaporation event (eta and rho)
```
## Results
The program prints to the terminal the result of observations of the system, 20 times across the simulation time, with the following format:
```
Observation number: [1...20]
	Present instant: 				[instant]
	Number of move events:			[mevents]
	Number of evaporation events:	[eevents]
	Hamiltonian cycle:				[cycle]
```

## Data structures

## Object oriented solution

## Tests

## To run the program
