/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*          Graph.java           *
********************************/

import java.util.*;

public class Graph{
  List<Integer> vertices = new ArrayList<>();
  List<Edge> edges = new ArrayList<>();

  public void addVertex(int value){
    vertices.add(value);
  }

  public void addEdge(int src, int dest, int weight){
    edges.add(new Edge(src, dest, weight));
  }

  public List<Integer> getVertices(){
    return vertices;
  }

  public List<Edge> getEdges(){
    return edges;
  }

  public void sortEdges(){
    Collections.sort(edges, new Comparator<Edge>(){
      public int compare(Edge e1, Edge e2){
        if (e1.getWeight() > e2.getWeight()){
          return 1;
        }
        else{
          if (e1.getWeight() < e2.getWeight()){
            return -1;
          }
          else{
            return 0;
          }
        }
      }
    });
  }
}
