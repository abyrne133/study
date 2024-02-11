package com.abyrne.graphs;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Node {
    Object value;
    Node[] adjacentNodes;

    public boolean breadthFirstSearch(Node destination) {
        Set<Node> visited = new HashSet<>();
        Node root = this;
        visited.add(root);
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node == null) {
                continue;
            }
            if (node.equals(destination)) {
                return true;
            }
            visited.add(node);
            if (node.adjacentNodes == null) {
                continue;
            }
            for (Node n : node.adjacentNodes) {
                if (!visited.contains(n)) {
                    queue.add(n);
                }
            }

        }
        return false;
    }
}
