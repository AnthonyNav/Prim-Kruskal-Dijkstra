package com.paths;

public class ListaLSimple {
    private String vertex; // Id del vertice 
    private Node start; // Identifica el nodo inicial
    private ListaLSimple nextList; // Enlaza la siguiente lista de adyacencia
    private int nodes; // Representa la cantidad de adyacencias que tiene
    
    public ListaLSimple() {
        nodes = 0;
        start = null;
        nextList = null;
    }
    // Getters y setters por defecto
    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public ListaLSimple getNextList() {
        return nextList;
    }

    public void setNextList(ListaLSimple nextList) {
        this.nextList = nextList;
    }
    public String getVertex() {
        return vertex;
    }
    public void setVertex(String vertex) {
        this.vertex = vertex;
    }
    // Metodos de insercion
    public void insert(String vertex, int weight){
        if (this.estaVacia()) {
            this.start = new Node(vertex, weight);
        } else {
            Node auxNodo = this.start;
            while (auxNodo.getNext() != null) { // Recorre la lista
                auxNodo = auxNodo.getNext();
            }
            auxNodo.setNext(new Node(vertex, weight)); // Inserta la referencia al nuevo nodo
        }
        nodes++;
    }

    public Node getNodeX(String vertex){ // Nos retorna el nodo con el vertice ingresado por parametros
        Node auxNodo = start;
        while (auxNodo.getVertex().compareTo(vertex) != 0) {
            auxNodo = auxNodo.getNext();
            if (auxNodo != null) {
                break;
            }
        }
        return auxNodo; // Si existe el vertice, retorna el nodo, sino retornara null
    }

    public int[] getWeights(){
        int i=0;
        int[] n = new int[nodes];
        Node auxNode = this.start;
        while (auxNode != null) {
            n[i++]=auxNode.getWeight();
            auxNode = auxNode.getNext();

        }
        return n;
    }

    public String[] getVertices(){
        int i=0;
        String[] n = new String[nodes];
        Node auxNode = this.start;
        while (auxNode != null) {
            n[i++]=auxNode.getVertex();
            auxNode = auxNode.getNext();
        }
        return n;
    }
    
 
    public boolean estaVacia(){
        return start==null;
    }
    @Override
    public String toString(){ // Asume que no esta vacia la lista
        String output = "";
        String aux = "";
        Node auxNodo = this.start;
        while (auxNodo != null) { // Recorre la lista
            aux = String.format("%-3s-%3d", auxNodo.getVertex(), auxNodo.getWeight());
            output = output + " | "+ aux; // concatena
            auxNodo = auxNodo.getNext();
        }
        return output+" |"; // Retorna nuestra cadena que representa los elementos existentes
    }
}
