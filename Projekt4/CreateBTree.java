/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*       CreateBTree.java        *
********************************/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CreateBTree{
  public CreateBTree(String filename){
    try{
      FileReader fileReader = new FileReader(filename);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String line = bufferedReader.readLine();
      int degree = Integer.parseInt(line);
      BTree tree = new BTree(degree);
      while ((line = bufferedReader.readLine()) != null){
        if (isPlus(line)){
          line = bufferedReader.readLine();
          int tmp = Integer.parseInt(line);
          System.out.println("+: " + tmp);
          tree.BTreeInsert(tree, tmp);
        }
        if (isQuestionMark(line)){
          line = bufferedReader.readLine();
          int tmp = Integer.parseInt(line);
          System.out.println("?: " + tmp);
          tree.BTreeSearch(tree, tmp);
        }
        if (isInterjection(line)){
          System.out.println("\n!: Obecne B-drzewo:");
          tree.BTreePrint(tree.root);
          System.out.println();
        }
      }
      fileReader.close();
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  public static boolean isPlus(String str){
    if (str.charAt(0) == '+') return true;
    else return false;
  }

  public static boolean isQuestionMark(String str){
    if (str.charAt(0) == '?') return true;
    else return false;
  }

  public static boolean isInterjection(String str){
    if (str.charAt(0) == '!') return true;
    else return false;
  }
}
