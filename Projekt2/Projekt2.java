/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*     Projekt2.java - ZAD 1     *
********************************/

import java.io.*;

public class Projekt2{

  private LCS createLCSTable(String x, String y){
    int m = x.length();
    int n = y.length();
    int[][] C = new int[m+1][n+1];
    for (int i = 1; i <= m; i++){
      C[i][0] = 0;
    }
    for (int j = 0; j <= n; j++){
      C[0][j] = 0;
    }
    for (int i = 1; i <= m; i++){
      for (int j = 1; j <= n; j++){
        if (x.charAt(i-1) == y.charAt(j-1)){
          C[i][j] = C[i-1][j-1] + 1;
        }
        else if (C[i-1][j] >= C[i][j-1]){
          C[i][j] = C[i-1][j];
        }
        else{
          C[i][j] = C[i][j-1];
        }
      }
    }
    return new LCS(x, y, m, n, C);
  }

  private int LCSLength(LCS newLcs){
    return newLcs.C[newLcs.m][newLcs.n];
  }

  private String LCSWord(String x, String y, int i, int j){
    String s1, s2;
    if (i == 0 || j == 0){
      return "";
    }
    if (x.charAt(i-1) == y.charAt(j-1)){
      return LCSWord(x.substring(0, i), y.substring(0, j), i-1, j-1)
             + x.charAt(i-1);
    }
    else{
      s1 = LCSWord(x, y.substring(0, j), i, j-1);
      s2 = LCSWord(x.substring(0, i), y, i-1, j);
    }
    return (s1.length() > s2.length()) ? s1 : s2;
  }

  public void printLCSLength(LCS newLcs){
    System.out.println("Length LCS(" + newLcs.x +", " + newLcs.y + ") = "
                       + LCSLength(newLcs));
  }

  public void printLCSWord(LCS newLcs){
    System.out.println("Word LCS(" + newLcs.x +", " + newLcs.y + ") = "
                       + LCSWord(newLcs.x, newLcs.y, newLcs.x.length(), newLcs.y.length()));
  }

  public static void main(String[] args){
    Projekt2 projekt2 = new Projekt2();
    LCS newLcs = projekt2.createLCSTable(args[0], args[1]);
    projekt2.printLCSLength(newLcs);
    projekt2.printLCSWord(newLcs);
  }
}
