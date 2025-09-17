package Lista;
import java.util.*;


public class line 
{

// metodo para printar  
    static public void print_list ( int [ ] array )
    {
        int size = array.length; 
        
        if ( size >= 0 )
        {
            for ( int i = 0; i < size; i++ )
            {
                if (array[i] != 0) // <-- ignora os zeros
                {
                    System.out.print( array [ i ] + " " );
                }
            }
            System.out.println();
        }
    }


//------------

// funcao para insercao no inicio 
    static public int [ ] Insert_in_Begin (int [ ] array, int number)
    {
        if (array == null) return new int[] { number };

// variavel aux recebe o valor do primeiro arranjo passado 
        int size_origin = array.length;

// alocando espaço para o novo arranjo
        int[   ] other_array         =  new int [ size_origin + 1 ]; // -> receberar um valor a mais ( ideia de limite )
                 other_array [ 0 ]   = number; 

// condicao preve caso o valor seja menor que o esperado  
        if ( size_origin <= 0 )
        {
            System.out.println("Erro.. Lenght, is not enough");
        }
         else 
        {
            for (int i = 0; i < size_origin; i++ )
                { other_array[ i+1 ] = array[i]; } // proximo valor recebera o valor antigo do array
            
        }
// retorna valor do novo arranjo 
        return other_array;    
    }

//------------

    static public int [ ] Remove_from_begin (int [ ] array )
    {
// condicao que preve caso o tem seu valor seja o minimo possivel 
        int length_Aux = array.length;

    if (array == null || array.length == 0) 
    {
        System.out.println("Erro... length is not enough");
        return array;
    }


// retorna erro 
        if (length_Aux <= 0 ) 
        { System.out.println("Erro... lenght is not enough");}
        
// retorna o arranjo vazio 
        else if (length_Aux == 1)
        {   return new int [0]; }
        
        else {
// preciso tirar o primeiro valor do arranjo 
            int [ ] other_array = new int [ length_Aux - 1 ];

// vai pular a primeira posicao do arranjo assim, 1 2 3 | 2 3 
            for (int i = 0; i < other_array.length; i++ )
                { other_array [ i ] = array [i+1];  } 

                return other_array;
        }

        return array; 
    }

//------------

    public static void main(String[] args) 
    {
// iniciando valor do tamanho do arranjo
 Scanner sc = new Scanner(System.in);

        System.out.print("Digite o tamanho inicial do array: ");
       // int tam = sc.nextInt();
        int[] array = new int[3];

        System.out.println("\nLista inicial:");
        print_list(array);

        System.out.println("\nInserindo 10, 20 e 30 no inicio...");
        array = Insert_in_Begin(array, 10);
        array = Insert_in_Begin(array, 20);
        array = Insert_in_Begin(array, 30);

        System.out.println("Lista apos insercoes:");
        print_list(array);

        System.out.println("\nRemovendo o primeiro elemento...");
        array = Remove_from_begin(array);

        System.out.println("Lista apos remocao:");
        print_list(array);

        sc.close();
    }
}
