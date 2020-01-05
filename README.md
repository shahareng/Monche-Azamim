# OOP_Graphs
This project represents a directional weighted graph.

In this project we created three main classes: DGraph, Graph_Algo, and a Graphic interface.
and some minor class such as: node, edge.

DGraph implements the interface graph.
each graph has two hasmaps, one for nodes and another one for edges.
Esize-number of total edges
MC- a number represents the version of the graph if there has been a change to the graph such ass adding or deleting a node or an edge
the MC will change by +1.

Graph_Algo implements the interface graph_algorithms.
in this class there are a number of functions you can apply on the graph.
isConnected - returns whethere the graph is strongly connected.
init - initialize a grpah from a file.
shortestpath - return a list of nodes that create the shortest path between two nodes.
shortestpathdist - returns the weights of the path between two nodes

GUI represents a visual class of the graph.

in this class you can create a window in which you can: 
see the graph.
look fot paths between nodes.
load and save graph.
 

