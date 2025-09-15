#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define null NULL

// Placa antiga: AAA-1234
bool is_old(char placa[])
{
    for (int i = 0; i < 3; i++) {
        if (!isupper(placa[i])) return false;
    }

    if (placa[3] != '-') return false;

    for (int i = 4; i < 8; i++) {
        if (!isdigit(placa[i])) return false;
    }

    return true;
}

// Placa Mercosul: ABC1D23
bool is_mercosul(char placa[])
{
    return  isupper(placa[0]) &&
            isupper(placa[1]) &&
            isupper(placa[2]) &&
            isdigit(placa[3]) &&
            isupper(placa[4]) &&
            isdigit(placa[5]) &&
            isdigit(placa[6]);
}

int classify_plates(char placa[])
{
    int length = strlen(placa);

    if (length == 8 && is_old(placa))
        return 1; // placa antiga

    if (length == 7 && is_mercosul(placa))
        return 2; // placa Mercosul

    return 0; // inválida
}

int main()
{
    char read_plates[30]; 

    if (fgets(read_plates, sizeof(read_plates), stdin)) {
        read_plates[strcspn(read_plates, "\n")] = '\0'; // remover \n
        int resultado = classify_plates(read_plates);
        printf("%d\n", resultado);
    }

    return 0;
}
