import java.util.Scanner;

public class Merge_SortXubous {
    static int[] arr;
    static int[] aux;

    static void intercalar(int esq, int meio, int dir) {
        int i = esq;
        int j = meio + 1;
        int k = esq;

        while (i <= meio && j <= dir) {
            if (arr[i] <= arr[j]) aux[k++] = arr[i++];
            else aux[k++] = arr[j++];
        }

        while (i <= meio) aux[k++] = arr[i++];
        while (j <= dir) aux[k++] = arr[j++];

        for (k = esq; k <= dir; k++) arr[k] = aux[k];
    }

    static void merge_sort(int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            merge_sort(esq, meio);
            merge_sort(meio + 1, dir);
            intercalar(esq, meio, dir);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        arr = new int[n];
        aux = new int[n];

        for (int i = 0; i < n; i++) {
            if (sc.hasNextInt()) arr[i] = sc.nextInt();
            else arr[i] = 0;
        }

        if (n > 0) merge_sort(0, n - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i]);
        }
        System.out.println(sb.toString());
    }
}