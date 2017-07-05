/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*           KMP.java            *
********************************/

public class KMP{
  public static void algorithm(char[] pattern, char[] text){
    System.out.println("\nKNUTH-MORRIS-PRATT:");
    boolean result = false;
    int patternLen = pattern.length;
    int textLen = text.length;
    int[] pi = prefixFunction(pattern);
    int q = 0;
    for (int i = 1; i <= textLen; i++){
      while (q >= 0 && pattern[q] != text[i-1]){
        q = pi[q];
      }
      q++;
      if (q == patternLen){
        System.out.println("Pattern FOUND -> starts on i = " + (i - patternLen + 1)
                           + ", ends on i = " + i + ".");
        q = pi[q];
        result = true;
      }
      else{
        if (i == textLen-1 && !result){
          System.out.println("Pattern NOT FOUND.");
        }
      }
    }
  }

  private static int[] prefixFunction(char[] pattern){
    int patternLen = pattern.length;
    int[] pi = new int[patternLen + 1];
    int k = -1;
    pi[0] = k;
    for (int q = 1; q < patternLen; q++){
      while (k >= 0 && pattern[k+1] != pattern[q]){
        k = pi[k];
      }
      k++;
      pi[q+1] = k;
    }
    return pi;
  }
}
