package Ordenacoes.Java;
import java.util.Arrays;

public class Selection_Sort
{
    /**
     * Método para trocar dois elementos de posição num arranjo.
     * @param array O arranjo de inteiros.
     * @param a A posição do primeiro elemento.
     * @param b A posição do segundo elemento.
     */
    public static void swap(int[] array, int a, int b)
    {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    /**
     * Ordena um arranjo de inteiros usando o algoritmo Selection Sort.
     * @param array O arranjo a ser ordenado.
     */
    public static void Selectionsort(int[] array)
    {
        // Verifica se o arranjo é nulo para evitar erros
        if (array == null) {
            System.out.println("Erro, missing data");
            return; // Encerra o método se o arranjo for nulo
        }

// Verifica se o arranjo está vazio
        if (array.length <= 0) {
            System.out.println("[]");
            return; // Encerra o metodo se o arranjo estiver vazio -> nao e bom, mas vamo q vamo
        }

        int size = array.length;
        
// Loop principal que percorre o arranjo até o penúltimo elemento
        for (int i = 0; i < (size - 1); i++)
        {
// Assume que o menor elemento está na posição atual (i)
            int minor = i;
            
// Loop secundário para encontrar o menor elemento no restante do arranjo
            for (int j = (i + 1); j < size; j++)
            {
// Se encontrar um elemento menor que o atual 'minor'
                if (array[j] < array[minor])
                {
// Atualiza a posicao do menor elemento
                    minor = j;
                }
            }
            
// Troca o menor elemento encontrado com o elemento da posição 'i'
            swap(array, i, minor);
        }
    }

    public static void main(String[] args)
    {
        int[] array = { 5, 3, 7, 4, 8, 1 };

        System.out.println("Arranjo antes de ordenar: " + Arrays.toString(array));
        
        Selectionsort(array);
        
        System.out.println("Arranjo apos ordenar: " + Arrays.toString(array));
    }
}