#include <stdio.h>
#include "square.h"
#include "triangle.h"

int main(void){
    printf("Hi, everybody!\n");
    printf("Five square is: %i\n", square(5));
    printf("A triangle ha %i sides\n", SIDES_OF_A_TRIANGLE);
    return 0;
}

