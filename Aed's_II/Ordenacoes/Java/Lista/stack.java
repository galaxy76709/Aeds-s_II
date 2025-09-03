package Ordenacoes.Java.Lista;
import java.util.*;

public class stack 
{
    //--------------
    public static void Print_stack(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("[]");
        }

        for (int i = 0; i < array.length; i++) { 
            System.out.println(array[i]);
        }
    }
    //--------------

    //--------------
    public static int[] Push_back(int[] array, int value) {
        if (array == null) {
            System.out.println("Erro: array nulo.");
            return null;
        }

        int length = array.length;

        int[] other_array = new int[length + 1];

        for (int i = 0; i < length; i++) {
            other_array[i] = array[i];
        }

        other_array[length] = value;

        return other_array;
    }
    //--------------

    //--------------
    public static int[] Pop_back(int[] array) {
        if (array == null) {
            System.out.println("Erro: array nulo.");
            return null;
        }

        int length = array.length;

        if (length == 0) {
            System.out.println("[]");
            return array;
        }

        int[] other_array = new int[length - 1];

        for (int i = 0; i < length - 1; i++) {
            other_array[i] = array[i];
        }

        return other_array;
    }
    //--------------

    //--------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] array = new int[0]; 

        System.out.println("Stack inicial:");
        Print_stack(array);

        array = Push_back(array, 10);
        array = Push_back(array, 20);
        array = Push_back(array, 30);

        System.out.println("Printando valores do stack:");
        Print_stack(array);

        System.out.println("Tirando os valores do stack:");
        array = Pop_back(array);
        Print_stack(array);

        array = Pop_back(array);
        Print_stack(array);

        array = Pop_back(array);
        Print_stack(array);

        sc.close();
    }
}
