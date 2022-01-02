#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#include "occupation.h"
#include "checkContainer.h"

const int MAX_X = 50;
const int MAX_Y = 50;
const int MAX_Z = 50;
const int ID_CONTAINER_SIZE = 12;
int x,y,z;
char *position_data_ptr;

void show_occupation();
void show_checkContainer();

int main(){

    char name[ID_CONTAINER_SIZE];
    char position_data [MAX_X][MAX_Y][MAX_Z][ID_CONTAINER_SIZE];
    position_data_ptr = position_data[0][0][0];

    FILE *fp = fopen("positions.txt","r");

    for(int i = 0; i < MAX_X; i++){
       for(int j = 0; j < MAX_Y; j++){
           for(int t = 0; t < MAX_Z; t++){
               position_data[i][j][t][0] = 0;
               }
       }
    }


    if(fp == NULL ){
            printf("Impossivel abrir o ficheiro\n");
            exit(-1);
    } else {
            printf("File Opened\n");

        while(fscanf(fp,"%d %d %d %s",&x,&y,&z,name)!= EOF){

            strcpy(&position_data[x][y][z][0],name);

            if(x < 0 || y < 0 || z < 0){
                printf("ERROR : Positions can not be negative\n");
                exit(-1);
            }
        }
    }

    printf("\n\nPosition[07][03][00] = %s\n",&position_data[7][3][0][0]);
    printf("Position[10][07][05] = %s\n",&position_data[10][7][5][0]);
    printf("Position[10][10][12] = %s\n\n",&position_data[10][10][12][0]);



    return 0;
}

void show_occupation() {
    	long res;
    	int occupied;
    	int free;

    	res = occupation();

    	free = res >> 32;
    	occupied = res;

    	printf("occupation()\nNúmero de espaços livres: %d\nNúmero de espaços ocupados: %d\n", free, occupied);
}

void show_checkContainer() {

    /*Put the coordinates in the x, y, and z global variables before calling the function*/
    x=10; y=10; z=12;
    printf("\ncheckContainer()\nIt should return 1: %hhd", checkContainer());
    z=11;
    printf("\nIt should return 0: %hhd\n", checkContainer());

}

