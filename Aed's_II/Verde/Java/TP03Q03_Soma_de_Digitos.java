import java.util.Scanner;
import java.lang.Math;

public class TP03Q03_Soma_de_Digitos {

    public static int textoParaInteiro(String str) {
        if (str.isEmpty()) {
            return 0;
        }

        char primeiroChar = str.charAt(0);
        int valorPosicional = (primeiroChar - '0') * (int)Math.pow(10, str.length() - 1);
        String restoDaString = str.substring(1);
        
        return valorPosicional + textoParaInteiro(restoDaString);
    }

    public static int somaDigitos(int numero) {
        if (numero < 10) {
            return numero;
        }

        int ultimoDigito = numero % 10;
        int restoDoNumero = numero / 10;
        
        return ultimoDigito + somaDigitos(restoDoNumero);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String txt;

        txt = sc.nextLine();

        while (!txt.equals("FIM")) {

            int x = textoParaInteiro(txt);
            int resp = somaDigitos(x);

            System.out.println(resp);

            txt = sc.nextLine();
        }

        sc.close();
    }
}