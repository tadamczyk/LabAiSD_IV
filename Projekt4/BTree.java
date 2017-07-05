/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*          BTree.java           *
********************************/

public class BTree{
  int degree;
  Node root;

  public BTree(int degree){
    root = new Node(null, degree);
    this.degree = degree;
  }

  public Node Search(Node x, int k){
    int i = 0;
    while (i < x.counter && k > x.key[i]){
      i++;
    }
    if (i < x.counter && k == x.key[i]){
      return x;
    }
    if (x.leaf){
      return null;
    }
    else{
      return Search(x.getChild(i), k);
    }
  }

  public void BTreeSearch(BTree tree, int x){
    Node tmp = new Node(null, degree);
    tmp = Search(tree.root, x);
    if (tmp == null){
      System.out.println("Brak szukanego elementu w drzewie.");
    }
    else{
      System.out.println("Znaleziono szukany element w drzewie.");
    }
  }

  public void BTreeSplitChild(Node x, int i, Node y){
    Node z = new Node(null, degree);
    z.leaf = y.leaf;
    z.counter = degree - 1;
    for (int j = 0; j < degree - 1; j++){
      z.key[j] = y.key[j+degree];
    }
    if (!y.leaf){
      for (int j = 0; j < degree; j++){
        z.child[j] = y.child[j+degree];
      }
    }
    y.counter = degree - 1;
    for (int j = x.counter; j > i; j--){
      x.child[j+1] = x.child[j];
    }
    x.child[i+1] = z;
    for (int j = x.counter - 1; j > i - 1; j--){
      x.key[j+1] = x.key[j];
    }
    x.key[i] = y.key[degree-1];
    x.counter++;
    y.key[degree-1] = 0;
    for (int j = 0; j < degree - 1; j++){
      y.key[j+degree] = 0;
    }
  }

  public void BTreeInsert(BTree T, int k){
    Node r = T.root;
    if (r.counter == (2*degree - 1)){
      Node s = new Node(null, degree);
      T.root = s;
      s.leaf = false;
      s.counter = 0;
      s.child[0] = r;
      BTreeSplitChild(s, 0, r);
      BTreeInsertNonFull(s, k);
    }
    else{
      BTreeInsertNonFull(r, k);
    }
  }

  public void BTreeInsertNonFull(Node x, int k){
    int i = x.counter;
    if (x.leaf){
      while (i >=1 && k < x.key[i-1]){
        x.key[i] = x.key[i-1];
        i--;
      }
      x.key[i] = k;
      x.counter++;
    }
    else{
      int j = 0;
      while (j < x.counter && k > x.key[j]){
        j++;
      }
      if (x.child[j].counter == (2*degree - 1)){
        BTreeSplitChild(x, j, x.child[j]);
        if (k > x.key[j]){
          j++;
        }
      }
      BTreeInsertNonFull(x.child[j], k);
    }
  }

  public void BTreePrint(Node n){
    for (int i = 0; i < n.counter; i++){
      if (i == 0){
        System.out.print("( ");
      }
      System.out.print(n.getKeyValue(i));
      if (i >= 0 && i < n.counter - 1){
        System.out.print(" | ");
      }
      if (i == n.counter - 1){
        System.out.print(" )");
      }
    }
    if (!n.leaf){
      System.out.print("\n[");
      for (int j = 0; j <= n.counter; j++){
        if (n.getChild(j) != null){
          BTreePrint(n.getChild(j));
        }
        if (j < n.counter){
          System.out.print(" | ");
        }
      }
      System.out.print("]\n");
    }
  }
}
