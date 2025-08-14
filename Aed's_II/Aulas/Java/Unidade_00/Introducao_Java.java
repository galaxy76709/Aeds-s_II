
/*
 * O nome do arquivo devera ser o mesmo da classe, por conta do seu compilador
 Deixar a primeira letra arquiva 
 * 
 * 
 */

import java.util.*; 

class Introducao_Java { // -> nome da classe sendo igual do arquivo 
    // o scanner 
    public static Scanner sc = new Scanner (System.in); // iniciando o Scanner, ele precisa ter uma variavel para recber os valores 
    public static void main ( String agrs []) // iniciando a main segue o mesmo padrao 
    {
// iniciando variaveis para soma

    int number_01 = 0; 
    int number_02 = 0; 
    int sum       = 0;

// leitura 
        System.out.println("Escreva o primeiro numero.");
        number_01 = sc.nextInt( ); 

        System.out.println("Escreva o segundo numero.");
        number_02 = sc.nextInt( ); 

// iniciando soma
        sum = number_01 + number_02; 

        System.out.println("O valor da soma de ["+ number_01 + "] com ["+ number_02 + "] e -> " + sum );


    }
}