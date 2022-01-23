main: main.o occupation.o checkContainer.o occupied_slots.o dynamicArray.o isRefrigerated.o checkEnergy.o
	gcc -Wall main.o occupation.o checkContainer.o occupied_slots.o dynamicArray.o isRefrigerated.o checkEnergy.o -o main

main.o: main.c occupation.h checkContainer.h
	gcc -Wall -c main.c

occupation.o: occupation.s occupation.h
	gcc -Wall -c occupation.s

checkContainer.o: checkContainer.s checkContainer.h
	gcc -Wall -c checkContainer.s

occupied_slots.o: occupied_slots.s occupied_slots.h
	gcc -Wall -c occupied_slots.s

dynamicArray.o: dynamicArray.c dynamicArray.h
	gcc -Wall -c dynamicArray.c

isRefrigerated.o: isRefrigerated.s isRefrigerated.h
	gcc -Wall -c isRefrigerated.s

checkEnergy.o: checkEnergy.c checkEnergy.h
	gcc -Wall -c checkEnergy.c

run: main
	./main

clean:
	rm *.o main
