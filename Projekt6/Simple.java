/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*          Simple.java          *
********************************/

import java.util.*;

public class Simple{
  public static void algorithm(char[] pattern, char[] text){
    System.out.println("\nSIMPLE:");
    boolean result = false;
    int patternLen = pattern.length;
    int textLen = text.length;
    int max = textLen - patternLen;
    for (int i = 0; i <= max; i++){
      /*int j = 0;
      while ((j < patternLen) && (pattern[j] == text[i+j])){
        j++;
      }
      if (j == patternLen){
        System.out.println("Pattern FOUND -> starts on i = " + (i+1)
                           + ", ends on i = " + (i+patternLen) + ".");
        result = true;
      }*/
      char[] tmp = new char[patternLen];
      System.arraycopy(text, i, tmp, 0, patternLen);
      if (Arrays.equals(pattern, tmp)){
        System.out.println("Pattern FOUND -> starts on i = " + (i+1)
                           + ", ends on i = " + (i+patternLen) + ".");
        result = true;
      }
      else{
        if (i == max && !result){
          System.out.println("Pattern NOT FOUND.");
        }
      }
    }
  }
}
