#include <stdio.h>
#include <string.h> 


int texto_para_inteiro(const char str[]) {
    int numero = 0;
    int i = 0;
    while (str[i] >= '0' && str[i] <= '9') {
        numero = numero * 10 + (str[i] - '0');
        i++;
    }
    return numero;
}


int sum_numbers(int number) {
    int sum = 0;
    while (number > 0) {
        sum += number % 10;
        number = number / 10;
    }
    return sum;
}


int main(void) {
    char txt[100];

    fgets(txt, sizeof(txt), stdin);
    txt[strcspn(txt, "\n")] = 0;

    while (strcmp(txt, "FIM") != 0) {

        int x = texto_para_inteiro(txt);
        int resp = sum_numbers(x);

        printf("%d\n", resp);  

        fgets(txt, sizeof(txt), stdin);
        txt[strcspn(txt, "\n")] = 0;
    }

    return 0;
}

