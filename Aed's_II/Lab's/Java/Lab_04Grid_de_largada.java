import java.util.*;

/*
 *
 * Ideia por tras: contar quantidade de movimetacoes feitas no sort
 * para saber quem "ultrapassou" 
 * */
public class Lab_04Grid_de_largada {
    static Scanner sc = new Scanner(System.in);

    //-------------
    public static void Populating_array(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = sc.nextInt();
        }
    }

    //-------------
    public static void Print_Array(int[] array) {
        if (array.length > 0)
            for (int i = 0; i < array.length; i++) {
                System.out.print("[" + array[i] + "]");
            }
        else
            System.out.println("[]");
    }

    //-------------
    public static int Sort_Hors(int[] array) {
        int count = 0;
        int temp_lenght = array.length;

        if (temp_lenght > 0 && array != null) {
            for (int i = 1; i < temp_lenght; i++) { 
                int key = array[i];
                int j = i - 1;

                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];                    
                     j--;
                    count++;
                }
                array[j + 1] = key;
            }

        } else {
            //System.out.println("ERRO, lenght our array missing data. ");
        }

        return (count);
    }

    //-------------
    public static void main(String[] args) {
        while (sc.hasNext()) { 
            int N = sc.nextInt();

            int[] largada = new int[N]; // Grid de Largada
            int[] chegada = new int[N]; // Grid de Chegada

            Populating_array(largada);
            Populating_array(chegada);
                        
            int[ ] posicaoDeLargada = new int [ N ]  ;
            for (int i = 0; i < N; i++) {
                int competidor = largada[ i ];
                posicaoDeLargada[competidor - 1] = i;
            }

            int[] arrayParaOrdenar = new int[N];
            for (int i = 0; i < N; i++) {
                int competidorQueChegou = chegada[i];
                    arrayParaOrdenar[i] = posicaoDeLargada[competidorQueChegou - 1];
            }

             int ultrapassagens = Sort_Hors(arrayParaOrdenar);

            System.out.println(ultrapassagens);
        }
    }
}