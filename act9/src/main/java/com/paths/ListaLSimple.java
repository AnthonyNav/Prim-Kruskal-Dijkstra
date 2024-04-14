package com.paths;

public class ListaLSimple {

    private Node start; // Identifica el nodo inicial
    private ListaLSimple nextList; // Enlaza la siguiente lista de adyacencia
    
    public ListaLSimple() {
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
    }

    public Node getNodeX(String vertex){ // Nos retorna el nodo con el vertice ingresado por parametros
        Node auxNodo = start;
        while (auxNodo.getVertex().compareTo(vertex) != 0 && auxNodo != null) { 
            auxNodo = auxNodo.getNext();
        }
        return auxNodo; // Si existe el vertice, retorna el nodo, sino retornara null
    }

    
 
    public boolean estaVacia(){
        return start==null;
    }

    

    

}
