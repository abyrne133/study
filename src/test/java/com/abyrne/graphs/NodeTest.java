package com.abyrne.graphs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NodeTest {

    @Test
    void breadthFirstSearch_givenPathBetween_returnsTrue() {
        Node rootNode = new Node();
        rootNode.value = 25;
        Node targetNode = new Node();
        targetNode.value = 55;
        rootNode.adjacentNodes = new Node[]{new Node(), new Node(), new Node(), targetNode};

        assertTrue(rootNode.breadthFirstSearch(targetNode));
    }

    @Test
    void breathFirstSearch_givenNoPathBetween_returnsFalse() {
        Node rootNode = new Node();
        rootNode.value = 25;
        Node targetNode = new Node();
        targetNode.value = 55;
        rootNode.adjacentNodes = new Node[]{new Node(), new Node(), new Node()};

        assertFalse(rootNode.breadthFirstSearch(targetNode));
    }
}
