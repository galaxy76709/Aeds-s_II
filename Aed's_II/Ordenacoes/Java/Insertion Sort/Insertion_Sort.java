/*
 * 
 * A ordenação por inserção é um algoritmo de ordenação simples que funciona inserindo iterativamente
 * cada elemento de uma lista não ordenada em sua posição correta em uma parte ordenada da lista. 
 * É como ordenar cartas de baralho em suas mãos. Você divide as cartas em dois grupos: as ordenadas e as não ordenadas. 
 * Em seguida, você escolhe uma carta do grupo não ordenado e a coloca no lugar correto no grupo ordenado.

    Começamos com o segundo elemento do array, pois o primeiro elemento é considerado classificado.
    Compare o segundo elemento com o primeiro elemento. Se o segundo elemento for menor, troque-os.
    Mova para o terceiro elemento, compare-o com os dois primeiros elementos e coloque-o na posição correta
    Repita até que toda a matriz esteja classificada.

 * 
 */


//import java.util.*;

public class Insertion_Sort 
{
    /*
     * metodo voltado para sortear o arranjo
     */
    void sort ( int arr [] )
    {
// iniciando aux com o valor do arranjo 
        int lenght = arr.length; // -> lenght e parte de OO, onde nao tem a necessiade de passar como uma funcao

// verificao minima de existenica
        if (lenght > 0 )
        {
// laco de repeticao para ir de x (1) para o tamanho do arranjo
            for (int x = 1; x < lenght; x = x + 1 )
            {
// variavel aux recebera o valor atual do indice ( key -> valor do indicie do arranjo  )
                int key = arr[ x ];

                int y = x - 1; // -> var aux para pegar o ultimo tamanho passado pelo x (indice - 1)

// laco de repeticao que ira relaizar a ordenacao de valores (laco anda a indice - 1)
                while ( y >= 0 && arr[y] > key )
                {
                    arr[ y + 1 ] = arr [ y ]; // atribuindo os valores caso a condicao do while seja afirmada
                    y = y - 1; // de forma indireta, o  while e decrementado
                }
// Insere a 'key' na posicao correta que foi aberta pelo laço 'while'.
                arr[y + 1] = key; 
            }
        }
        else { System.out.println("Erro, minimum size not reached...");}

    } // end sort


//------------------------------------
    
// um threads voltado para printar o arranjo 
    static void printArray(int arr[])
    {
        int n = arr.length;
        if (n > 0 )
        {
            for (int i = 0; i < n; ++i)
                System.out.print(arr[i] + " ");

                System.out.println();

        } else { System.out.println( "Erro, minimum size not reached...");}

    }
//------------------------------------

    public static void main(String[] args) {
        int arr [ ] = {12, 33, 65, 78, 13, 0, 87};

        Insertion_Sort ob = new Insertion_Sort(); // -> cria um objeto apartir da classe
        
        ob.sort(arr);

        printArray(arr);
        
    }
}
