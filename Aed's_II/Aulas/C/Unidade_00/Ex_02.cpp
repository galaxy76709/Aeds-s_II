/*
Faça um método que receba um array de inteiros e mostre na 
tela o maior e o menor elementos do array.

• Repita o exercício acima fazendo menos comparações com 
os elementos do array
*/

// dependencias 
 
#include <iostream> 
   using std::cin  ;  // para entrada 
   using std::cout;  // para saida 
   using std::endl;  // para mudar de linha 
   
   #include <iomanip> 
   using std::setw;   // para definir espacamento 
   
   #include <string> 
   using std::string;   // para cadeia de caracteres 
   
   #include <fstream> 
   using std::ofstream;   // para gravar arquivo 
   using std::ifstream ;   // para ler       arquivo 
   #define null nullptr	

template < typename T > 
class Array 
{
private : // area reservada 

    int    optional; 
    int   *data; // ponteiro para o arranjo
    int    length; 

    
public :

// construtor padrao do arranjo 
Array ( int n, int initial )
    {
// definindo valores iniciais
        optional = initial; 
        length  = 0;
        data    = null;

    // condicao de tamanho necessario 
        if ( n > 0 )
        {
// separando os novos valores para a criacao do arranjo 
            length = n;
            data = new int [ length ];
            for (int i = 0; i < length; i++) {
                data[i] = initial;
            }
        }

    } // end construtor


    
// metodo para definir um valor no arranjo
void set (int position, int value )
{
// condicao de existencia minima 
    if ( 0 <= position && position < length )
    {
         data [ position ] = value; 
    }
}//end set ( )

// metodo para obter um valor do arranjo
int get ( int position )
{
        int value = optional;
// condicao de exisstencia 
        if ( 0 <= position && position < length )
        {
// o valor de "value" recebera o valor da posicao de "position"
            value = data [ position ];
        }
        return ( value ); 
}// end get ( )



// metodo para procurar o menor e maior valor no arranjo (busca linear)
void find_Bigger_Smaller ( T &smaller, T& bigger  )
{

// condicao de existencia 
    if ( length > 0 || data != null )
    {
// iniciando variaceis aux
     smaller = data[0];
     bigger  = data[0];
// percorrendo o arranjo ate achar ou terminar
    for (int i = 0; i < length; i++)
    {
// condicao que preve caso o valor de x e menor que o valor do indicie 
        if ( data[ i ] < smaller  )
        {
            smaller = data[ i ];
        }
//condicao que ve se o valor do indice e maior que o maior valor
        if (data[ i ] > bigger )
        {
            bigger = data [ i ]; 
        }

    }

    } else {cout<<"Erro, missing data..."<<endl;}

}// end find_Bigger_Smaller

}; // end class Array


// funcao principal para testar
int main ()
{
    Array<int> arr(10, 0);
// considerando que o arranjo esteja ordenado 
    arr.set(0, 10);
    arr.set(1, 20);
    arr.set(2, 30);
    arr.set(3, 40);
    arr.set(4, 50);
    arr.set(5, 60);
    arr.set(6, 70);
    arr.set(7, 80);
    arr.set(8, 90);
    arr.set(9, 100);
    int x = 0;
    int y = 0;

    arr.find_Bigger_Smaller(x, y);
    cout << "Menor valor: " << x << endl;
    cout << "Maior valor: " << y << endl;

    return 0;
}
