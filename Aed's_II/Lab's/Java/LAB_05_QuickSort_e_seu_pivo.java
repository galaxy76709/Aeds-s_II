import java.util.Arrays;
import java.util.Random;

public class LAB_05_QuickSort_e_seu_pivo {

    private static final Random rand = new Random();

    // --- Função de troca ---
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // --- Estratégia 1: Primeiro elemento ---
    public static void quickSortFirstPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionFirstPivot(array, left, right);
            quickSortFirstPivot(array, left, pivotIndex - 1);
            quickSortFirstPivot(array, pivotIndex + 1, right);
        }
    }

    private static int partitionFirstPivot(int[] array, int left, int right) {
        int pivot = array[left];
        int i = left + 1;
        int j = right;
        while (i <= j) {
            while (i <= j && array[i] <= pivot) i++;
            while (i <= j && array[j] > pivot) j--;
            if (i < j) swap(array, i, j);
        }
        swap(array, left, j);
        return j;
    }

    // --- Estrategia 2: Ultimo elemento ---
    public static void quickSortLastPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionLastPivot(array, left, right);
            quickSortLastPivot(array, left, pivotIndex - 1);
            quickSortLastPivot(array, pivotIndex + 1, right);
        }
    }

    private static int partitionLastPivot(int[] array, int left, int right) {
        int pivot = array[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, right);
        return i + 1;
    }

    // --- Estrategia 3: Pivo aleatorio ---
    public static void quickSortRandomPivot(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionRandomPivot(array, left, right);
            quickSortRandomPivot(array, left, pivotIndex - 1);
            quickSortRandomPivot(array, pivotIndex + 1, right);
        }
    }

    private static int partitionRandomPivot(int[] array, int left, int right) {
        int randomIndex = left + rand.nextInt(right - left + 1);
        swap(array, randomIndex, right);
        return partitionLastPivot(array, left, right); // reaproveita a lógica do último pivo
    }

    // --- Estrategia 4: Mediana de tres ---
    public static void quickSortMedianOfThree(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partitionMedianOfThree(array, left, right);
            quickSortMedianOfThree(array, left, pivotIndex - 1);
            quickSortMedianOfThree(array, pivotIndex + 1, right);
        }
    }

    private static int partitionMedianOfThree(int[] array, int left, int right) {
        int mid = (left + right) / 2;
        // Ordena os 3 valores (left, mid, right)
        if (array[left] > array[mid]) swap(array, left, mid);
        if (array[left] > array[right]) swap(array, left, right);
        if (array[mid] > array[right]) swap(array, mid, right);
        // Usa o mid como pivo
        swap(array, mid, right);
        return partitionLastPivot(array, left, right);
    }

    // --- Funcao auxiliar para medir desempenho ---
    private static void testStrategy(String name, int[] original, java.util.function.BiConsumer<int[], int[]> sorter) {
        int[] array = Arrays.copyOf(original, original.length);
        long start = System.nanoTime();
        sorter.accept(array, new int[]{0, array.length - 1});
        long end = System.nanoTime();
        System.out.println(name + " -> Tempo: " + (end - start) / 1_000_000.0 + " ms | Ordenado: " + Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] myArray = {10, 7, 8, 9, 1, 5, 12, -3};

        System.out.println("Array original: " + Arrays.toString(myArray));

        // Testando cada estrategia
        testStrategy("Primeiro Pivo", myArray, (arr, bounds) -> quickSortFirstPivot(arr, bounds[0], bounds[1]));
        testStrategy("Último Pivo", myArray, (arr, bounds) -> quickSortLastPivot(arr, bounds[0], bounds[1]));
        testStrategy("Pivo Aleatório", myArray, (arr, bounds) -> quickSortRandomPivot(arr, bounds[0], bounds[1]));
        testStrategy("Mediana de Três", myArray, (arr, bounds) -> quickSortMedianOfThree(arr, bounds[0], bounds[1]));
    }
}
