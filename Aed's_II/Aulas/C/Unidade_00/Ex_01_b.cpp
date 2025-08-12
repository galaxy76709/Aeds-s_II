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
    T     *data; // ponteiro para o arranjo
    int    length; 

public :

// construtor padrao do arranjo 
Array ( int n, T initial )
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
        data = new T [ length ];
        for (int i = 0; i < length; i++) {
            data[i] = initial;
        }
    }
} // end construtor

// destrutor para liberar memoria
~Array() {
    if (data != null) {
        delete[] data;
    }
}

// metodo para definir um valor no arranjo
void set (int position, T value )
{
    // condicao de existencia minima 
    if ( 0 <= position && position < length )
    {
         data [ position ] = value; 
    }
}//end set ( )

// metodo para obter um valor do arranjo
T get ( int position )
{
    T value = optional;
// condicao de existencia 
    if ( 0 <= position && position < length )
    {
// o valor de "value" recebera o valor da posicao de "position"
        value = data [ position ];
    }
    return ( value ); 
}// end get ( )



// metodo para procurar um valor x no arranjo (pesquisa binaria)
bool binary_Search ( int low, int high, T target  )
{ 
    if ( data != null && low < high )
    {
// laco de repeticao 
    while ( low <= high ) 
    {
// pegando o tamanho do meio do arranjo 
        int middle = low + ( high - low ) / 2; 

// caso o valor esteja na posicao do meio ( middle ) retorna verdadeiro
        if ( target == data [ middle ] )
            {
                return true;
            }
        
// caso o valor esteja na metade "maior" do arranjo, o arranjo desconsidera a outra metade 
        if ( target > data [ middle ] )
            {
                low = middle + 1;
            }
// condicao que verifica a proposta contraria  target < data [ middle ]
        else 
            {
                high = middle - 1;
            }
        }
// se nao encontrar
    return false;

// condicao caso o apontador esteja aterrado ou menor valor, seja maior que o maior valor ( erro logico )
    } else { cout<<"Erro, missing data..."<<endl; return (false); }
    

} // end binary_Search
 
// metodo para obter o tamanho
int size() {
    return length;
}

}; // end class Array


// funcao principal para testar
int main ()
{
    Array<int> arr(10, 0);

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

    int target = 0;
    cout << "Digite um numero para procurar: ";
    cin >> target; getchar ( );

// chamando pesquisa binaria
    if (arr.binary_Search( 0, arr.size()-1,  target ) )
    {
        cout << "Valor encontrado no array." << endl;
    }
    else
    {
        cout << "Valor NAO encontrado no array." << endl;
    }

    return 0;
}
