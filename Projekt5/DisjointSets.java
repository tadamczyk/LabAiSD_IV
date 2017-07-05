/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*       DisjointSets.java       *
********************************/

import java.util.*;

public class DisjointSets{
  public static Node findSet(Node x){
    if (x != x.parent){
      x.parent = findSet(x.parent);
    }
    return x.parent;
  }

  public static void union(Node x, Node y){
    if (x.rank > y.rank){
      y.parent = x;
    }
    else{
      x.parent = y;
      if (x.rank == y.rank){
        y.rank++;
      }
    }
  }
}
