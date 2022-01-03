main: main.o occupation.o checkContainer.o occupied_slots.o
	gcc -Wall main.o occupation.o checkContainer.o occupied_slots.o -o main

main.o: main.c occupation.h checkContainer.h
	gcc -Wall -c main.c

occupation.o: occupation.s occupation.h
	gcc -Wall -c occupation.s

checkContainer.o: checkContainer.s checkContainer.h
	gcc -Wall -c checkContainer.s

occupied_slots.o: occupied_slots.s occupied_slots.h
	gcc -Wall -c occupied_slots.s

run: main
	./main

clean:
	rm *.o main
