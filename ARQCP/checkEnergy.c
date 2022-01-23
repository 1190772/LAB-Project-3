#include "isRefrigerated.h"
#include "dynamicArray.h"

double checkEnergy(int temp,char x,char y, char z, Container* container_ptr){

double delta_t;
double rt;
double area;
double energy;
int time_secs = 3600;

if(isRefrigerated(container_ptr,x,y,z,5)){

   delta_t = temp - container_ptr->refrigeration_temperature;

   area = 2*(container_ptr->width * container_ptr->height) + 2*(container_ptr->length*container_ptr->height)
          + 2*(container_ptr->length*container_ptr->width);
   
   rt = container_ptr->wall->thickness/(container_ptr->wall->k*area) + (container_ptr->wall+1)->thickness/((container_ptr->wall+1)->k*area)
        + (container_ptr->wall+2)->thickness/((container_ptr->wall+2)->k*area); 

   energy = delta_t/rt * time_secs;

} else {

   energy = 0;
}
 return energy;
}


