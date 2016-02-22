//
//  random.c
//  Random_Forest
//
//  Created by AKSHAY PARDESHI
//
//

#include "random.h"
#include <string.h>
#include <stdlib.h>
#include <math.h>
#define trainSize 1700
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
//Define buying attribute
#define buying_vhigh   3
#define buying_high    2
#define buying_med     1
#define buying_low     0
//Define safety attribute
#define safety_low  0
#define safety_med  1
#define safety_high 2

typedef struct node
{
    int*numdata;
    int size;
    int atrbIdx;
    int atrb_labels;
    int visitedAtrb[attributes];
    
}node;
char* data[trainSize][attributes+1];
int  numData[trainSize][attributes+1];
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


void read_data(car*,char [trainSize][10]);
//void stringToInt(char[trainSize][attributes],int[trainSize][attributes]);
void stringToInt();
//int getEntropy(char[trainSize][attributes],int label_idx,int);
float getEntropy();
int* divideNode(int idx,int atrb_labels,int size);
float findDivNode(int idx,int atrb_labels,int size);

int main()
{

    car* trainData;
    
    trainData=(car*)malloc(sizeof(car)*trainSize);
    char trainLabel[trainSize][10];
    int isDivided[attributes]={0};
    int atrbLabel[attributes]={BUYING_LABEL,MAINT_LABEL,DOORS_LABEL,PERSONS_LABEL,LUG_BOOT_LABEL,SAFETY_LABEL};

    
    
    printf("Random Forest Implementation:\n");
    read_data(trainData,trainLabel);

    for (int i=0; i<trainSize; i++)
    {
        //printf("print %d ",i);
        for (int j=0; j<=attributes; j++)
        {
            data[i][j]=(char*)malloc(sizeof(char)*10);
            //numData[i][j]=(int*)malloc(sizeof(int));
            numData[i][j]=0;
        }
        strcpy(data[i][0],trainData[i].buying);
        strcpy(data[i][1],trainData[i].maint);
        strcpy(data[i][2],trainData[i].doors);
        strcpy(data[i][3],trainData[i].persons);
        strcpy(data[i][4],trainData[i].lug_boot);
        strcpy(data[i][5],trainData[i].safety);
        strcpy(data[i][6],trainLabel[i]);


    }
    //stringToInt(data,numData);
    stringToInt();
    printf("Data Copied in to 2D array:\n");
    for (int i=0; i<trainSize; i++)
    {
        for (int j=0; j<=attributes; j++)
        {
            //  data[i][j]=(char*)malloc(sizeof(char)*10);
            printf("%s(%d) ",data[i][j],numData[i][j]);
            
        }
        printf("      %d\n",i);
    }
    printf("data Printed\n");

    float entropy;
    //entropy=getEntropy(numData,6,trainSize);
    entropy=getEntropy(LABEL,trainSize);
    printf("entropy at top level = %f",entropy);
    float min_entropy=1000;
    int divLabel,flag=0;//flag to check if all attributes are already divided
    for (int i=0;i<attributes; i++)
    {
        if(!(isDivided[i]))
        {
            float ent=findDivNode(i,atrbLabel[i],trainSize);//divide across given index into labels of given size
            if(min_entropy<ent)
            {   flag=1;
                min_entropy=ent;
                divLabel=i;
            }
        }
    }
    int *ptr;
    if(flag!=1)
        ptr=divideNode(divLabel,atrbLabel[divLabel],trainSize);
    return 0;
}
//return entropy sum across node to be divided
int* divideNode(int idx,int atrb_labels,int size)
{
    return NULL;
}
float findDivNode(int idx,int atrb_labels,int size)
{
    return 0;
}

//convert string value into integer to simplify calculations
//void stringToInt(char data[][attributes],int numData[][attributes])
void stringToInt()
{
    printf("Entered StringToint\n");
    for (int i=0; i<trainSize; i++)
    {
        //printf("%d ",numData[i][0]);
        if(!strcmp(data[i][BUYING],"vhigh"))
           numData[i][BUYING]=buying_vhigh;
        else if(!strcmp(data[i][BUYING],"high"))
            numData[i][BUYING]=buying_high;
        else if(!strcmp(data[i][BUYING],"med"))
            numData[i][BUYING]=buying_med;
        else
            numData[i][BUYING]=buying_low;
        // printf("%d ",numData[i][0]);
        
          //       printf("%d ",numData[i][5]);
        if(!strcmp(data[i][SAFETY],"high"))
            numData[i][SAFETY]=safety_high;
        else if(!strcmp(data[i][SAFETY],"med"))
            numData[i][SAFETY]=safety_med;
        else
            numData[i][SAFETY]=safety_low;
                       // printf("%d ",numData[i][5]);
        if(!strcmp(data[i][LABEL],"unacc"))
            numData[i][LABEL]=label_unacc;
        else if(!strcmp(data[i][LABEL],"acc"))
            numData[i][LABEL]=label_acc;
        else if(!strcmp(data[i][LABEL],"good"))
            numData[i][LABEL]=label_good;
        else
            numData[i][LABEL]=label_vgood;
    }
    //for(int j=0;j<trainSize;j++)
      //  printf("%d  ",numData[j][6]);
    
    printf("stringToInt done\n");
}
//int getEntropy(char numData[trainSize][attributes],int label_idx,int size)
float getEntropy(int label_idx,int size)
{
    int arr[4]={0}; //label size i.e number of possible values for label
    float entropy=0;
    printf("Entered get Entropy\n");
    for(int i=0;i<size;i++)
    {
        printf("%d ",numData[i][label_idx]);
    }
    printf("Done\n");
    for(int i=0;i<size;i++)
    {
        arr[numData[i][label_idx]]++;
    }
    for(int i=0;i<4;i++)
    {

        entropy+=arr[i]*1.0f/size*log(arr[i]*1.0f/size)/log(2);
        printf("%d  %f %f\n",arr[i],arr[i]*1.0f/size*log(arr[i]*1.0f/size)/log(2),entropy);
    }
    printf("getEntropy Done\n");
    return (-entropy);
    
}
void read_data(car*trainData,char trainLabel[trainSize][10])
{
        FILE *trainFD;
    char* token;
    char temp[100];
    int i=0;
    
    //Read data in store it into train_label and trainData variables
    trainFD=fopen("car/car.data.txt","r+");
    
    while(!feof(trainFD))
    {
        fscanf(trainFD,"%s",temp);
        //printf("%s\n",temp);
        int cnt=0;
        token = strtok(temp, ",");
        strcpy(trainData[i].buying,token);
        /*while (token) {
         token = strtok(NULL, ",");
         cnt++;
         printf("%d ",cnt);
         }*/
        token = strtok(NULL, ",");
        strcpy(trainData[i].maint,token);
        token = strtok(NULL, ",");
        strcpy(trainData[i].doors,token);
        token = strtok(NULL, ",");
        strcpy(trainData[i].persons,token);
        token = strtok(NULL, ",");
        strcpy(trainData[i].lug_boot,token);
        token = strtok(NULL, ",");
        strcpy(trainData[i].safety,token);
        token = strtok(NULL, ",");
        strcpy(trainLabel[i],token);
        strcpy(trainData[i].label,trainLabel[i]);
        
        i++;
        if (i==trainSize) {
            break;
        }
    }

}