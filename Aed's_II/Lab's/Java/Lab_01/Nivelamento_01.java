
import java.util.*;

public class Nivelamento_01 
{

static final int MAX = 100; // -> Criando uma constante para facilitar o processo

//funcoa destinada para verificacao de da palavra fim no arranjo 
// Função para verificar se a palavra é "FIM"
    static boolean isEnd(String palavra) 
    {    return palavra.equals("FIM");   }

// funcao destinada a conta de palavras maiusculas
    static int count_Uppercase (String temp )
{
// iniciando variaveis aux 
    int count = 0;
    int lengh = temp.length(); // resgatando o tamanho do arranjo 

// condicoa de existencia 
    if ( temp != null )
    {
// laco de repeticao que le o tamanho da string todo ou ler mais se necessario 
      for (int x = 0; x < lengh && x < MAX; x = x + 1 ) 
      {
// condicao que passa cada caractere para a funcao ( isUppserCase ) e verifica se sao letras maiusculas
        if ( Character.isUpperCase(temp.charAt(x)))
        {
            count = count + 1; // -> contador e incrementado 
        }
      }

    }

    return ( count );  // -> retorna a contagem 

}

// funcao principal 

    public static void main(String[] args) {
// abrindo o scanner para leitura 
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite uma palavra (ou FIM para sair): ");
        String entrada = sc.nextLine(); 
        

        // Repetir enquanto a entrada NÃO for "FIM"
        while ( ! isEnd ( entrada  )) {

            int maiusculas = count_Uppercase ( entrada );
            System.out.println("Quantidade de letras maiusculas: " + maiusculas);

            System.out.print("Digite uma palavra (ou FIM para sair): ");
            entrada = sc.nextLine();
        }

        sc.close();
        System.out.println("Programa encerrado.");
        }
    }


    


 