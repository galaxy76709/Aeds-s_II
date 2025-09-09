#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#define MAX 100


 int isEnd ( char *string)
 {
// removendo o \n do final da string
//verificao manual do arranjo (cada posicao dele) formato feio d mais
    return (string [0] == 'F' && string [1] == 'I' && string [2] == 'M'
        && string [3] == '\n' || string [3] == '\0' );

 }


// metodo para contar letras maiusculas em um arranjo de caractere 
int  count_upperCase ( char *string ) 
{

// iniciando variaveis locais para auxilio 
int count = 0;
int     x = 0; // -> index

// laco de repeticao de x ate 100 ou enquanto x for diferente de \n ou \0
    while ( x < MAX && string [x] != '\n' && string [x] != '\0' )
    {
        if  ( string[x] >= 'A' && string[x] <= 'Z' )
             { count = count + 1; }// -> caso esteja certo sera incrementado 
      x = x + 1; // atribuicao de fomra indireta
    }

    return (count);
}


int main () 
{
// inicinado variaveis
	char letter [MAX];
	int   resp = 0;

// laco de repeticao enquanto a resp for 0 rodara
	while (resp == 0)
    {
// ler linha
        printf("Digite a palavra: \n");
        fgets (letter, 100, stdin);

// passagem de valor, caso seja diferente de 0 sera lido novamente 
        resp = isEnd (letter);

        if (resp == 0)
        {
            int value = count_upperCase (letter);
            printf("No texo a [%d] letras maiusculas \n", value);
        }

    }
}