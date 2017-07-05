/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*           Edge.java           *
********************************/

public class Edge{
  int src;
  int dest;
  int weight;

  public Edge(int src, int dest, int weight){
    this.src = src;
    this.dest = dest;
    this.weight = weight;
  }

  public int getSrc(){
    return src;
  }

  public int getDest(){
    return dest;
  }

  public int getWeight(){
    return weight;
  }

  public String toString(){
    return this.src + " - " + this.dest + " => weight: " + this.weight;
  }
}
