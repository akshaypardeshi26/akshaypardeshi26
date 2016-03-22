//
//  random.h
//  Random_Forest
//
//  Created by AKSHAY PARDESHI on 15/02/16.
//
//

#ifndef __Random_Forest__random__
#define __Random_Forest__random__

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#define attributes 6
//Structure for attributes of car
/*
 car labels
 unacc, acc, good, vgood
 unacceptable, acceptable, good, very good
 
 attributes
 buying:   vhigh, high, med, low.
 maint:    vhigh, high, med, low.
 doors:    2, 3, 4, 5more.
 persons:  2, 4, more.
 lug_boot: small, med, big.
 safety:   low, med, high.
 */
//Define attribute
#define BUYING 0
#define MAINT   1
#define DOORS   2
#define PERSONS 3
#define LUG_BOOT    4
#define SAFETY  5
#define LABEL 6

//Define attribute Labels
#define BUYING_LABEL    4
#define MAINT_LABEL     4
#define DOORS_LABEL     4
#define PERSONS_LABEL   3
#define LUG_BOOT_LABEL  3
#define SAFETY_LABEL    3


//Define labels
#define label_unacc   0
#define label_acc     1
#define label_good    2
#define label_vgood   3
#define totalLabels   4
//Define buying attribute
#define buying_vhigh   3
#define buying_high    2
#define buying_med     1
#define buying_low     0
//define person attributes
#define person_two  0
#define person_four 1
#define person_more 2
//Define luggage_boot
#define lug_small  0
#define lug_med  1
#define lug_big 2
//Define safety attribute
#define safety_low  0
#define safety_med  1
#define safety_high 2

typedef struct node
{
    int**numdata;
    int rows;
    int columns;
    int divAttribute;
    int visitedAttrb[attributes];
    struct node** children;
    int divLabels;
    int ansLabel;
    
}node;
typedef struct car
{
    char buying[10];
    char maint[10];
    char doors[10];
    char persons[10];
    char lug_boot[10];
    char safety[10];
    char label[10];
}car;



void read_data(car*,char [][10],int );
//void stringToInt(char[trainSize][attributes],int[trainSize][attributes]);
void stringToInt(char* data[][attributes+1],int numData[][attributes+1]);
//int getEntropy(char[trainSize][attributes],int label_idx,int);
float getEntropy();
//int* divideNode(int idx,int atrb_labels,int size);
int findDivNode(node*);
void randomForest();
node*creatRootNode(int numData[][attributes+1]);
node*createDecisionTree(int numData[][attributes+1]);
//free dynamically allocated memory
void freeMem(node* t_node);
//check if all results in given are are of same type
int isHomogeneous(node* t_node);
//devide each node into subtree
void creatSubTree(node* _node,int level);
#endif /* defined(__Random_Forest__random__) */
