package com.abyrne.graphs;


import java.util.LinkedList;
import java.util.Queue;

public class Node {
    Object value;
    Node[] adjacentNodes;
    boolean isVisited;


    public boolean breadthFirstSearch(Node destination) {
        Node root = this;
        root.isVisited = true;
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
            node.isVisited = true;
            if (node.adjacentNodes == null) {
                continue;
            }
            for (Node n : node.adjacentNodes) {
                if (!n.isVisited) {
                    queue.add(n);
                }
            }

        }
        return false;
    }
}
