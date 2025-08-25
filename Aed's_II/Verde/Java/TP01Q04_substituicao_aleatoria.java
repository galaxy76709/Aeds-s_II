package Verde.Java;
import java.util.*;

public class TP01Q04_substituicao_aleatoria {

// Metodo que sorteia duas letras e substitui todas as ocorrencias da primeira pela segunda
    public static String substituirAleatorio(String entrada, Random gerador) {
// sorteio das duas letras minúsculas
        char letra1 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));
        char letra2 = (char) ('a' + (Math.abs(gerador.nextInt()) % 26));

// construir nova string com substituicoes
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < entrada.length(); i++) {
            char c = entrada.charAt(i);
            if (c == letra1) {
                sb.append(letra2);
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random gerador = new Random();
        gerador.setSeed(4); // seed fixa

        String linha = sc.nextLine();

        while (!linha.equals("FIM")) {
            String resultado = substituirAleatorio(linha, gerador);
            System.out.println(resultado);
            linha = sc.nextLine();
        }

        sc.close();
    }
}
