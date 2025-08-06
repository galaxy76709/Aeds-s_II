#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h> // para strcmp
#define   null NULL
/*
###### PALINDROMOS ######
Palindromos sao as palavaras ou frases que se lendo de tras para 
frente se coincidem, assim dando a mesma coisa 
Como: 
arrra
osso
ovo
rotor
A base do teto desaba
Socorram-me, subi no ônibus em Marrocos

Sendo assim a atividade terar que:
Crie um método iterativo que recebe uma string como parâmetro e retorna true se essa é um palíndromo.
Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NAO indicando se a linha é um palíndromo.
Destaca-se que uma linha de entrada pode ter caracteres não letras
A entrada termina com a leitura de FIM

Claro, esta forma esta desnesesariamente complexa e o lerdo do verde nn vai aceitar isso, mas ainda sim
foi legal


*/


//--------------------
/* 
strcut definida para criacao de 
arranjo de caracteres
*/
typedef
struct char_string 
{
    char   *data; // -> apontador destinado a indexar os valores para o arranjo
    int   lenght; // -> tamanho usado para a criacado da minha string
    int    index;

    
} char_string ;
typedef  char_string * ref_String;
//--------------------


//--------------------
// ideia de construstor em c ( simplificado )
ref_String new_charString ( int size )
{
// inicio de alocacao de espaco
    ref_String tmp_String = ( ref_String ) malloc ( sizeof ( char_string ) );

// condicao de existencia
    if ( tmp_String != null )
    { 
// por preacoacao os valores ja sao aterrados, para nao serem usados com lixo
        tmp_String -> lenght = 0; 
        tmp_String -> data   = null;  
        tmp_String -> index  = 0;
//condicao de tamanho minimo 
        if ( size > 0 )
        {
// recebendo valores apos a verificacao de existencia 
        tmp_String -> lenght = size; 
        tmp_String -> data   = ( char* ) malloc ( size * sizeof ( char ) ); // -> alocando de forma dinamica 
        tmp_String -> index  = 0;

// retornando a nova string 
            return ( tmp_String );

        } else 
        {printf ("Erro, valor minimo nao atingido...\n");}

    }
// condicao caso 
    else {printf ("Erro, Missing data...\n");}
}
//--------------------


//--------------------
void delete_String ( ref_String tmp_String )
{
// condicao de existencia 
    if ( tmp_String != null )
    {
// condicao de existencia 
        if ( tmp_String -> data  != null )
        {
// anulando e deletando os valores para liberacao de dados
          free  ( tmp_String -> data );
          tmp_String -> data   = null;
          tmp_String -> lenght = 0;
          tmp_String -> index  = 0;

        } else 
        {printf ("Erro, valor minimo nao atingido...\n");}

    }
// condicao caso 
    else {printf ("Erro, Missing data...\n");}
}
//--------------------


//--------------------
void declare_String ( ref_String tmp_String )
{
// iniciando valores aux 
    if ( tmp_String != null )
    {
        if ( tmp_String -> data != null )
        {
            fgets(tmp_String->data, tmp_String->lenght, stdin);

// eliminando o '\n' no final da string, caso exista
            int len = strlen(tmp_String->data);
                if (tmp_String->data[len - 1] == '\n')
                {
                    tmp_String->data[len - 1] = '\0';
                    tmp_String->lenght = len - 1;
                }
                else
                {
                    tmp_String->lenght = len;
                }
        }
    }
}
//--------------------


//--------------------
bool verify_Palindrome ( ref_String compare )
{
// valor de auxilio 
    bool isTrue     = true; // -> iniciando com "0" 

// condicoa de existencia 
    if ( compare != null && compare -> data != null && compare -> lenght > 1 )
    {
        while ( isTrue )
        {
        int left      =  0;
        int right     =  compare -> lenght - 1;
           
        while ( left < right && isTrue )
            {
                if  ( compare -> data [ left ] != compare -> data [ right ]) 
                {
                    isTrue = false; 
                } 

                left  = left  + 1;
                right = right - 1;

            }

            return ( isTrue );
        }

    } else 
    {printf ("Missing, data... \n"); }
    return false;
}
//--------------------


//--------------------
void print_string ( ref_String tmp_String )
{
// condicao de existencia 
    if (tmp_String != null && tmp_String->data != null)
    {
        for (int i = 0; i < tmp_String->lenght; i++)
        {
            printf("%c", tmp_String->data[i]);
        }
        printf("\n");
    }
}
//--------------------



//--------------------
void method_01 ( void )
{
// declaracao de variaveis
    bool verification = false; 
    char line[1000]; // -> espaco para leitura temporaria
    ref_String string = null;

    do
    {

        printf("Digite a palvra: \n");
// leitura da entrada com fgets
        fgets(line, sizeof(line), stdin);

// eliminando '\n' se estiver presente
        int len = strlen(line);
        if (line[len - 1] == '\n')
        {
            line[len - 1] = '\0';
            len--;
        }

// condicao para continuar enquanto nao encontrar "FIM"
        if ( strcmp(line, "FIM") != 0 )
        {
// alocar estrutura com base no tamanho real da linha
            string = new_charString(len + 1);

// copiar caracteres para a estrutura
            strcpy(string->data, line);
            string->lenght = len;

// realizar verificacao
            verification = verify_Palindrome(string);

// imprimir resultado
            if (verification)
            {
                printf("SIM\n");
            }
            else
            {
                printf("NAO\n");
            }

// liberar memoria
            delete_String(string);
        }

    } while (strcmp(line, "FIM") != 0);
}
//--------------------


//--------------------
int main()
{
    method_01();
    return 0;
}
//--------------------
