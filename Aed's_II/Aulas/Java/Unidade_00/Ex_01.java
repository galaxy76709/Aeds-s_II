public class Ex_01 {

// Metodo para buscar um valor no array 
    static boolean Findx(int[] numbers, int target) {
// iniciando variavel aux    
     boolean found = false;

// Percorre o array ate achar ou acabar
        for (int i = 0; i < numbers.length && !found; i++) {
// caso a condicao seja verade
            if (numbers[i] == target) {
// assim que encontrar o valor, ele finalizarar o laco de repeticap 
                found = true;
            }
        }
// seu retorno dependendo se achar 
        return found;
    }

public static void main(String[] args) {
// criando arranjo para os valores do exemplo 
        int[] arr = {5, 8, 12, 3, 7};
        int x = 12; // -> valor a ser procurado 

        if (Findx(arr, x)) {
            System.out.println("O valor " + x + " esta no array.");
        } else {
            System.out.println("O valor " + x + " NAO esta no array.");
        }
    }
}
