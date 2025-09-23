import java.util.Random;

/**
 * Algoritmo de ordenacao Heapsort em uma classe autocontida.
 * O código original de ordenação foi fornecido por Max do Val Machado.
 * A estrutura foi adaptada para ser executável em um único arquivo.
 */
public class HeapSortCompleto {

    // 1. Atributos que antes estavam na classe Geracao
    private int[] array;
    private int n;

    // 2. Construtores que inicializam e preenchem o array
    /**
     * Construtor.
     * @param tamanho do array de numeros inteiros.
     */
    public HeapSortCompleto(int tamanho) {
        this.n = tamanho;
        this.array = new int[tamanho];
        
        // Preenche o array com números aleatórios para teste
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(1000); // Números aleatórios de 0 a 999
        }
    }

    /**
     * Construtor padrão.
     */
    public HeapSortCompleto() {
        this(10); // Chama o outro construtor com tamanho padrão 10
    }

    // 3. Sua lógica de ordenação (sem alterações)
    /**
     * Algoritmo de ordenacao Heapsort.
     */
    public void sort() {
        // Alterar o vetor ignorando a posicao zero
        int[] tmp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            tmp[i + 1] = array[i];
        }
        array = tmp;

        // Contrucao do heap
        for (int tamHeap = 2; tamHeap <= n; tamHeap++) {
            construir(tamHeap);
        }

        // Ordenacao propriamente dita
        int tamHeap = n;
        while (tamHeap > 1) {
            swap(1, tamHeap--);
            reconstruir(tamHeap);
        }

        // Alterar o vetor para voltar a posicao zero
        tmp = array;
        array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = tmp[i + 1];
        }
    }

    public void construir(int tamHeap) {
        for (int i = tamHeap; i > 1 && array[i] > array[i / 2]; i /= 2) {
            swap(i, i / 2);
        }
    }

    public void reconstruir(int tamHeap) {
        int i = 1;
        while (i <= (tamHeap / 2)) {
            int filho = getMaiorFilho(i, tamHeap);
            if (array[i] < array[filho]) {
                swap(i, filho);
                i = filho;
            } else {
                i = tamHeap;
            }
        }
    }

    public int getMaiorFilho(int i, int tamHeap) {
        int filho;
        if (2 * i == tamHeap || array[2 * i] > array[2 * i + 1]) {
            filho = 2 * i;
        } else {
            filho = 2 * i + 1;
        }
        return filho;
    }

    // 4. Métodos de suporte que antes estavam na classe Geracao
    /**
     * Troca dois elementos de posição no array.
     */
    private void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Mostra os elementos do array no console.
     */
    public void mostrar() {
        System.out.print("[ ");
        // A lógica de mostrar precisa cuidar do array temporário com n+1 posições
        // ou do array final com n posições.
        int tamanhoParaMostrar = (array.length == n + 1) ? n + 1 : n;
        if (tamanhoParaMostrar == n) { // Array final, de 0 a n-1
            for (int i = 0; i < n; i++) {
                System.out.print(array[i] + " ");
            }
        } else { // Array temporário durante a ordenação, de 1 a n
             for (int i = 1; i <= n; i++) {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println("]");
    }
    
    // 5. Ponto de entrada para executar o programa
    public static void main(String[] args) {
        // Cria uma instância do Heapsort com 20 elementos
        HeapSortCompleto heapsort = new HeapSortCompleto(20);

        // Mostra o array original
        System.out.println("Array original (desordenado):");
        heapsort.mostrar();

        // Chama o método de ordenação
        heapsort.sort();

        // Mostra o array final, já ordenado
        System.out.println("\nArray final (ordenado com Heapsort):");
        heapsort.mostrar();
    }
}