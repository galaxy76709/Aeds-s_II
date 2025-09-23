public class Merge_Sort {

    private int[] array;

    public Merge_Sort(int[] array) {
        this.array = array;
    }

    /**
     * Algoritmo de ordenação Mergesort.
     * @param esq início do array a ser ordenado
     * @param dir fim do array a ser ordenado
     */
    private void MergeSort(int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            MergeSort(esq, meio);
            MergeSort(meio + 1, dir);
            intercalar(esq, meio, dir);
        }
    }

    /**
     * Algoritmo que intercala os elementos entre as posições esq e dir
     * @param esq início do array a ser ordenado
     * @param meio posição do meio do array a ser ordenado
     * @param dir fim do array a ser ordenado
     */
    public void intercalar(int esq, int meio, int dir) {
        int n1 = meio - esq + 1; // pega o lado direito 
        int n2 = dir - meio;    // pega lado esquerdo 
        int i  = 0 , j = 0 , k = 0 ;

        // Criar subarrays temporários
        int[] a1 = new int[n1 + 1];
        int[] a2 = new int[n2 + 1];

        // Copiar elementos para os subarrays
        for (i = 0; i < n1; i++) {
            a1[i] = array[esq + i];
        }
        for (j = 0; j < n2; j++) {
            a2[j] = array[meio + j + 1];
        }

        // Sentinela (valor muito grande no final)
        a1[n1] = a2[n2] = Integer.MAX_VALUE;

        // Intercalação propriamente dita
        i = j = 0;
        for (k = esq; k <= dir; k++) {
            if (a1[i] <= a2[j]) {
                array[k] = a1[i++];
            } else {
                array[k] = a2[j++];
            }
        }
    }

    /** Método auxiliar para ordenar o array completo */
    public void sort() {
        MergeSort(0, array.length - 1); 
    }

    /** Mostrar array */
    public void printArray() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /** Teste do Merge Sort */
    public static void main(String[] args) {
        int[] vetor = { 38, 27, 43, 3, 9, 82, 10 };

        Merge_Sort ms = new Merge_Sort(vetor);

        System.out.println("Array original:");
        ms.printArray();

        ms.sort();

        System.out.println("Array ordenado:");
        ms.printArray();
    }
}
