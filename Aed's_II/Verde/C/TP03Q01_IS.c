#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

bool isVogal(const char* s) {
    if (*s == '\0') {
        return true;
    }

    char c = tolower(*s);
    if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
        return isVogal(s + 1);
    }

    return false;
}

bool isConsoante(const char* s) {
    if (*s == '\0') {
        return true;
    }

    char c = tolower(*s);
    if (isalpha(c) && !(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) {
        return isConsoante(s + 1);
    }
    
    return false;
}

bool isInteiro(const char* s) {
    if (*s == '\0') {
        return true;
    }

    if (isdigit(*s)) {
        return isInteiro(s + 1);
    }

    return false;
}

bool isRealRecursivo(const char* s, int separadorCount) {
    if (*s == '\0') {
        return separadorCount == 1;
    }

    if (isdigit(*s)) {
        return isRealRecursivo(s + 1, separadorCount);
    } else if (*s == '.' || *s == ',') {
        if (separadorCount == 0) {
            return isRealRecursivo(s + 1, separadorCount + 1);
        } else {
            return false;
        }
    }
    
    return false;
}

bool isReal(const char* s) {
    return isRealRecursivo(s, 0);
}

int main() {
    char linha[1000];

    while (fgets(linha, sizeof(linha), stdin) != NULL) {
        
        linha[strcspn(linha, "\n")] = '\0';

        if (strcmp(linha, "FIM") == 0) {
            break;
        }

        if (strlen(linha) == 0) {
            printf("NAO NAO NAO NAO\n");
            continue;
        }

        printf("%s ", isVogal(linha) ? "SIM" : "NAO");
        printf("%s ", isConsoante(linha) ? "SIM" : "NAO");
        printf("%s ", isInteiro(linha) ? "SIM" : "NAO");
        printf("%s\n", isReal(linha) ? "SIM" : "NAO");
    }

    return 0;
}