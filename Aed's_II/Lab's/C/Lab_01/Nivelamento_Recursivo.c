// biblioteca de apoio com definicao maxima de tamanho 
#include <stdio.h>
#define MAX 100

// funcao de verificao caso a palavra seja "FIM"
int isEnd ( char *string )
{
// iniciando verificacao caso a resposta seja FIM
    return ( string [ 0 ] == 'F' && 
             string [ 1 ] == 'I' &&
             string [ 2 ] == 'M' &&
             string [ 3 ] == '\n' || string [ 3 ] == '\0' );
}

int count_upperCase_rec ( char *string, int x)
{
// passo base, caso a string atinja um espaco em branco, o fim do arranjo e ou o tamanho maximo, retorna 0, assim , o acaba com a funcao 
    if (  x >= MAX || string [ x ] == '\n' || string [ x ] == '\0' ) // -> erro logico
    // tinha colocado && ao invels de ||, entrando assim em loop infinito
    {
        return 0;
    }

// variavel auxiliar para soma da resposta
    int sum = 0;

// condincao para verificar caso a palavra contenha letras maiusculas
    if (string [ x ] >= 'A' && string [ x ]<= 'Z')
        {sum = 1;}

    return sum + count_upperCase_rec ( string, x + 1 ); // -> fazendo assim, le todos o valor um por um 

}


void executar( void ) {
// variavel local com tamanho maximo 
    char letter[MAX];

// lendo a string e passando para a variavel local 
    printf("Digite a palavra:\n");
    fgets(letter, MAX, stdin);

    if ( !isEnd(letter)) {
        int value = count_upperCase_rec(letter, 0);
        printf("No texto ha [%d] letras maiusculas\n", value);
        executar(); // chamada recursiva

    } else {  printf ("O texto foi finalizado");}


}

int main ( void ) 
{
    executar ( );
    return 0;
}
