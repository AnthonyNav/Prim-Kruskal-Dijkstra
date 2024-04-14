package com.paths;

public class Node {

    private String vertex;
    private int weight;
    private Node next;

    public Node(String vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
        next = null;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public String getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Node [vertex=" + vertex + ", weight=" + weight + ", next=" + next + "]";
    }    

}
