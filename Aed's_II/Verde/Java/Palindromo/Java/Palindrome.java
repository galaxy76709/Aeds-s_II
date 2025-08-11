/*
###### PALINDROMOS ######
Palindromos sao as palavaras ou frases que se lendo de tras para 
frente se coincidem, assim dando a mesma coisa 
Como: 
arrra
osso
ovo
rotor
A base do teto desaba
Socorram-me, subi no ônibus em Marrocos

Sendo assim a atividade terar que:
Crie um método iterativo que recebe uma string como parâmetro e retorna true se essa é um palíndromo.
Na saída padrão, para cada linha de entrada, escreva uma linha de saída com SIM/NAO indicando se a linha é um palíndromo.
Destaca-se que uma linha de entrada pode ter caracteres não letras
A entrada termina com a leitura de FIM


*/


import java.util.*; 


public class Palindrome 
{
// iniciando funcao principal da minha classe 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner (System.in);
        
        System.out.println("Escreva a palavra");
        String Text = sc.nextLine();
// adicionando valor para a variavel
        Text = Text.replaceAll("\\s+","").toLowerCase(); // -> fazendo assim, espacos em brancos serao descartados 

// declarando variaveis 
        int start = 0;
        int size   = Text.length() - 1; 
        boolean isPalindrome = true; 

// laco de repeticao que vai do inicio da strinf ao final ( fisico ), e o caso o valor seja falso o laco para (condicao logica ) 
        while ( start < size && isPalindrome )
        {
            if (Text.charAt( start ) != Text.charAt(size))
            {
                isPalindrome = false; 
            }
// decrementacao onde o tamanho maximo ( size ) se reduxa 
            start = start + 1; 
            size  = size  - 1;
        }
// condicao que prever caso 
        if ( isPalindrome )
        {
            System.out.println("SIM");
        } else 
        {
            System.out.println("NAO");
        }

    }
    
}
