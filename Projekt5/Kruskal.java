/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*         Kruskal.java          *
********************************/

import java.util.*;

public class Kruskal{
  public static List<Edge> kruskalArray;

  public static void kruskal(Graph graph){
    DisjointSets d = new DisjointSets();
    kruskalArray = new ArrayList<>();
    Node[] vertices = new Node[graph.getVertices().size()+1];
    for (int i = 1; i < graph.getVertices().size()+1; i++){
      vertices[graph.getVertices().get(i-1)] =
              new Node(graph.getVertices().get(i-1));
    }
    graph.sortEdges();
    for (Edge edge : graph.getEdges()){
      if (d.findSet(vertices[edge.getSrc()]) !=
          d.findSet(vertices[edge.getDest()])){
        kruskalArray.add(edge);
        d.union(vertices[edge.getSrc()], vertices[edge.getDest()]);
      }
    }
  }
}
