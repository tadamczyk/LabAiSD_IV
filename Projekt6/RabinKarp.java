/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*        RabinKarp.java         *
********************************/

public class RabinKarp{
  private static final int ALPHABET_SIZE = 128;
  private static final int PRIME_NUMBER = 27077;

  public static void algorithm(char[] pattern, char[] text){
    System.out.println("\nRABIN-KARP:");
    boolean result = false;
    int patternLen = pattern.length;
    int textLen = text.length;
    int max = textLen - patternLen;
    int h = 1;
    for (int i = 0; i < patternLen-1; i++){
      h = (h * ALPHABET_SIZE) % PRIME_NUMBER;
    }
    int patternHash = hash(pattern, 0, patternLen);
    int textHash = hash(text, 0, patternLen);
    for (int i = 0; i < max; i++){
      int j = 0;
      if (patternHash == textHash){
        while ((j < patternLen) && (pattern[j] == text[i+j])){
          j++;
        }
        if (j == patternLen){
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
      int t1 = (text[i]*h) % PRIME_NUMBER;
      if (textHash < t1){
        textHash += PRIME_NUMBER;
      }
      textHash = (ALPHABET_SIZE*(textHash - t1) + text[i+patternLen]) % PRIME_NUMBER;
    }
  }

  private static int hash(char[] source, int begin, int end){
    int h = 0;
    for (int i = begin; i < end; i++){
      h = (ALPHABET_SIZE*h + source[i]) % PRIME_NUMBER;
    }
    return h;
  }
}
