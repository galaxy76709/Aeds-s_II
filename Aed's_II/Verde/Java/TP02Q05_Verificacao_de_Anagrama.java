package Verde.Java;
import java.util.Scanner;

public class TP02Q05_Verificacao_de_Anagrama {

    /**
     * Método iterativo para verificar se duas strings são anagramas.
     * Usa contagem de caracteres para maior eficiência.
     * 
     * @param s1 Primeira string.
     * @param s2 Segunda string.
     * @return true se forem anagramas, false caso contrário.
     */
    public static boolean saoAnagramas(String s1, String s2) {
        // Normalizar: remover espaços e converter para minúsculas
        s1 = s1.replaceAll("\\s+", "").toLowerCase();
        s2 = s2.replaceAll("\\s+", "").toLowerCase();

        // Se os comprimentos forem diferentes, não pode ser anagrama
        if (s1.length() != s2.length()) {
            return false;
        }

        // Contagem de caracteres (assumindo apenas ASCII estendido)
        int[] freq = new int[256];

        // Contar letras da primeira string
        for (char c : s1.toCharArray()) {
            freq[c]++;
        }

        // Subtrair letras da segunda string
        for (char c : s2.toCharArray()) {
            freq[c]--;
            if (freq[c] < 0) {
                return false; // já ficou desequilibrado
            }
        }

        // Se chegou até aqui, são anagramas
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String linha = sc.nextLine().trim();

            if (linha.equalsIgnoreCase("FIM")) {
                break;
            }

            String[] partes = linha.split(" - ");
            String palavra1 = partes[0];
            String palavra2 = partes[1];

            System.out.println(saoAnagramas(palavra1, palavra2) ? "SIM" : "NÃO");
        }

        sc.close();
    }
}
