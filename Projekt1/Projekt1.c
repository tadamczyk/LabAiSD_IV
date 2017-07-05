/********************************
*        TOMASZ ADAMCZYK        *
*       Nr albumu: 243217       *
*      Informatyka II ROK       *
*  Algorytmy i struktury danych *
*      Projekt1.c - ZAD 2       *
********************************/

#include <stdio.h>
#include <stdlib.h>

typedef enum COLOR{
  RED,
  BLACK
} NodeColor;

typedef struct BST{
  struct BST *up, *left, *right;
  int key, color;
} RedBlackTree;

RedBlackTree *tree = NULL;

RedBlackTree *createNode(int key){
  RedBlackTree *temp = (RedBlackTree*) malloc(sizeof(RedBlackTree));
  temp->up = NULL;
  temp->left = NULL;
  temp->right = NULL;
  temp->key = key;
  temp->color = RED;
  return temp;
}

void leftRotate(RedBlackTree **root, RedBlackTree *temp){
  RedBlackTree *rightSon = temp->right;
  temp->right = rightSon->left;
  if (rightSon->left != NULL){
    rightSon->left->up = temp;
  }
  rightSon->up = temp->up;
  if (temp->up == NULL){
    *(root) = rightSon;
  }
  else if (temp == temp->up->left){
    temp->up->left = rightSon;
  }
  else{
    temp->up->right = rightSon;
  }
  rightSon->left = temp;
  temp->up = rightSon;
}

void rightRotate(RedBlackTree **root, RedBlackTree *temp){
  RedBlackTree *leftSon = temp->left;
  temp->left = leftSon->right;
  if (leftSon->right != NULL){
    leftSon->right->up = temp;
  }
  leftSon->up = temp->up;
  if (temp->up == NULL){
    *(root) = leftSon;
  }
  else if (temp == temp->up->left){
    temp->up->left = leftSon;
  }
  else{
    temp->up->right = leftSon;
  }
  leftSon->right = temp;
  temp->up = leftSon;
}

void insertTree(RedBlackTree **root, RedBlackTree *temp){
  if ((*root) == NULL){
    *root = temp;
    printf("+: Number %d is inserted into a tree.\n", temp->key);
    return;
  }
  else{
    if (temp->key < (*root)->key){
      insertTree(&(*root)->left, temp);
      (*root)->left->up = *root;
    }
    else if (temp->key >= (*root)->key){
      insertTree(&(*root)->right, temp);
      (*root)->right->up = *root;
    }
  }
}

void insertRedBlackTree(RedBlackTree **root, int key){
  RedBlackTree *temp = createNode(key);
  insertTree(&(*root), temp);
  RedBlackTree *parent = NULL;
  RedBlackTree *grandparent = NULL;
  while ((temp != *(root)) && (temp->up->color == RED)){
    parent = temp->up;
    grandparent = temp->up->up;
    if (parent == grandparent->left){
      RedBlackTree *uncle = grandparent->right;
      if (uncle != NULL && uncle->color == RED){
        uncle->color = BLACK;
        grandparent->color = RED;
        parent->color = BLACK;
        temp = grandparent;
      }
      else{
        if (temp == parent->right){
          leftRotate(root, parent);
          temp = parent;
          parent = temp->up;
        }
        parent->color = BLACK;
        grandparent->color = RED;
        rightRotate(root, grandparent);
      }
    }
    else{
      RedBlackTree *uncle = grandparent->left;
      if (uncle != NULL && uncle->color == RED){
        uncle->color = BLACK;
        grandparent->color = RED;
        parent->color = BLACK;
        temp = grandparent;
      }
      else{
        if (temp == parent->left){
          rightRotate(root, parent);
          temp = parent;
          parent = temp->up;
        }
        parent->color = BLACK;
        grandparent->color = RED;
        leftRotate(root, grandparent);
      }
    }
  }
  (*root)->color = BLACK;
}

RedBlackTree *treeMinimum(RedBlackTree *root){
  RedBlackTree *temp = root;
  while (temp->left != NULL){
    temp = temp->left;
  }
  return temp;
}

RedBlackTree *treeSuccessor(RedBlackTree *root){
  RedBlackTree *temp = root;
  if (temp->right != NULL){
    return treeMinimum(temp->right);
  }
  RedBlackTree *parent = root->up;
  while (parent != NULL && temp == parent->right){
    temp = parent;
    parent = parent->up;
  }
  return parent;
}

void deleteRedBlackTree_Fixup(RedBlackTree *root, RedBlackTree *temp){
  while (temp != root && temp->color == BLACK){
    if (temp == temp->up->left){
      RedBlackTree *brother = temp->up->right;
      if (brother->color == RED){
        brother->color = BLACK;
        temp->up->color = RED;
        leftRotate(&root, temp->up);
        brother = temp->up->right;
      }
      if (brother->left->color == BLACK && brother->right->color == BLACK){
        brother->color = RED;
        temp = temp->up;
      }
      else if (brother->right->color == BLACK){
        brother->left->color = BLACK;
        brother->color = RED;
        rightRotate(&root, brother);
        brother = temp->up->right;
      }
      brother->color = brother->up->color;
      temp->up->color = BLACK;
      brother->right->color = BLACK;
      leftRotate(&root, temp->up);
      temp = root;
    }
    else{
      RedBlackTree *brother = temp->up->left;
      if (brother->color == RED){
        brother->color = BLACK;
        temp->up->color = RED;
        rightRotate(&root, temp->up);
        brother = temp->up->left;
      }
      if (brother->right->color == BLACK && brother->left->color == BLACK){
        brother->color = RED;
        temp = temp->up;
      }
      else if (brother->left->color == BLACK){
        brother->right->color = BLACK;
        brother->color = RED;
        leftRotate(&root, brother);
        brother = temp->up->left;
      }
      brother->color = brother->up->color;
      temp->up->color = BLACK;
      brother->left->color = BLACK;
      rightRotate(&root, temp->up);
      temp = root;
    }
  }
  temp->color = BLACK;
}

RedBlackTree *deleteRedBlackTree(RedBlackTree *root, RedBlackTree *temp){
  int removedKey = temp->key;
  RedBlackTree *x, *y;
  if (temp->up == NULL){
    printf("-: Number %d is not REMOVED (Does not exist in a tree).\n", removedKey);
    return temp;
  }
  if (temp->left == NULL && temp->right == NULL){
    x = temp;
  }
  else x = treeSuccessor(temp);
  if (x->left != NULL){
    y = x->left;
  }
  else{
    y = x->right;
  }
  if (y != NULL){
    y->up = x->up;
  }
  if (x->up == NULL){
    root = y;
  }
  else if (x == x->up->left){
    x->up->left = y;
  }
  else{
    x->up->right = y;
  }
  if (x != temp){
    temp->key = x->key;
  }
  if (x->color == BLACK){
    deleteRedBlackTree_Fixup(root, y);
  }
  printf("-: Number %d is REMOVED.\n", removedKey);
  return x;
}

RedBlackTree *searchNode(RedBlackTree *root, int x){
  if (root){
    if (root->key == x){
      printf("?: Number %d IS in a tree.\n", x);
      return root;
    }
    else{
      if (x < root->key){
        return searchNode(root->left, x);
      }
      else{
        return searchNode(root->right, x);
      }
    }
  }
  else{
    printf("?: Number %d IS NOT in a tree.\n", x);
    RedBlackTree *temp = createNode(x);
    return temp;
  }
}

void printTree(RedBlackTree *root){
  if (root){
    printTree(root->left);
    printf("\t%d %s\n", root->key, root->color == RED ? "RED" : "BLACK");
    printTree(root->right);
  }
}

int main(){
  ///*
  insertRedBlackTree(&tree, 38);
  insertRedBlackTree(&tree, 31);
  insertRedBlackTree(&tree, 22);
  printTree(tree);
  searchNode(tree, 22);
  searchNode(tree, 8);
  insertRedBlackTree(&tree, 8);
  insertRedBlackTree(&tree, 20);
  insertRedBlackTree(&tree, 5);
  insertRedBlackTree(&tree, 10);
  printTree(tree);
  searchNode(tree, 9);
  insertRedBlackTree(&tree, 9);
  printTree(tree);
  searchNode(tree, 9);
  insertRedBlackTree(&tree, 21);
  deleteRedBlackTree(tree, searchNode(tree, 8));
  insertRedBlackTree(&tree, 27);
  insertRedBlackTree(&tree, 29);
  insertRedBlackTree(&tree, 25);
  deleteRedBlackTree(tree, searchNode(tree, 29));
  deleteRedBlackTree(tree, searchNode(tree, 40));
  insertRedBlackTree(&tree, 28);
  printTree(tree);
  //*/

  /*
  insertRedBlackTree(&tree, 18);
  insertRedBlackTree(&tree, 11);
  insertRedBlackTree(&tree, 6);
  insertRedBlackTree(&tree, 30);
  printTree(tree);
  searchNode(tree, 6);
  searchNode(tree, 19);
  insertRedBlackTree(&tree, 21);
  insertRedBlackTree(&tree, 19);
  insertRedBlackTree(&tree, 8);
  printTree(tree);
  searchNode(tree, 19);
  insertRedBlackTree(&tree, 22);
  insertRedBlackTree(&tree, 23);
  insertRedBlackTree(&tree, 5);
  insertRedBlackTree(&tree, 20);
  insertRedBlackTree(&tree, 26);
  insertRedBlackTree(&tree, 17);
  searchNode(tree, 26);
  searchNode(tree, 1);
  printTree(tree);
  */
  return 0;
}
