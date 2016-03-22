//
//  random.c
//  Random_Forest
//
//  Created by AKSHAY PARDESHI
//
//

#include "random.h"
int trainSize=1612;
int testSize = 116;
void printNode(node*);
node** divideNode(node*,int);
int numOfLabels[attributes];
int testInput(node*t_node, int* in);
void testFunction(node*t_node,int data[trainSize][attributes+1]);
void printDataInNode(node* t_node);
void randomForest()
{
    char* data[trainSize][attributes+1];
    car* trainData;
    int numData[trainSize][attributes+1];
    trainData=(car*)malloc(sizeof(car)*trainSize);
    char trainLabel[trainSize][10];
    
    /*initialize number of labels for each attribute*/
    numOfLabels[0]=4;
    numOfLabels[1]=4;
    numOfLabels[2]=4;
    numOfLabels[3]=3;
    numOfLabels[4]=3;
    numOfLabels[5]=3;
     //////////////////////////////
    printf("Random Forest Implementation start:\n");
    read_data(trainData,trainLabel,0);
    printf("Data read successfully\n");
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
    stringToInt(data,numData);
  /* printf("Data Copied in to 2D array:\n");
    for (int i=0; i<trainSize; i++)
    {
        for (int j=0; j<=attributes; j++)
        {
            //  data[i][j]=(char*)malloc(sizeof(char)*10);
            printf("i=%d: %s(%d) ",i,data[i][j],numData[i][j]);
            
        }
        printf("      %d\n",i);
    }
    printf("data Printed\n");*/
    node*root=createDecisionTree(numData);
    testFunction(root,numData);
    trainSize=testSize;
    //--------------------testing--------------------------
    
   car* testCar=(car*)malloc(sizeof(car)*testSize);
    char testLabel[testSize][10];
    
    int testNumData[testSize][attributes+1];
    char* testData[trainSize][attributes+1];
    
    read_data(testCar,testLabel,1);
    
    for (int i=0; i<testSize; i++)
    {
    //printf("print %d ",i);
        for (int j=0; j<=attributes; j++)
        {
            testData[i][j]=(char*)malloc(sizeof(char)*10);
            //numData[i][j]=(int*)malloc(sizeof(int));
            testNumData[i][j]=0;
        }
        strcpy(testData[i][0],testCar[i].buying);
        strcpy(testData[i][1],testCar[i].maint);
        strcpy(testData[i][2],testCar[i].doors);
        strcpy(testData[i][3],testCar[i].persons);
        strcpy(testData[i][4],testCar[i].lug_boot);
        strcpy(testData[i][5],testCar[i].safety);
        strcpy(testData[i][6],testLabel[i]);
    }

    stringToInt(testData,testNumData);
    testFunction(root,testNumData);

}

//read data from text file and store it into structured format
void read_data(car*trainData,char trainLabel[trainSize][10],int test_flag)
{
    FILE *trainFD;
    char* token;
    char temp[100];
    int i=0;
    
    //Read data in store it into train_label and trainData variables
    if(test_flag==1)
        trainFD=fopen("test_car.txt","r+");
    else
        trainFD=fopen("train_car.txt","r+");
    if(trainFD==NULL)
        printf("Error in reading File\n");
    while(!feof(trainFD))
    {
        fscanf(trainFD,"%s",temp);
        //printf("%s\n",temp);
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
//convert string value into integer to simplify calculations
//void stringToInt(char data[][attributes],int numData[][attributes])
void stringToInt(char* data[trainSize][attributes+1],int numData[trainSize][attributes+1])
{
    //printf("Entered StringToint\n");
    for (int i=0; i<trainSize; i++)
    {
        //DEFINE BUYING PRICE
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
        //MAINT NEED
        //printf("%d ",numData[i][0]);
        //as maint and buying labels are same reuse(vhigh,high,med,low)
        if(!strcmp(data[i][MAINT],"vhigh"))
            numData[i][MAINT]=buying_vhigh;
        else if(!strcmp(data[i][MAINT],"high"))
            numData[i][MAINT]=buying_high;
        else if(!strcmp(data[i][MAINT],"med"))
            numData[i][MAINT]=buying_med;
        else
            numData[i][MAINT]=buying_low;
        // printf("%d ",numData[i][0]);
        
        //printf("%d ",numData[i][0]);
        //as maint and buying labels are same reuse(vhigh,high,med,low)
        //NUMBER OF DOORS
        if(!strcmp(data[i][DOORS],"5more"))
            numData[i][DOORS]=buying_vhigh;
        else if(!strcmp(data[i][DOORS],"4"))
            numData[i][DOORS]=buying_high;
        else if(!strcmp(data[i][DOORS],"3"))
            numData[i][DOORS]=buying_med;
        else
            numData[i][DOORS]=buying_low;
        // printf("%d ",numData[i][0]);
        //number of person
        if(!strcmp(data[i][PERSONS],"4"))
            numData[i][PERSONS]=person_four;
        else if(!strcmp(data[i][PERSONS],"2"))
            numData[i][PERSONS]=person_two;
        else
            numData[i][PERSONS]=person_more;
        // printf("%d ",numData[i][0]);
        //LUGGAGE BOOT
        if(!strcmp(data[i][LUG_BOOT],"big"))
            numData[i][LUG_BOOT]=lug_big;
        else if(!strcmp(data[i][LUG_BOOT],"med"))
            numData[i][LUG_BOOT]=lug_med;
        else
            numData[i][LUG_BOOT]=lug_small;
        
        //       printf("%d ",numData[i][5]);
        //SAFETY NEED
        if(!strcmp(data[i][SAFETY],"high"))
            numData[i][SAFETY]=safety_high;
        else if(!strcmp(data[i][SAFETY],"med"))
            numData[i][SAFETY]=safety_med;
        else
            numData[i][SAFETY]=safety_low;
        // printf("%d ",numData[i][5]);
        //CAR CONDITION
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
    
    //printf("stringToInt done\n");
}
void printNode(node* t_node)
{
    printf("\nPrintNode Entered:\n");
    printf("rows = %d columns = %d \n visited arr :",t_node->rows,t_node->columns);
    for (int i=0; i<attributes; i++)
    {
        printf("%d ", t_node->visitedAttrb[i]);
    }
    printf("\ndivAttr : %d divLbl: %d ansLabels: %d",t_node->divAttribute,t_node->divLabels,t_node->ansLabel);
    printf("\nPrintNode Exit:\n");
}












//create Decision Tree
node*createDecisionTree(int numData[][attributes+1])
{
    node*root = creatRootNode(numData);
    creatSubTree(root,1);
    
    return root;

}
//creat root node for decision tree
node*creatRootNode(int data[][attributes+1])
{
    node *root;
    
    printf("create root node\n");
    root = (node*)malloc(sizeof(node));
    root->columns = attributes+1;
    root->rows = trainSize;
    root->divAttribute=-1;
    root->ansLabel=0;
    root->divLabels=0;
    
    for (int i=0;i<attributes;i++)
        root->visitedAttrb[i]=0;
    root->numdata = (int**)malloc(sizeof(int*)*root->rows);
    for (int i=0;i<root->rows;i++)
        root->numdata[i]=(int*)malloc(sizeof(int)*root->columns);
    
    for(int i=0;i<root->rows;i++)
        for(int j=0;j<root->columns;j++)
            root->numdata[i][j]=data[i][j];
    printf("created root node\n");
    return root;
    
}

void creatSubTree(node* t_node,int level)
{
    
    printf("\ncreateSubTree: At level %d\n",level-1);
  //  printDataInNode(t_node);
    int isHomo=isHomogeneous(t_node);
    if(isHomo!=-1)
    {

        t_node->ansLabel=isHomo;
        t_node->divAttribute=-1;
        printf("it is homogeneous Node %d %d\n",t_node->rows,t_node->ansLabel);
        printNode(t_node);
        return;
    }
    if (t_node->rows<5)
    {
        t_node->divAttribute=-1;
          return;
    }
    node** child;
    int divLbl =  findDivNode(t_node);

    if (divLbl != -1)
    {
        t_node->divAttribute=divLbl;
        child = divideNode(t_node,divLbl);//divide across root node for decision tree
        //printf("Priniting Children at level %d\n",level-1);
        //printNode(t_node);
        for (int i=0;i< t_node->divLabels;i++ )
        {
            printf("\nchild number :%d",i);
            if(child[i]->rows!=0)
            {
                creatSubTree(child[i],level+1);
            }
            else
                child[i]=NULL;
        }
       // for(int i=0;i<t_node->divLabels;i++)
         //   printf("%d  ",child[i]->rows);
    }
    //else
       // printf("Successfully divided across this node\n");
    //printf("%d  ",t_node->rows);
}
int findDivNode(node* t_node)
{
    // printf("Entered findDivNode \n");
    int flag=0,divLabel=-1;
    float ent=0,entC;
    float gain,max_gain=-1000;
    node**child;
    ent = getEntropy(t_node);
    //printf("Entropy of parent: %f\n",ent);
    for (int i=0;i<attributes; i++)
    {

        if(!(t_node->visitedAttrb[i]))
        {
           
            entC=0;
            child = divideNode(t_node,i);
           
            for (int j=0;j < t_node->divLabels ; j++)
            {
           
                entC += child[j]->rows*getEntropy(child[j])*1.0f/(t_node->rows);
                //printf("Cumulative Entropy till Child %d is %f\n",j,entC);
                freeMem(child[j]);
            }
            t_node->visitedAttrb[i] = 0;
            gain = ent - entC;
            //printf("\nfor %d rows across atrb num: %d gain is %f",t_node->rows,i,gain);
            if(gain > max_gain)
            {   flag=1;
                max_gain=gain;
                divLabel=i;
            }
           
        }
        //  printf("Division Label number is %d\n",divLabel);
    }
    return divLabel;
}

node** divideNode(node* sNode ,int divAttrb)
{
    node** child;
    int numChild=numOfLabels[divAttrb];
    int* lblSampleCnt;//to keep count of data divided across each label types.

   // printf("Entered divideNode\n");
    lblSampleCnt=(int*)calloc(sizeof(int),numChild);//initialze all counts to zero
    sNode->visitedAttrb[divAttrb] = 1;
    sNode->divAttribute = divAttrb;
    sNode->divLabels    = numChild;
    child = (node**)malloc(sizeof(node*)*numChild);
    for(int i=0;i<numChild;i++)
    {
        child[i]=(node*)malloc(sizeof(node));
        child[i]->columns=sNode->columns;
        child[i]->numdata=(int**)malloc(sizeof(int*)*sNode->rows);
        child[i]->ansLabel=0;
        child[i]->divLabels=0;
        child[i]->divAttribute=-1;
        for(int j=0;j<attributes;j++)
            child[i]->visitedAttrb[j]=sNode->visitedAttrb[j];
    }
     //   printf("Allocated memory for children samples :%d\n",sNode->rows);
  //  for(int i=0;i<sNode->rows;i++)
    //{
//       printf("sNode %d %d",i,sNode->numdata[i][1]);
    //}
    //printf("Visited Successfully\n");
  
    for(int i=0;i<sNode->rows;i++)
    {
              // printf(" %dth data: %d %d %d %d %d %d\n",i,sNode->numdata[i][0],sNode->numdata[i][1],sNode->numdata[i][2],sNode->numdata[i][3],sNode->numdata[i][4],sNode->numdata[i][5]);
  
        int tempLbl = sNode->numdata[i][divAttrb]; //label of divattribute for divisiion
        //printf("\nchildlabel %d",tempLbl);
        child[tempLbl]->numdata[lblSampleCnt[tempLbl]]=(int*)malloc(sizeof(int)*sNode->columns);
        for (int j=0;j<sNode->columns;j++)
        {
           // printf("i=%d j=%d",i,j);
            child[tempLbl]->numdata[lblSampleCnt[tempLbl]][j]=sNode->numdata[i][j];
           // printf("%d ",child[tempLbl]->numdata[lblSampleCnt[tempLbl]][j]);
            
        }
        lblSampleCnt[tempLbl]++;
        //printf("sample count for child %d is %d samples:%d\n",tempLbl,lblSampleCnt[tempLbl],i);
    }
  
    for(int j=0;j<numChild;j++)
    {
        //child[j]->numdata = (int**)realloc(child[j]->numdata,sizeof(int*)*lblSampleCnt[j]);
        child[j]->rows=lblSampleCnt[j];
       // printf("samples in child %d are %d\n",j,child[j]->rows);
    }
    //printf("Devided across parent %d to get %d children\n",sNode->rows,sNode->divLabels);
    sNode->children=child;
    free(lblSampleCnt);
    return sNode->children;
}

//free dynamically allocated memory of node
void freeMem(node* t_node)
{
  //  for(int i=0;i<t_node->rows;i++)
    //{
      //  if(t_node->numdata[i]!=NULL)
        //    free(t_node->numdata[i]);
    //}
   // if(t_node->numdata!=NULL)
     //   free(t_node->numdata);
    
    //free(t_node->children);
   // free(t_node);
}
//return entropy sum across node to be divided
float getEntropy(node* t_node)
{
    int arr[4]={0}; //label size i.e number of possible values for label
    int label_idx = attributes;
    float entropy=0;
    int size =t_node->rows;
    //printf("Entered get Entropy\n");

    for(int i=0;i<size;i++)
    {
        arr[t_node->numdata[i][label_idx]]++;
        
    }
    for(int i=0;i<4;i++)
    {
//        if(arr[i]==0)
  //          break;
        entropy+=arr[i]*1.0f/(size+0.000001)*log((arr[i]+0.00001)*1.0f/(size+0.000001))/log(2);
        //printf("arr[%d]=%d  cal=%f ent=%f\n",i,arr[i],arr[i]*1.0f/size*log((arr[i]+0.00001)*1.0f/size)/log(2),entropy);
        
    }
    //printf("getEntropy Done\n");
    return (-entropy);
    
}
//check if all results in given are are of same type
int isHomogeneous(node* t_node)
{
    //printf("\nEntered isHomogeneous:\n");
    int lblCnt[totalLabels]={0};
    int flag=t_node->numdata[0][attributes];
    int val = t_node->numdata[0][attributes];
    lblCnt[val]++;
  //  printf("it is homogeneous Node %d %d\n",t_node->rows,t_node->numdata[0][attributes]);

    for(int i=1;i<t_node->rows;i++)
    {
    //    printf("data[%d] %d\n",i,t_node->numdata[i][attributes]);
        lblCnt[t_node->numdata[i][attributes]]++;
        if(t_node->numdata[i][attributes]!=val)
        {
      //      printf("%d\n",i);
            flag=-1;
            //break;
        }
        //printf("data[%d] %d\n",i,t_node->numdata[i][attributes]);
    }
  //  printf("done checking if Homogeneous\n");
    int max=lblCnt[0];
    int maxIdx=0;
    for (int i=1;i<totalLabels; i++)
    {
        if(lblCnt[i]>max)
        {
            max=lblCnt[i];
            maxIdx=i;
        }
    }
    t_node->ansLabel=maxIdx;
   // printf("Exiting isHomogeneous\n");
    return flag;
}


int testInput(node*t_node, int* in)
{
    if(t_node->divAttribute==-1)
        return t_node->ansLabel;
    if(t_node->children[in[t_node->divAttribute]]!=NULL)
        return(testInput(t_node->children[in[t_node->divAttribute]],in));
    else
        return t_node -> ansLabel;
    
}

void testFunction(node*t_node,int indata[trainSize][attributes+1])
{
    int cnt=0;
    for(int i=0;i<trainSize;i++)
    {
        if(testInput(t_node, indata[i])==indata[i][attributes])
           cnt++;
        else
            printf("i=%d ,expected = %d result = %d\n",i,indata[i][attributes],testInput(t_node, indata[i]));
    }
           float accu = cnt*100.0f/trainSize;
           printf("\nAccuracy is: %f\n",accu);
}
void printDataInNode(node* t_node)
{
    printf("PrintDataInNode Entered:\n");
    printf("rows = %d columns = %d \n i = 0:",t_node->rows,t_node->columns);
    for (int i=0; i<t_node->rows; i++)
    {
        for (int j=0; j<attributes+1; j++)
        {
            printf("%d ",t_node->numdata[i][j]);
        }
        printf("\ni = %d: ",i+1);
    }
        printf("PrintDataInNode Exited:\n");
}
