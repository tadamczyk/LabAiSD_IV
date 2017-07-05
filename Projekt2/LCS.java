/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*       LCS.java - ZAD 1        *
********************************/

public class LCS {
  public String x, y;
  public int m, n;
  public int[][] C;

  public LCS(String x, String y, int m, int n, int[][] C){
    this.x = x.toUpperCase();
    this.y = y.toUpperCase();
    this.m = m;
    this.n = n;
    this.C = C;
  }
}
