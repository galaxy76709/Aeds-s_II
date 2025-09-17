public class Insertion_Sort 
{
    static public void Print_Array ( int [ ] array  ) 
    { 
        int temp = array.length; 
         if ( temp >  0 ) 
         { 
            for (int i = 0; i < temp; i ++ )
            {
                System.out.print("[" + array [ i ] + "]" );
            }
         } else if ( temp == 0 ) {
            System.out.println("{}");
         }
         else {
            System.out.println("Erro, lenght is not enough...");
         }
    }


    void sort ( int [ ] array )
    {
        int temp_lenght = array.length; 

        if ( temp_lenght <= 0 )
        {
             System.out.println( "Erro, lenght is not enough ");
        } else {
            for ( int i = 1; i < temp_lenght; i++  )
            {
                int key = array[ i ]; // key sera o valor da posicao de i 

                int j   = i - 1 ; // j recebera a ultima posicao do arranjo  

                while ( j >= 0 && array [ j ] > key ) {
// a posicao atual de i ( j + 1 ) sera subistituido pela posicao de j ( a posicao anterior )
                    array [ j + 1] = array [ j ]; 
                    j--; // decrementando os valos de j para verificar todos os valores dos ultimos 
                    
                }
                array [ j + 1 ] = key;  
            }
        }

    }

    public static void main(String[] args) 
    {
        int arr [ ] = { 5, 2 ,19 ,2 ,14 ,7 ,2 ,258 ,1015 ,24 ,2 }; 
        System.out.println("Arranjo antes da ordenacao");
        Print_Array ( arr );

        Insertion_Sort ob = new Insertion_Sort( ); 


        ob.sort( arr );
        System.out.println("\n");
        
        System.out.println("Arranjo depos da ordenacao");
        Print_Array ( arr );

    }
    
}
