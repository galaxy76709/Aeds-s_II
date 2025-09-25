import java.util.Arrays;
public class LAB_05_QuickSort_e_seu_pivo 
{
    private int[] array; 

// funcao para realizar o swap 
    private void swap(int x, int y) {
        int temp    = array[x];
        array[x]    = array[y];
        array[y]    = temp; 
    }

    /**
    * Quicksort com pivo no meio.
    */
    private void quick_Sort_Middle(int esq, int dir) {
        int i = esq; 
        int j = dir; 
        int pivot = array[(esq + dir) / 2]; // pivo é o do meio
        while (i <= j) {
            while (array[i] < pivot) { i++; }
            while (array[j] > pivot) { j--; }
            if (i <= j) { swap(i, j); i++; j--; }
        }
        if (esq < j) quick_Sort_Middle(esq, j);
        if (i < dir) quick_Sort_Middle(i, dir);
    }

    /**
    * Quicksort com piv no primeiro elemento.
    */
    private void quick_Sort_First(int esq, int dir) {
        int i = esq;
        int j = dir;
        int pivot = array[esq]; // pivo é o primeiro elemento
        while (i <= j) {
            while (array[i] < pivot) { i++; }
            while (array[j] > pivot) { j--; }
            if (i <= j) { swap(i, j); i++; j--; }
        }
        if (esq < j) quick_Sort_First(esq, j);
        if (i < dir) quick_Sort_First(i, dir);
    }

    /**
    * Quicksort com pivo no ultimo elemento.
    */
    private void quick_Sort_Last(int esq, int dir) {
        int i = esq;
        int j = dir;
        int pivot = array[dir]; // pivo é o ultimo elemento
        while (i <= j) {
            while (array[i] < pivot) { i++; }
            while (array[j] > pivot) { j--; }
            if (i <= j) { swap(i, j); i++; j--; }
        }
        if (esq < j) quick_Sort_Last(esq, j);
        if (i < dir) quick_Sort_Last(i, dir);
    }

    /**
    * Quicksort com pivo mediana de tres (primeiro, meio, ultimo).
    */
    private void quick_Sort_MedianOfThree(int esq, int dir) {
        int i = esq;
        int j = dir;
        int meio = (esq + dir) / 2;

        // calcular a mediana de tres
        int a = array[esq];
        int b = array[meio];
        int c = array[dir];
        int pivot = 0;
        if ((a >= b && a <= c) || (a <= b && a >= c)) {
            pivot = a;
        } else if ((b >= a && b <= c) || (b <= a && b >= c)) {
            pivot = b;
        } else {
            pivot = c;
        }

        while (i <= j) {
            while (array[i] < pivot) { i++; }
            while (array[j] > pivot) { j--; }
            if (i <= j) { swap(i, j); i++; j--; }
        }
        if (esq < j) quick_Sort_MedianOfThree(esq, j);
        if (i < dir) quick_Sort_MedianOfThree(i, dir);
    }

    // métodos publicos para chamar cada versão
    public void sortMiddle(int[] arr) {
        this.array = arr;
        if (arr != null && arr.length > 0) quick_Sort_Middle(0, arr.length - 1);
    }

    public void sortFirst(int[] arr) {
        this.array = arr;
        if (arr != null && arr.length > 0) quick_Sort_First(0, arr.length - 1);
    }

    public void sortLast(int[] arr) {
        this.array = arr;
        if (arr != null && arr.length > 0) quick_Sort_Last(0, arr.length - 1);
    }

    public void sortMedianOfThree(int[] arr) {
        this.array = arr;
        if (arr != null && arr.length > 0) quick_Sort_MedianOfThree(0, arr.length - 1);
    }

    public static void main(String[] args) {
        LAB_05_QuickSort_e_seu_pivo sorter = new LAB_05_QuickSort_e_seu_pivo();
        int[] myArray = {10, 7, 8, 9, 1, 5, 12, -3};

        System.out.println("Array original: " + Arrays.toString(myArray));

        // Teste cada estratégia:
        int[] arr1 = Arrays.copyOf(myArray, myArray.length);
        sorter.sortMiddle(arr1);
        System.out.println("Ordenado (pivo meio): " + Arrays.toString(arr1));

        int[] arr2 = Arrays.copyOf(myArray, myArray.length);
        sorter.sortFirst(arr2);
        System.out.println("Ordenado (pivo primeiro): " + Arrays.toString(arr2));

        int[] arr3 = Arrays.copyOf(myArray, myArray.length);
        sorter.sortLast(arr3);
        System.out.println("Ordenado (pivo ultimo): " + Arrays.toString(arr3));

        int[] arr4 = Arrays.copyOf(myArray, myArray.length);
        sorter.sortMedianOfThree(arr4);
        System.out.println("Ordenado (pivo mediana de tres): " + Arrays.toString(arr4));
    }
}
