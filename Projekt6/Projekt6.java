/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*         Projekt6.java         *
********************************/

import java.io.*;

public class Projekt6{
  private static String RESET = "\u001B[0m";
  private static String RED   = "\u001B[1;31m";
  private static String GREEN = "\u001B[1;32m";
  private static int NANOSEC  = 1000000000;

  private static char[] pattern;
  private static char[] text;
  private static int patternLen;
  private static int textLen;
  private static String[][] finalResults;

  private static int getArrayLength(String filename){
    File file = new File(filename);
    int counter = 0;
    if (!file.exists()){
      System.out.println(filename + " does not exist.");
      System.exit(0);
    }
    try{
      FileInputStream fis = new FileInputStream(file);
      char current;
      while (fis.available() > 0){
        current = (char) fis.read();
        if (current != '\n'){
          counter++;
        }
      }
    }
    catch (IOException e){
      e.printStackTrace();
    }
    return counter;
  }

  private static void getDataToArray(String filename, char[] array){
    File file = new File(filename);
    int index = 0;
    try{
      FileInputStream fis = new FileInputStream(file);
      char current;
      while (fis.available() > 0){
        current = (char) fis.read();
        if (current != '\n'){
          array[index++] = current;
        }
      }
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  private static void calculateTimesAndShowResults(
          char[] pattern, char[] text, String[][] finalResults){
    finalResults[0][0] = "ALGORITHM";
    finalResults[0][1] = "Simple";
    finalResults[0][2] = "RabinKarp";
    finalResults[0][3] = "KMP";
    finalResults[1][0] = "TIME";
    long start = 0, stop = 0;
    start = System.nanoTime();
    Simple.algorithm(pattern, text);
    stop = System.nanoTime() - start;
    finalResults[1][1] = Double.toString((double) stop/NANOSEC) + "s";
    String min = finalResults[1][1];
    String max = finalResults[1][1];
    long minL = 0, maxL = 0;
    minL = maxL = stop;
    start = System.nanoTime();
    RabinKarp.algorithm(pattern, text);
    stop = System.nanoTime() - start;
    finalResults[1][2] = Double.toString((double) stop/NANOSEC) + "s";
    if (stop > maxL){
      max = finalResults[1][2];
      maxL = stop;
    }
    if (stop < minL){
      min = finalResults[1][2];
      minL = stop;
    }
    start = System.nanoTime();
    KMP.algorithm(pattern, text);
    stop = System.nanoTime() - start;
    finalResults[1][3] = Double.toString((double) stop/NANOSEC) + "s";
    if (stop > maxL){
      max = finalResults[1][3];
      maxL = stop;
    }
    if (stop < minL){
      min = finalResults[1][3];
      minL = stop;
    }
    showFinalResults(finalResults, min, max);
  }

  private static void showFinalResults(String[][] a, String min, String max){
    System.out.println("\n------------------------------------"
                       + "------------------------------------");
    for (int i = 0; i < 2; i++){
      for (int j = 0; j < 4; j++){
        System.out.print("| ");
        if (min == a[i][j]){
          System.out.print(GREEN);
        }
        if (max == a[i][j]){
          System.out.print(RED);
        }
        System.out.printf("%14s", a[i][j]);
        System.out.print(RESET);
        System.out.print(" |");
      }
      System.out.println("\n------------------------------------"
                         + "------------------------------------");
    }
  }

  public static void main(String[] args){
    patternLen = getArrayLength(args[0]);
    pattern = new char[patternLen];
    getDataToArray(args[0], pattern);
    textLen = getArrayLength(args[1]);
    text = new char[textLen];
    getDataToArray(args[1], text);
    finalResults = new String[2][4];
    calculateTimesAndShowResults(pattern, text, finalResults);
  }
}
