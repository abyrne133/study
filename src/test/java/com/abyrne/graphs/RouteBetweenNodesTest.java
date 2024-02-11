package com.abyrne.graphs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteBetweenNodesTest {

    @Test
    void isAnyRoute_givenOneRoute_returnsTrue() {
        Node nodeOne = new Node();
        Node nodeTwo = new Node();

        nodeOne.adjacentNodes = new Node[]{new Node(), nodeTwo};

        assertTrue(new RouteBetweenNodes().isAnyRoute(nodeOne, nodeTwo));
        assertTrue(new RouteBetweenNodes().isAnyRoute(nodeTwo, nodeOne));
    }

    @Test
    void isAnyRoute_givenBothDirectionRoute_returnsTrue() {
        Node nodeOne = new Node();
        Node nodeTwo = new Node();

        nodeOne.adjacentNodes = new Node[]{new Node(), nodeTwo};
        nodeTwo.adjacentNodes = new Node[]{new Node(), nodeOne, new Node()};

        assertTrue(new RouteBetweenNodes().isAnyRoute(nodeOne, nodeTwo));
        assertTrue(new RouteBetweenNodes().isAnyRoute(nodeTwo, nodeOne));
    }

    @Test
    void isDirectionalRoute_givenOneRoute_returnsTrue(){
        Node nodeOne = new Node();
        Node nodeTwo = new Node();

        nodeOne.adjacentNodes = new Node[]{new Node(), nodeTwo};

        assertTrue(new RouteBetweenNodes().isDirectionalRoute(nodeOne, nodeTwo));
        assertFalse(new RouteBetweenNodes().isDirectionalRoute(nodeTwo, nodeOne));
    }

    @Test
    void isDirectionalRoute_givenBothDirectionRoute_returnsTrue(){
        Node nodeOne = new Node();
        Node nodeTwo = new Node();

        nodeOne.adjacentNodes = new Node[]{new Node(), nodeTwo};
        nodeTwo.adjacentNodes = new Node[]{new Node(), nodeOne, new Node()};

        assertTrue(new RouteBetweenNodes().isDirectionalRoute(nodeOne, nodeTwo));
        assertTrue(new RouteBetweenNodes().isDirectionalRoute(nodeTwo, nodeOne));
    }
}
