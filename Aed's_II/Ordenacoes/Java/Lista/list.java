package Ordenacoes.Java.Lista;

public class list 
{

//-----------------------
    static public void Print_list (int [ ] array )
    {
// condicao de existencia 
        if (array != null && array.length > 0  )
        {
            for (int i = 0; i < array.length; i++ )
                 { 
                    if ( array [ i ] != 0 )
                    { System.out.println(array[ i ] + " ");} 
                   
                    else { System.out.println("[]");}
                
                }
    }
}
//-----------------------


//-----------------------
    static public int[] Pop_back ( int[] array )
    {
        int length_aux = array.length; 


        if ( array != null && length_aux > 0 )
        {
// variavel aux recebe valor do tamanho do arranjo 
            int[ ] other_aux = new int [ length_aux - 1 ];
            
            for (int i = 0 ; i < length_aux - 1; i++ )
            { 
                other_aux [ i ] = array [ i ];
            }
            return other_aux; 
        }  

        else if ( length_aux == 0 )
             { System.out.println("Erro, lenght is not enough...");
              return null; }
        else { System.out.println("Erro, missing data...");
              return null; }
        
    }
//-----------------------


//-----------------------
    static public int [ ] Push_back ( int[ ] array, int value )
    {
        if ( array == null )
        {
            System.out.println("Erro, missing data... ");
        }

        int aux_lenght = array.length; 

        if ( aux_lenght == 0 )
        {
            System.out.println("[]");
            return array; 
        }

        int [] other_array = new int [ aux_lenght + 1 ]; 

        for (int i = 0; i < aux_lenght; i++ )
        {
            other_array [ i ] = array [ i ]; 
        }

            other_array [ aux_lenght ] = value; 
            
            return other_array; 
    } 
//-----------------------




    public static int main ( String [ ] args )
    {
        return 0;
    }
    
}
