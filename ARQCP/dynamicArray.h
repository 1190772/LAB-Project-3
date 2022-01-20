typedef struct {
	double k, thickness;
        char material[21];
} Wall;

typedef struct {
	Wall *wall;
	double length, width, height;
        unsigned int cargo;
        char x, y, z;
        char refrigeration_temperature;/*0 when null*/
        char id_container[12];
} Container;

void fillDynamicArray(Container *container_ptr, Wall *wall_ptr);
void wall(Wall *wall_ptr);
int container_size();
int wall_size();
void demonstrationUS409(Container *container_ptr, int n);
