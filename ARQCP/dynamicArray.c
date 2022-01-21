#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "dynamicArray.h"

void fillDynamicArray(Container* container_ptr, Wall *wall_ptr) {	
	
	wall(wall_ptr);

	FILE *f1 = fopen("characteristics.txt", "r");

	if (f1 != NULL) {
		unsigned short i = 0;
		while (fscanf(f1, "%s %hhd %hhd %hhd %u %hhd %lf %lf %lf", (container_ptr+i)->id_container, &(container_ptr+i)->x, &(container_ptr+i)->y, &(container_ptr+i)->z,
					&(container_ptr+i)->cargo, &(container_ptr+i)->refrigeration_temperature, &(container_ptr+i)->length, &(container_ptr+i)->width, &(container_ptr+i)->height)!=EOF) {
			switch ((container_ptr+i)->refrigeration_temperature) {
				case 7:
					(container_ptr+i)->wall = wall_ptr;
					break;
				case -5:
					(container_ptr+i)->wall = wall_ptr+3;
					break;
				default:
					(container_ptr+i)->wall = 0;
			}
			
			i++;
		}
		
	}else {
		printf("characteristics.txt file not found!");
		exit(-1);
	}
}

void wall(Wall *wall_ptr) {
	char material_arr[][21] = {"Corten Steel", "Expanded Polystyrene", "Bamboo", "Stainless Steel", "Polyurethane Foam", "Plywood"};
	double k_arr[] = {25, 0.046, 0.55, 16.2, 0.03, 0.13};
	double thickness_arr[] = {0.02, 0.06, 0.02, 0.025, 0.05, 0.025};
	for (int i = 0; i<6; i++) {
		strcpy((wall_ptr+i)->material, material_arr[i]);
		(wall_ptr+i)->k = *(k_arr+i);
		(wall_ptr+i)->thickness = *(thickness_arr+i);
	}

}

int container_size() {
	return sizeof(Container);
}

int wall_size() {
	return sizeof(Wall);
}

void demonstrationUS409(Container *container_ptr, int n) {
	printf("\nUS409");
	for (int i = 0; i<n; i++){
		printf("\n\tPosition %d:\n\t\tID Container:%s x:%hhd y:%hhd z:%hhd cargo:%u length:%f width:%f height:%f refrigeration temperature:%hhd", 
				i, (container_ptr+i)->id_container, (container_ptr+i)->x, (container_ptr+i)->y, (container_ptr+i)->z, (container_ptr+i)->cargo, (container_ptr+i)->length, (container_ptr+i)->width, (container_ptr+i)->height, (container_ptr+i)->refrigeration_temperature);

		Wall *wall_ptr = (container_ptr+i)->wall;
		
		if ((container_ptr+i)->refrigeration_temperature!=0)
			for (int j = 0; j<3; j++)
				printf("\n\t\t\tWall material:%s k:%f thickness:%f", (wall_ptr+j)->material, (wall_ptr+j)->k, (wall_ptr+j)->thickness);
		printf("\n");
	}
}
