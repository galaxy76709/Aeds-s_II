package Verde.Java;
import java.util.*;

public class TP03Q03_ciframento_cesar {

    // Método de ciframento de César com faixa ASCII 32–126
    public static String criptografia(String entrada) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            int ascii = (int) c;

            if (ascii >= 32 && ascii <= 126) {
                int novoAscii = 32 + ((ascii - 32 + 3) % 95);
                sb.append((char) novoAscii);
            } else {
                sb.append(c); // mantém caracteres fora do intervalo
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        while (!text.equals("FIM")) {
            System.out.println(criptografia(text));
            text = sc.nextLine();
        }

        sc.close();
    }
}
