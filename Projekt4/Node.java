/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*          Node.java            *
********************************/

public class Node{
  int order;
  int key[];
  int counter;
  Node parent;
  Node child[];
  boolean leaf;

  public Node(Node parent, int order){
    this.order = order;
    key = new int[2*order-1];
    counter = 0;
    this.parent = parent;
    child = new Node[2*order];
    leaf = true;
  }

  public int getKeyValue(int index){
    return key[index];
  }

  public Node getChild(int index){
    return child[index];
  }
}
