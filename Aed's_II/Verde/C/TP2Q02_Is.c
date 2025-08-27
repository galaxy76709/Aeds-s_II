#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

//----------------------------------------
int Isvowel(char txt[]) {
    int answer = 1; // verdadeiro
    int len = strlen(txt);

    for (int i = 0; i < len; i++) {
        char c = tolower(txt[i]);
        if (c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u') {
            answer = 0; // falso
        }
    }

    return (len > 0) ? answer : 0;
}
//----------------------------------------


//----------------------------------------
int IsReal(char txt[]) {
    int answer = 1;
    int len = strlen(txt);
    char temp[100];
    strcpy(temp, txt);

    // substitui ',' por '.'
    for (int i = 0; i < len; i++) {
        if (temp[i] == ',') temp[i] = '.';
    }

    // só é real se tiver ponto
    int hasDot = 0;
    for (int i = 0; i < len; i++) {
        if (temp[i] == '.') hasDot = 1;
    }
    if (!hasDot) return 0;

    // tenta converter
    char *endptr;
    strtod(temp, &endptr);
    if (*endptr != '\0') answer = 0;

    return (len > 0) ? answer : 0;
}
//----------------------------------------


//----------------------------------------
int Isconsonant(char txt[]) {
    int answer = 1;
    int len = strlen(txt);

    for (int i = 0; i < len; i++) {
        char c = tolower(txt[i]);
        if (!isalpha(c) || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            answer = 0;
        }
    }

    return (len > 0) ? answer : 0;
}
//----------------------------------------


//----------------------------------------
int IsNumber(char txt[]) {
    int answer = 1;
    int len = strlen(txt);

    for (int i = 0; i < len; i++) {
        if (!isdigit(txt[i])) {
            answer = 0;
        }
    }

    return (len > 0) ? answer : 0;
}
//----------------------------------------


int main() {
    char txt[100];

    fgets(txt, sizeof(txt), stdin);
    txt[strcspn(txt, "\n")] = 0; 

    while (strcmp(txt, "FIM") != 0) {
        int isV = Isvowel(txt);
        int isC = Isconsonant(txt);
        int isN = IsNumber(txt);
        int isR = IsReal(txt);

        printf("%s %s %s %s\n",
               isV ? "SIM " : "NAO ",
               isC ? "SIM " : "NAO ",
               isN ? "SIM " : "NAO ",
               isR ? "SIM " : "NAO ");

        fgets(txt, sizeof(txt), stdin);
        txt[strcspn(txt, "\n")] = 0; 
    }

    return 0;
}
