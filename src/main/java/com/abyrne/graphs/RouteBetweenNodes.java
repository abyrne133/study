package com.abyrne.graphs;

public class RouteBetweenNodes {
    public boolean isAnyRoute(Node nodeOne, Node nodeTwo) {
        return isDirectionalRoute(nodeOne, nodeTwo) || isDirectionalRoute(nodeTwo, nodeOne);
    }

    public boolean isDirectionalRoute(Node from, Node to) {
        return from.breadthFirstSearch(to);
    }
}

