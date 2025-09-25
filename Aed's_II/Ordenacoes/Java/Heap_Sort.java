import java.util.Arrays;

public class Heap_Sort {
    private static int[] array;

    /**
     * Troca dois elementos de posição no array.
     */
    public static void swap(int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     * Constrói o heap máximo a partir de um array não ordenado.
     */
    public static void buildMaxHeap(int n) {
        // Começa do último nó não-folha e vai até a raiz
        for (int i = n / 2; i >= 1; i--) {
            heapify(i, n);
        }
    }

    /**
     * Mantém a propriedade de heap máximo (sift-down).
     */
    public static void heapify(int i, int tamHeap) {
        int largest = i;
        int left    = 2 * i;
        int right   = 2 * i + 1;

        // Verifica se o filho esquerdo existe e é maior que o pai
        if (left <= tamHeap && array[left] > array[largest]) {
            largest = left;
        }

        // Verifica se o filho direito existe e é maior que o maior atual
        if (right <= tamHeap && array[right] > array[largest]) {
            largest = right;
        }

        // Se o maior não é o pai, troca e continua heapificando
        if (largest != i) {
            swap(i, largest);
            heapify(largest, tamHeap);
        }
    }

    /**
     * Método principal que orquestra a ordenação.
     */
    public static void sort(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            return;
        }

        int n = inputArray.length;
        
        // Cria array baseado em 1 (índice 1 a n)
        array = new int[n + 1];
        for (int i = 0; i < n; i++) 
           {array[i + 1] = inputArray[i];}

        // Construção do heap máximo
        buildMaxHeap(n);

        // Ordenação: extrai elementos um por um
        int tamHeap = n;
        while (tamHeap > 1) {
            // Move a raiz (maior elemento) para o final
            swap(1, tamHeap);
            // Reduz o tamanho do heap
            tamHeap--;
            // Restaura a propriedade de heap máximo
            heapify(1, tamHeap);
        }

        // Copia o resultado de volta para o array original
        for (int i = 0; i < n; i++) {
            inputArray[i] = array[i + 1];
        }
    }

    /**
     * Método principal para testar o algoritmo.
     */
    public static void main(String[] args) {
        // Teste 1: Array desordenado
        int[] vetor1 = {12, 11, 13, 5, 6, 7, 9, 45, 2};
        System.out.println("Array original: " + Arrays.toString(vetor1));
        Heap_Sort.sort(vetor1);
        System.out.println("Array ordenado: " + Arrays.toString(vetor1));
        
        // Teste 2: Array já ordenado
        int[] vetor2 = {1, 2, 3, 4, 5};
        System.out.println("\nArray original: " + Arrays.toString(vetor2));
        Heap_Sort.sort(vetor2);
        System.out.println("Array ordenado: "   + Arrays.toString(vetor2));
        
        // Teste 3: Array inversamente ordenado
        int[] vetor3 = {5, 4, 3, 2, 1};
        System.out.println("\nArray original: " + Arrays.toString(vetor3));
        Heap_Sort.sort(vetor3);
        System.out.println("Array ordenado: "   + Arrays.toString(vetor3));
        
        // Teste 4: Array com elementos repetidos
        int[] vetor4 = {3, 7, 3, 1, 7, 2, 1};
        System.out.println("\nArray original: " + Arrays.toString(vetor4));
        Heap_Sort.sort(vetor4);
        System.out.println("Array ordenado: "   + Arrays.toString(vetor4));
    }
}