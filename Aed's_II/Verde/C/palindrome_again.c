#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

bool ehPalindromo(const char* str) {
    int esquerda = 0;
    int direita = strlen(str) - 1;

    while (esquerda < direita) {
        // Pula caracteres não-alfabéticos da esquerda
        while (esquerda < direita && !isalpha(str[esquerda])) {
            esquerda++;
        }
        // Pula caracteres não-alfabéticos da direita
        while (esquerda < direita && !isalpha(str[direita])) {
            direita--;
        }

        if (esquerda < direita) {
            if (tolower(str[esquerda]) != tolower(str[direita])) {
                return false;
            }
            esquerda++;
            direita--;
        }
    }
    return true;
}

int main() {
    char linha[1000];
    
    while (fgets(linha, sizeof(linha), stdin) != NULL) {
        linha[strcspn(linha, "\n")] = '\0';
        
        if (strcmp(linha, "FIM") == 0) {
            break;
        }

        if (ehPalindromo(linha)) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }
    }

    return 0;
}