/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*      Projekt3.cpp - ZAD 1     *
********************************/

#include <cstdio>
#include <fstream>
#include <queue>
#include <string>
#define ENTER 10
#define SPACE 32
#define MAX 127
#define EMPTY ""

using namespace std;

string CODE_ARRAY[MAX];
int COUNTER_ARRAY[MAX];
int NORMAL_LENGTH, CHANGED_LENGTH;

struct TreeNode{
  char character;
  unsigned int counter;
  TreeNode *left, *right;
  TreeNode(char character, unsigned int counter){
    this->character = character;
    this->counter = counter;
    left = NULL;
    right = NULL;
  }
};

struct Compare{
  bool operator()(TreeNode *left, TreeNode *right){
    return (left->counter > right->counter);
  }
};

typedef priority_queue<TreeNode*, vector<TreeNode*>, Compare> priorityQueue;

void ReadFromFile(char *filename, int tab[]);
void HuffmanCodes(int tab[]);
void PrintCodes(TreeNode *root, int tab[], string code);
void Encrypt(char *source, char *destination);
void Decrypt(char *source, char *destination);

void ReadFromFile(char *filename, int tab[]){
  FILE *file;
  char character;
  file = fopen(filename, "r");
  if (file == NULL){
    printf("File is empty or does not exist.");
  }
  while (character != EOF){
    character = fgetc(file);
    tab[(int) character]++;
    NORMAL_LENGTH++;
  }
  fclose(file);
}

void HuffmanCodes(int tab[]){
  TreeNode *top, *left, *right;
  priorityQueue newTree;
  for (int i = 0; i < MAX; ++i){
    if (tab[i] != 0) {
      newTree.push(new TreeNode((char) i, tab[i]));
    }
  }
  while (newTree.size() != 1){
    left = newTree.top();
    newTree.pop();
    right = newTree.top();
    newTree.pop();
    top = new TreeNode('!', left->counter + right->counter);
    top->left = left;
    top->right = right;
    newTree.push(top);
  }
  PrintCodes(newTree.top(), tab, "");
}

void PrintCodes(TreeNode *root, int tab[], string code){
  if (!root){
    return;
  }
  else if (root->character != '!'){
    if ((int) root->character == ENTER){
      printf("\\n  - %6d - %20s\n", tab[(int) root->character], code.c_str());
    }
    else if ((int) root->character == SPACE){
      printf("\' \' - %6d - %20s\n", tab[(int) root->character], code.c_str());
    }
    else{
      printf("%c   - %6d - %20s\n", root->character, tab[(int) root->character], code.c_str());
    }
    CHANGED_LENGTH += (tab[(int) root->character] * code.length());
    CODE_ARRAY[(int) root->character] = code;
  }
  PrintCodes(root->left, tab, code + "0");
  PrintCodes(root->right, tab, code + "1");
}

int CountNormalLength(int tab[]){
  int i, j, counter = 0;
  for (i = 0; i < MAX; ++i){
    if (tab[i] != 0){
      counter++;
    }
  }
  i = 2;
  j = 0;
  while (counter > i){
    i *= 2;
    j++;
  }
  j++;
  return NORMAL_LENGTH *= j;
}

void PrintLengths(int normal, int changed){
  normal = CountNormalLength(COUNTER_ARRAY);
  printf("\nNormal length = %d\n", normal);
  printf("Changed length = %d\n", changed);
  printf("\nResult = %c%4.2f%c \n", (changed < normal ? '-' : '+'),
        (1 - ((double)changed / (double)normal)) * 100, '%');
}

void Encrypt(char *source, char *destination){
  FILE *in = fopen(source, "r");
  ofstream out;
  out.open(destination);
  char character;
  while (character != EOF){
    character = fgetc(in);
    out << CODE_ARRAY[(int) character].c_str() << " ";
  }
  fclose(in);
  out.close();
  printf("Encrypt file was done.\n");
}

void Decrypt(char *source, char *destination){
  FILE *in = fopen(source, "r");
  ofstream out;
  out.open(destination);
  string str;
  char character;
  int i;
  while (character != EOF){
    character = fgetc(in);
    if (character != ' '){
      str += character;
    }
    else{
      i = 0;
      while (str.compare(CODE_ARRAY[i]) != 0 && i < MAX){
        i++;
      }
      out << (char) i;
      str = EMPTY;
    }
  }
  fclose(in);
  out.close();
  printf("Decrypt file was done.\n");
}

int main(int argc, char *argv[]){
  ReadFromFile(argv[1], COUNTER_ARRAY);
  HuffmanCodes(COUNTER_ARRAY);
  PrintLengths(NORMAL_LENGTH, CHANGED_LENGTH);
  Encrypt(argv[1], (char*) ".code");
  Decrypt((char*) ".code", argv[2]);
}
