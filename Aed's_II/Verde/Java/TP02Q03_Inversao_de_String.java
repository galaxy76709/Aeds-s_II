package Verde.Java;
import java.util.*;

public class TP02Q03_Inversao_de_String 
{
    public static String invert( String txt ) 
    {

        StringBuilder invertido = new StringBuilder( txt ); 

        invertido.reverse( ); 

        String new_String = invertido.toString( ); 

        return new_String;
    }


    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String txt = ""; 

        while ( sc.hasNext( )  ) 
        {
        txt = sc.nextLine( );

        String new_txt = invert( txt );

        System.out.println ( new_txt );

        }
        
    }
    
}