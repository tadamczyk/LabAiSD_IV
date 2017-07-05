/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*          Node.java            *
********************************/

public class Node{
  int key;
  int rank;
  Node parent;

  public Node(int key){
    this.key = key;
    this.rank = 0;
    this.parent = this;
  }

  public void setKey(int key){
    this.key = key;
  }

  public int getKey(){
    return key;
  }

  public void setRank(int rank){
    this.rank = rank;
  }

  public int getRank(){
    return rank;
  }

  public void setParent(Node parent){
    this.parent = parent;
  }

  public Node getParent(){
    return parent;
  }
}
