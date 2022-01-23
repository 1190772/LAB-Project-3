#include "dynamicArray.h"

int enoughEnergy(int temp, Container* container_ptr, unsigned long availableEnergy, int length){

double delta_t;
double rt;
double area;
double energy=0;
int time_secs = 3600;
int i;
int enough=0;

for(i=0; i<length; i++){
if(container_ptr->refrigeration_temperature!=0){

   delta_t = temp - container_ptr->refrigeration_temperature;

   area = 2*(container_ptr->width * container_ptr->height) + 2*(container_ptr->length*container_ptr->height)
          + 2*(container_ptr->length*container_ptr->width);
   
   rt = container_ptr->wall->thickness/(container_ptr->wall->k*area) + (container_ptr->wall+1)->thickness/((container_ptr->wall+1)->k*area)
        + (container_ptr->wall+2)->thickness/((container_ptr->wall+2)->k*area); 

   energy += delta_t/rt * time_secs;

}
container_ptr++;
}
 if(energy<=availableEnergy){
 enough = 1;
}
 return enough;
}

