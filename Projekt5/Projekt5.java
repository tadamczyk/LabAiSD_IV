/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*         Projekt5.java         *
********************************/

import java.util.*;

public class Projekt5{
  public static void main(String[] args){
    Graph graph = new Graph();
    Kruskal kruskal = new Kruskal();
    graph.addVertex(1);
    graph.addVertex(2);
    graph.addVertex(3);
    graph.addVertex(4);
    graph.addVertex(5);
    graph.addEdge(1, 2, 1);
    graph.addEdge(1, 5, 2);
    graph.addEdge(2, 3, 5);
    graph.addEdge(2, 4, 2);
    graph.addEdge(2, 5, 1);
    graph.addEdge(3, 4, 7);
    graph.addEdge(4, 5, 3);
    kruskal.kruskal(graph);
    System.out.println(kruskal.kruskalArray);
  }
}
