#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* method(char p1[], char p2[]) {   
    int sizeP1 = strlen(p1);
    int sizeP2 = strlen(p2);
    int i = 0, j = 0, index = 0;
    int size = sizeP1 + sizeP2 + 1; // +1 para o '\0'

    char* palavra = malloc(size * sizeof(char));
    if (palavra == NULL) {
        return NULL; // só devolve NULL se falhar
    }

    while (i < sizeP1 || j < sizeP2) {
        if (i < sizeP1) {
            palavra[index++] = p1[i++];
        }
        if (j < sizeP2) {
            palavra[index++] = p2[j++];
        }
    }

    palavra[index] = '\0';
    return palavra;
}
 
int main() {
    char p1[100], p2[100];

    while (scanf("%99s %99s", p1, p2) == 2) {
        char* palavra = method(p1, p2);

        if (palavra != NULL) {
            printf("%s\n", palavra);
            free(palavra);
        }
    }

    return 0;
}
