package com.paths;
import java.io.File;
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
                        break;
                    case 2:
                        // Algoritmo de kruskal
                        System.out.println("Kruskal");
                        break;
                    case 3:
                        System.out.println("dijkstra");
                        // Algoritmo de dijkstra
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
            salir = (scan.nextInt() == 2)? true:salir;   
        }
        scan.close();

    }

    // Procedimiento para cargar el contenido de los grafos
    public static boolean cargarGrafo(int gn){
        String direccion = "";
        switch (gn) {
            case 1:
                direccion = "act9/src/main/java/com/paths/Grafos/G1.txt";
                break;
            case 2:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G2.txt";
                break;
            case 3:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G3.txt";
                break;
            case 4:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G4.txt";
                break;
            case 5:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G5.txt";
                break;
            case 6:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G6.txt";
                break;
            case 7:
                direccion = "C:/Users/Christian/Documents/Proyectos/BusquedaEnGrafos/grafos/src/main/java/com/busqueda/Grafos/G7.txt";
                break;
            case 8:
                direccion = "act9/src/main/java/com/paths/Grafos/G8.txt";
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
                aux.setVertex(String.format("%-2s", str[0].replaceAll(" ", "")));
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
}
