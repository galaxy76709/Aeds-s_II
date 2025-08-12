/*
Faça um método que receba um array de inteiros e um 
número inteiro x e retorne um valor booleano informando se o 
elemento x está contido no array

• Repita o exercício acima considerando que os elementos do 
array estão ordenados de forma crescente. Uma sugestão 
seria começar a pesquisa pelo meio do array
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

// metodo para procurar um valor x no arranjo (busca linear)
bool findX (int x )
{
    bool found = false;

    // percorrendo o arranjo ate achar ou terminar
    for (int i = 0; i < length && !found; i++)
    {
        if (data[i] == x)
        {
            found = true;
        }
    }

    return found;
}// end findX

}; // end class Array


// funcao principal para testar
int main ()
{
    Array<int> arr(5, 0);

    arr.set(0, 10);
    arr.set(1, 20);
    arr.set(2, 30);
    arr.set(3, 40);
    arr.set(4, 50);

    int x = 0;
    cout << "Digite um numero para procurar: ";
    cin >> x; getchar ( );

    if (arr.findX(x))
    {
        cout << "Valor encontrado no array." << endl;
    }
    else
    {
        cout << "Valor NAO encontrado no array." << endl;
    }

    return 0;
}
