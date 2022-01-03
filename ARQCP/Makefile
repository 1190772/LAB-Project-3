main: main.o occupation.o checkContainer.o
    gcc -Wall main.o occupation.o checkContainer.o -o main

main.o: main.c occupation.h checkContainer.h
    gcc -Wall -c main.c

occupation.o: occupation.s occupation.h
    gcc -Wall -c occupation.s

checkContainer.o: checkContainer.s checkContainer.h
    gcc -Wall -c checkContainer.s

run: main
    ./main

clean:
    rm *.o main