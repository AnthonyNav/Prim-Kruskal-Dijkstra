package com.paths;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    private static ListaLSimple adjacent; // listas de adyacencias
    private static HashMap<String,String> father; // Arreglo padre
    private static HashMap<String, Integer> distance; // Arreglo de distancia
    private static HashMap<String, Boolean> visited; // Arreglo de visitado
    private static String[] sequence; // Orden
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        boolean salir = false;
        boolean salirMenu;
        int op;

        while (!salir) {
            boolean cargaCompleta = false;
            // Cargamos los datos del grafo
            while (!cargaCompleta) {
                System.out.println("\n\n");
                System.out.println("Ingrese el grafo que desea (1-8):");
                cargaCompleta = cargarGrafo(scan.nextInt());
            }
            salirMenu = false;
            while (!salirMenu) {
                System.out.println("\n\nQue Algoritmo desea?\n1)Arbol de expancion minimo (Prim)\n2)Arbol de expancion minimo (Kruskar)\n3)Camino Minimo (Dijkstra)\n4)Salir");
                op = scan.nextInt();
                switch (op) {
                    case 1:
                        // Algoritmo de prim 
                        System.out.println("Prim");
                        System.out.println("Dame un vertice inicial:");
                        scan.nextLine(); // Consumir el salto de línea pendiente
                        prim(scan.nextLine().replaceAll(" ","").toUpperCase());
                        break;
                    case 2:
                        // Algoritmo de kruskal
                        System.out.println("Kruskal");
                        kruskal();
                        break;
                    case 3:
                        System.out.println("Dame un vertice inicial:");
                        // Algoritmo de dijkstra
                        scan.nextLine();
                        dijkstra(scan.nextLine().replaceAll(" ","").toUpperCase());
                        break;
                    case 4:
                        salirMenu = true;
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }

            }
            System.out.println("\n\nDesea probar con otro grafo?\n1)Si\n2)No");
            salir = scan.nextInt() == 2;
        }
        scan.close();

    }

    // Procedimiento para cargar el contenido de los grafos
    public static boolean cargarGrafo(int gn){
        String direccion = "";
        switch (gn) {
            case 1:
                direccion = "src/main/java/com/paths/Grafos/G1.txt";
                break;
            case 2:
                direccion = "src/main/java/com/paths/Grafos/G2.txt";
                break;
            case 3:
                direccion = "src/main/java/com/paths/Grafos/G3.txt";
                break;
            case 4:
                direccion = "src/main/java/com/paths/Grafos/G4.txt";
                break;
            case 5:
                direccion = "src/main/java/com/paths/Grafos/G5.txt";
                break;
            case 6:
                direccion = "src/main/java/com/paths/Grafos/G6.txt";
                break;
            case 7:
                direccion = "src/main/java/com/paths/Grafos/G7.txt";
                break;
            case 8:
                direccion = "src/main/java/com/paths/Grafos/G8.txt";
                break;
            default:
                System.out.println("Opcion no valida");
                return false;
        }

        try{
            File archivo = new File(direccion);
            // Crear un objeto Scanner para leer el archivo
            Scanner scanner = new Scanner(archivo);
            // Declaracion de variables
            String linea;
            String[] str, vert;
            adjacent = new ListaLSimple();
            ListaLSimple aux = adjacent;
            father = new HashMap<>();
            visited = new HashMap<>();
            distance = new HashMap<>();

            // Leer el archivo línea por línea
            while (scanner.hasNextLine()) {
                linea = scanner.nextLine();
                // System.out.println(linea);                     
                str = linea.split(":");
                //aux.setVertex(String.format("%-2s", str[0].replaceAll(" ", "")));
                aux.setVertex(str[0].replaceAll(" ", ""));
                // Inicializacion de arreglos
                father.put(str[0].replaceAll(" ", ""), "-");
                distance.put(str[0].replaceAll(" ", ""), Integer.MAX_VALUE);
                visited.put(str[0].replaceAll(" ", ""), false);
                // Inicializacion de la lista adyacente
                str = str[1].split(" ");
                for (String s : str) {
                    // Ingresando datos a lalista de adyacencia
                    vert = s.split("-");
                    aux.insert(vert[0], Integer.parseInt(vert[1]));
                }


                System.out.println("Lista "+aux.getVertex()+": " + aux); // Para ver lo como esta leyendo el archivo
                aux.setNextList(new ListaLSimple());
                aux = aux.getNextList();
            }
            // Cerrar el Scanner después de leer todas las líneas
            scanner.close();
            sequence = new String[father.size()];
            // Captura de los vertices en formato String en un arreglo
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public static void dijkstra(String nodeInit){
        int i;String v;
        Node auxP = null;
        // Buscando su lista de adyacencia
        ListaLSimple auxL = adjacent;
        while (auxL != null && auxL.getVertex().compareTo(nodeInit) != 0 ){
            System.out.println(auxL.getVertex());
            auxL = auxL.getNextList();
        }
        // Preparando los arreglos
        String[] nodes = distance.keySet().toArray(new String[0]);
        for (i = 0; i < father.size(); i++) {
            auxP = auxL.getNodeX(nodes[i].replaceAll(" ",""));
            if (auxP != null){
                distance.replace(auxP.getVertex(),auxP.getWeight());
            }
            if (nodes[i].compareTo(nodeInit) != 0){
                father.replace(nodes[i] , nodeInit);
            }
        }
        //Inicializando los valores para nuestro vertice inicial
        distance.replace(nodeInit,0); //Usamos el -1 para indicar que nuestro
        father.replace(nodeInit,"-");
        visited.replace(nodeInit,true);

//        for (i = 0; i < father.size(); i++) {
//            System.out.println( i +": "+ nodes[i] + " " + String.valueOf(distance.get(nodes[i])));
//            System.out.println( i +": "+ nodes[i] + " " + String.valueOf(visited.get(nodes[i])));
//            System.out.println( i +": "+ nodes[i] + " " + String.valueOf(father.get(nodes[i]))+"\n");
//            }

        // Inicio del algoritmo

        for (i = 0; i < visited.size()-1 ; i++) {
            v = vMinimo();
            //System.out.println("El Vertice de distancia minimo es: "+v);
            visited.replace(v,true);
            auxL = adjacent; // Preparando la busqueda de la lista de w
            while (auxL.getVertex().compareTo(v)!=0){
                auxL = auxL.getNextList();
            }
            auxP = auxL.getStart();
            while (auxP!=null){
                if (!visited.get(auxP.getVertex())&&(distance.get(v)+auxP.getWeight()<distance.get(auxP.getVertex()))){
                    distance.replace(auxP.getVertex(),distance.get(v)+auxP.getWeight());
                    father.replace(auxP.getVertex(),v);
                }
                auxP = auxP.getNext();
            }
        }
        System.out.println("Impresion de los arreglos\nDistancia");
        System.out.println(distance.entrySet());
        System.out.println("Caminos");
        System.out.println(father.entrySet());
        Scanner scan = new Scanner(System.in);
        System.out.println("Dame el vertice final");
        v = scan.nextLine().toUpperCase().replaceAll(" ","");
        printPath(v, nodeInit);
        System.out.println("\nCosto:"+ distance.get(v));
    }

    public static String vMinimo(){ // Regresa el vertice minimo de D que no este visitado, sino existe retorna null
        String aux = null;
        int auxv = Integer.MAX_VALUE, i;
        String[] vertNodes = visited.keySet().toArray(new String[0]);
        for (i = 0; i < visited.size(); i++) {
            if (!visited.get(vertNodes[i])){
                if (distance.get(vertNodes[i])<auxv){
                    auxv = distance.get(vertNodes[i]);
                    aux = vertNodes[i];
                }
            }
        }
        return aux;
    }

    public static void printPath(String vf, String vi){
        if (vf.compareTo(vi)!=0) printPath(father.get(vf), vi);
        System.out.print(vf + " " );
    }

    public static void prim(String nodeInit) {
        int i;
        // Inicialización de las estructuras de datos
        for (String vertex : father.keySet()) {
            visited.put(vertex, false);
            father.put(vertex, "-");
            distance.put(vertex, Integer.MAX_VALUE);
        }

        // El vértice inicial tiene un peso de 0
        distance.put(nodeInit, 0);

        // Algoritmo de Prim
        for (i = 0; i < visited.size() - 1; i++) {
            String u = minKey(distance, visited); // Seleccionamos el vértice con la clave mínima

            visited.put(u, true); // Agregamos el vértice al árbol

            // Actualizamos los pesos y padres de los vértices adyacentes a u
            ListaLSimple adjList = adjacent;
            while (adjList != null && !adjList.getVertex().equals(u)) {
                adjList = adjList.getNextList();
            }
            if (adjList != null) {
                Node adjNode = adjList.getStart();
                while (adjNode != null) {
                    String v = adjNode.getVertex();
                    int weight = adjNode.getWeight();
                    if (!visited.get(v) && weight < distance.get(v)) {
                        father.put(v, u);
                        distance.put(v, weight);
                    }
                    adjNode = adjNode.getNext();
                }
            }
        }

        // Mostrar el árbol de expansión mínimo y su peso total
        int totalWeight = 0;
        System.out.println("Arbol de expansion minimo:");
        for (String vertex : father.keySet()) {
            if (!father.get(vertex).equals("-")) {
                System.out.println(father.get(vertex) + " - " + vertex);
                totalWeight += distance.get(vertex);
            }
        }
        System.out.println("Peso total del arbol: " + totalWeight);
    }

    // Método auxiliar para encontrar la clave mínima en el mapa de distancias
    private static String minKey(HashMap<String, Integer> distance, HashMap<String, Boolean> visited) {
        int min = Integer.MAX_VALUE;
        String minKey = null;
        for (String vertex : distance.keySet()) {
            if (!visited.get(vertex) && distance.get(vertex) < min) {
                min = distance.get(vertex);
                minKey = vertex;
            }
        }
        return minKey;
    }
    
    //Kruskal
    private static ArrayList<Edge> getAllEdges() {
    ArrayList<Edge> edges = new ArrayList<>();
    ListaLSimple aux = adjacent;
    while (aux != null) {
        Node node = aux.getStart();
        while (node != null) {
            edges.add(new Edge(aux.getVertex(), node.getVertex(), node.getWeight()));
            node = node.getNext();
        }
        aux = aux.getNextList();
    }
    return edges;
    }
    
    public static void kruskal() {
    ArrayList<Edge> allEdges = getAllEdges();
    Collections.sort(allEdges); // Ordenar todas las aristas por peso

    // Inicializar conjuntos disjuntos
    HashMap<String, String> parent = new HashMap<>();
    for (String vertex : father.keySet()) {
        parent.put(vertex, vertex);
    }

    // Algoritmo de Kruskal
    ArrayList<Edge> minimumSpanningTree = new ArrayList<>();
    int totalWeight = 0;
    for (Edge edge : allEdges) {
        String sourceParent = findParent(edge.getSource(), parent);
        String destinationParent = findParent(edge.getDestination(), parent);

        if (!sourceParent.equals(destinationParent)) {
            // No forma ciclo, agregar al �rbol de expansi�n m�nima
            minimumSpanningTree.add(edge);
            totalWeight += edge.getWeight();
            // Unir los conjuntos
            parent.put(destinationParent, sourceParent);
        }
    }

    // Mostrar el �rbol de expansi�n m�nima y su peso total
    System.out.println("Arbol de expansion minimo (Kruskal):");
    for (Edge edge : minimumSpanningTree) {
        System.out.println(edge.getSource() + " - " + edge.getDestination() + " : " + edge.getWeight());
    }
    System.out.println("Peso total del arbol: " + totalWeight);
    }

    private static String findParent(String vertex, HashMap<String, String> parent) {
    if (!parent.get(vertex).equals(vertex)) {
        parent.put(vertex, findParent(parent.get(vertex), parent));
    }
    return parent.get(vertex);
    }
}

