import java.util.Arrays;   
/*  
 * A ideia do Quick Sort é usar o conceito de dividir e conquistar.  
 * Faremos esse Quick Sort de forma recursiva.  
 */  
public class Quick_Sort  
{  
    private int partition(int[] array, int low, int high)  
    {   
        // Escolhendo o último elemento como pivô  
        int pivot = array[high];   

        // i será o índice que marca a posição dos menores elementos que o pivô  
        int i = (low - 1);    

        // Laço para percorrer a sublista do arranjo  
        for (int j = low; j < high; j++)   
        {   
            // Caso o elemento seja menor ou igual ao pivô  
            if (array[j] <= pivot)   
            {   
                i++;  
                // Realizando a troca (swap)  
                int temp  = array[i];  
                array[i]  = array[j];  
                array[j]  = temp;  
            }   
        }   

        // Coloca o pivô na posição correta (logo após os menores elementos)  
        int temp     = array[i + 1];  
        array[i + 1] = array[high];  
        array[high]  = temp;   

        // Retorna a posição do pivô  
        return i + 1;  
    }   

    public void sort(int[] array, int low, int high)   
    {   
        // A recursão terá seu passo base quando o menor índice for maior ou igual ao maior  
        if (low < high)   
        {   
            // Função destinada a particionar o arranjo em relação ao pivô  
            int pivot = partition(array, low, high);   

            // Chamada da função para ordenar o lado esquerdo do pivô  
            sort(array, low, pivot - 1);  

            // Chamada da função para ordenar o lado direito do pivô  
            sort(array, pivot + 1, high);  
        }   
    }   

    public static void main(String[] args)   
    {   
        int[] minhaLista = {10, 7, 8, 9, 1, 5, 3, 6};  

        System.out.println("Arranjo original: " + Arrays.toString(minhaLista));  

        Quick_Sort sorter = new Quick_Sort();  
        sorter.sort(minhaLista, 0, minhaLista.length - 1);  

        System.out.println("Arranjo ordenado: " + Arrays.toString(minhaLista));  
    }   
}  

/*              
######teste_de_mesa######
 Primeira Chamada: sort(array, 0, 7)

Partição 1: low=0, high=7, pivot=6
text

Array: [10, 7, 8, 9, 1, 5, 3, 6]
Pivô: 6 (último elemento)

j=0: 10 > 6 → nada
j=1: 7 > 6 → nada  
j=2: 8 > 6 → nada
j=3: 9 > 6 → nada
j=4: 1 ≤ 6 → i=0, troca array[0] e array[4]
Array: [1, 7, 8, 9, 10, 5, 3, 6]

j=5: 5 ≤ 6 → i=1, troca array[1] e array[5]
Array: [1, 5, 8, 9, 10, 7, 3, 6]

j=6: 3 ≤ 6 → i=2, troca array[2] e array[6]
Array: [1, 5, 3, 9, 10, 7, 8, 6]

Coloca pivô na posição i+1=3
Troca array[3] com array[7]
Array: [1, 5, 3, 6, 10, 7, 8, 9]

Retorna pivotIndex = 3

Recursão Esquerda: sort(array, 0, 2)

Partição 2: low=0, high=2, pivot=3
text

Array: [1, 5, 3, 6, 10, 7, 8, 9]
Pivô: 3

j=0: 1 ≤ 3 → i=0, troca array[0] com array[0] (mesma posição)
Array: [1, 5, 3, 6, 10, 7, 8, 9]

j=1: 5 > 3 → nada
j=2: 3 ≤ 3 → i=1, troca array[1] com array[2]
Array: [1, 3, 5, 6, 10, 7, 8, 9]

Coloca pivô na posição i+1=2
Troca array[2] com array[2] (mesma posição)
Retorna pivotIndex = 2

Recursão Esquerda da Esquerda: sort(array, 0, 1)
text

Array: [1, 3, 5, 6, 10, 7, 8, 9]
Pivô: 3 (último elemento da sublista)

j=0: 1 ≤ 3 → i=0, troca array[0] com array[0]
Array: [1, 3, 5, 6, 10, 7, 8, 9]

Coloca pivô na posição i+1=1
Troca array[1] com array[1] (mesma posição)
Retorna pivotIndex = 1

Recursão Direita da Esquerda: sort(array, 3, 2)
text

low=3 > high=2 → condição falha, retorna

Recursão Direita: sort(array, 4, 7)

Partição 3: low=4, high=7, pivot=9
text

Array: [1, 3, 5, 6, 10, 7, 8, 9]
Pivô: 9

j=4: 10 > 9 → nada
j=5: 7 ≤ 9 → i=4, troca array[4] com array[5]
Array: [1, 3, 5, 6, 7, 10, 8, 9]

j=6: 8 ≤ 9 → i=5, troca array[5] com array[6]
Array: [1, 3, 5, 6, 7, 8, 10, 9]

Coloca pivô na posição i+1=6
Troca array[6] com array[7]
Array: [1, 3, 5, 6, 7, 8, 9, 10]

Retorna pivotIndex = 6

Recursão Esquerda da Direita: sort(array, 4, 5)
text

Array: [1, 3, 5, 6, 7, 8, 9, 10]
Pivô: 8

j=4: 7 ≤ 8 → i=4, troca array[4] com array[4]
Array: [1, 3, 5, 6, 7, 8, 9, 10]

j=5: 8 ≤ 8 → i=5, troca array[5] com array[5]
Array: [1, 3, 5, 6, 7, 8, 9, 10]

Coloca pivô na posição i+1=6
Troca array[6] com array[5] (mesma posição)
Retorna pivotIndex = 5

Recursão Direita da Direita: sort(array, 7, 7)
text

low=7 == high=7 → condição falha, retorna

Array Final Ordenado: [1, 3, 5, 6, 7, 8, 9, 10]

Complexidade:

    Melhor caso: O(n log n) - pivô sempre no meio

    Pior caso: O(n²) - pivô sempre menor ou maior elemento

 */