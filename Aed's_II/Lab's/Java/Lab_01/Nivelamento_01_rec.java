import java.util.Scanner; // importa a classe Scanner para leitura do teclado

public class Nivelamento_01_rec { // inicio da classe principal

    static final int MAX = 100; // constante que limita a verificacao de tamanho

// Funcao para verificar se a palavra recebida e "FIM"
    static boolean isEnd(String palavra) { // recebe a palavra a verificar
        return palavra.equals("FIM"); // retorna true se for exatamente "FIM"
    }

// Funcao recursiva que conta letras maiusculas em uma string
// param: temp -> string a ser verificada
// param: index -> posicao atual dentro da string
    static int countUppercaseRecursive(String temp, int index) {
// se a string for nula, retorna 0 (nao ha caracteres)
        if (temp == null) { // verifica nulidade
            return 0; // retorna zero, nao ha maiusculas
        }

// se o indice alcancou o final da string ou o limite MAX, termina
        if (index >= temp.length() || index >= MAX) { // condicao de parada
            return 0; // fim da recursao, sem mais contagens
        }

// verifica se o caractere atual e maiusculo
        char c = temp.charAt(index); // obtem caractere na posicao index
        int add = Character.isUpperCase(c) ? 1 : 0; // 1 se maiusculo, 0 caso contrario

// chamada recursiva para o próximo indice e soma o resultado
        return add + countUppercaseRecursive(temp, index + 1); // soma e segue
    }
    

// Metodo recursivo que faz a leitura de entradas e processa enquanto nao for "FIM"
    static void processInputsRecursively(Scanner sc) {
        System.out.print("Digite uma palavra (ou FIM para sair): "); // pede entrada ao usuario
        String entrada = sc.nextLine(); // lê a linha digitada

// se for "FIM", termina a recursao naturalmente (nao usa break)
        if (isEnd(entrada)) { // verifica condicao de parada
            System.out.println("Programa encerrado."); // mensagem de encerramento
            return; // retorna para encerrar esta chamada recursiva
        }

// conta maiusculas de forma recursiva a partir do indice 0
        int maiusculas = countUppercaseRecursive(entrada, 0); // chama o contador recursivo

        // exibe o resultado para o usuario
        System.out.println("Quantidade de letras maiusculas: " + maiusculas); // mostra qtd

        // chama novamente o metodo para processar a próxima entrada (recursao)
        processInputsRecursively(sc); // chamada recursiva para ler mais entradas
    }

    public static void main(String[] args) { // metodo principal onde a execucao comeca
        Scanner sc = new Scanner(System.in); // cria um Scanner para entrada padrao

        // inicia o processamento recursivo das entradas
        processInputsRecursively(sc); // primeiro passo da recursao de leitura

        sc.close(); // fecha o Scanner (boa pratica)
    }

} // fim da classe Nivelamento_01
