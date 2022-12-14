#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#include "occupation.h"
#include "checkContainer.h"
#include "occupied_slots.h"
#include "dynamicArray.h"
#include "isRefrigerated.h"
#include "checkEnergy.h"
#include "enoughEnergy.h"

const int MAX_X = 50;
const int MAX_Y = 50;
const int MAX_Z = 16;
const int ID_CONTAINER_SIZE = 12;
int x,y,z;
char *position_data_ptr;
int pos[2] = {70300, 100705};
int* pos_ptr = pos;
const int N_POS = 2;

void show_occupation();
void show_checkContainer();
void show_occupied_slots();
void showCheckEnergy(Container* container_ptr);
void showEnoughEnergy(Container* container_ptr);

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

    show_occupation();
    show_checkContainer();
    show_occupied_slots();


   
	Container *container_ptr;
	const int ARRAY_CONTAINER_SIZE = occupation() >> 32;
	Wall *wall_ptr;
	const int ARRAY_WALL_SIZE = 6;

	container_ptr = (Container*)malloc(ARRAY_CONTAINER_SIZE*container_size());
	wall_ptr = (Wall*)malloc(ARRAY_WALL_SIZE*wall_size());
	
	fillDynamicArray(container_ptr, wall_ptr);

	demonstrationUS409(container_ptr, 3);
	
	showCheckEnergy(container_ptr);
	
	showEnoughEnergy(container_ptr);
	
	free(container_ptr);
	free(wall_ptr);
    return 0;
}

void showCheckEnergy(Container* container_ptr){
     printf("\nenergy = %f J\n",checkEnergy(20,2,5,10,container_ptr));
     printf("energy = %f J\n",checkEnergy(20,5,10,1,container_ptr));
     printf("energy = %f J\n",checkEnergy(20,7,3,0,container_ptr));
}

void showEnoughEnergy(Container* container_ptr){
	printf("\nWith low energy: %d \n", enoughEnergy(20, container_ptr, 10, 5));
	printf("With high energy: %d \n", enoughEnergy(20, container_ptr, 1000000000, 5));
}

void show_occupation() {
    	long res;
    	int occupied;
    	int free;

    	res = occupation();

    	free = res >> 32;
    	occupied = res;

    	printf("occupation()\nN??mero de espa??os livres: %d\nN??mero de espa??os ocupados: %d\n", free, occupied);
}

void show_checkContainer() {

    /*Put the coordinates in the x, y, and z global variables before calling the function*/
    x=10; y=10; z=12;
    printf("\ncheckContainer()\nIt should return 1: %hhd", checkContainer());
    z=11;
    printf("\nIt should return 0: %hhd\n", checkContainer());

}

void show_occupied_slots() {
	unsigned int res;
	
	res = occupied_slots();

	printf("\noccupied_slots()\nN??mero de espa??os ocupados: %u\n", res);
}


