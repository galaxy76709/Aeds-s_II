package Verde.Java;
import java.util.*;

public class TP02Q01_AlgebraBooleana {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String entrada = in.nextLine().trim();
            if (entrada.equals("0") || entrada.isEmpty()) break; // condição de parada
            
            System.out.println(executar(entrada));
        }

        in.close();
    }

    private static String executar(String linha) {
        try {
            String[] partes = linha.split("\\s+");
            int qtdVars = Integer.parseInt(partes[0]);

            // lê os valores de A, B, C...
            boolean[] valores = new boolean[qtdVars];
            for (int i = 0; i < qtdVars; i++) {
                valores[i] = partes[i + 1].equals("1");
            }

            // remonta a expressão
            StringBuilder sb = new StringBuilder();
            for (int i = qtdVars + 1; i < partes.length; i++) {
                sb.append(partes[i]).append(" ");
            }
            String expr = sb.toString().trim();

            // substitui variáveis por true/false
            for (int i = 0; i < qtdVars; i++) {
                char var = (char) ('A' + i);
                expr = expr.replace(String.valueOf(var), valores[i] ? "true" : "false");
            }

            boolean resultado = avaliar(expr);
            return resultado ? "1" : "0";

        } catch (Exception e) {
            return "0"; // em caso de erro, devolve 0
        }
    }

    private static boolean avaliar(String expr) {
        expr = expr.trim();

        // remove parênteses externos redundantes
        if (expr.startsWith("(") && expr.endsWith(")") && parBalanceado(expr)) {
            return avaliar(expr.substring(1, expr.length() - 1));
        }

        // NOT
        if (expr.startsWith("not(")) {
            return !avaliar(expr.substring(4, expr.length() - 1));
        }

        // AND
        if (expr.startsWith("and(")) {
            String conteudo = expr.substring(4, expr.length() - 1);
            String[] args = separar(conteudo);
            for (String s : args) {
                if (!avaliar(s)) return false;
            }
            return true;
        }

        // OR
        if (expr.startsWith("or(")) {
            String conteudo = expr.substring(3, expr.length() - 1);
            String[] args = separar(conteudo);
            for (String s : args) {
                if (avaliar(s)) return true;
            }
            return false;
        }

        // valores finais
        if (expr.equals("true")) return true;
        if (expr.equals("false")) return false;

        return false; // fallback
    }

    // garante que os parênteses externos realmente fecham
    private static boolean parBalanceado(String expr) {
        int nivel = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') nivel++;
            else if (c == ')') nivel--;
            if (nivel == 0 && i < expr.length() - 1) return false;
        }
        return true;
    }

    private static String[] separar(String conteudo) {
        List<String> res = new ArrayList<>();
        int nivel = 0, inicio = 0;

        for (int i = 0; i < conteudo.length(); i++) {
            char c = conteudo.charAt(i);
            if (c == '(') nivel++;
            else if (c == ')') nivel--;
            else if (c == ',' && nivel == 0) {
                res.add(conteudo.substring(inicio, i).trim());
                inicio = i + 1;
            }
        }
        res.add(conteudo.substring(inicio).trim());
        return res.toArray(new String[0]);
    }
}
