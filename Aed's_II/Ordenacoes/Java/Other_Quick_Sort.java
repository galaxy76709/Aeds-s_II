import java.util.Arrays;

public class Other_Quick_Sort 
{

    private int [ ] array; 

// funcoa para realizar o swap 
    private void swap ( int x, int y )
    {
        int temp    = array [ x ];
        array [ x ] = array [ y ];
        array [ y ] = temp; 
    }

/**
* O algoritmo de ordenação Quicksort.
* @param esq Início do subarray a ser ordenado.
* @param dir Fim do subarray a ser ordenado.
*/

    private void quick_Sort ( int esq, int dir )
    {
        int i = esq; int j = dir; 
// seleciona o pivo na metade do arranjo 
        int pivot = array [( esq + dir ) / 2 ]; 
// particiona o arranjo 
        while ( i <= j )
        {
// encotra o elemento que deveria estar a direita
            while ( array [ i ] < pivot )
                    { i++; }
// encontra o elemento que deveria estar a esquerda
            while ( array [ j ] > pivot )
                    { j--; }
// troca de elementos de lugar se os "ponteios" nao se cruzarem 
            if ( i <= j )
                {swap ( i, j ); i++; j--;}    
        }
// chamada recursiva para as duas particoes do quick sort 
        if (esq < j )   {quick_Sort( esq, j );}
        if (i  < dir)  {quick_Sort( i, dir );}
    }// end

    public void sort ( int [ ] array_sort )
    {
        if ( array_sort == null || array_sort.length <= 0 )
        {
            return ; // -> condicao que previne o resultado do arranjo de ser nulo 
        }

        this.array = array_sort;
        int  lengh = array_sort.length; 
        // chamada recursiva do quick sort
        quick_Sort ( 0 , lengh - 1 ); 
    }

    public static void main ( String [ ] args )
    {
// criando uma instancia da classe sort 
        Other_Quick_Sort sorter = new Other_Quick_Sort();
        int [ ]   myArray = {10, 7, 8, 9, 1, 5, 12, -3};

        System.out.println("Array original: " + Arrays.toString(myArray));    
    
// Chama o método para ordenar o array
        sorter.sort ( myArray );
        
        System.out.println("Array ordenado: " + Arrays.toString(myArray));
    
    }
}
