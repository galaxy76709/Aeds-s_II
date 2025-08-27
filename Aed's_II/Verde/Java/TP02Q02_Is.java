package Verde.Java;
import java.util.*;

public class TP02Q02_Is 
{
//----------------------------------------
    public static boolean Isvowel ( String txt )
    {
        boolean answer = true;
        txt            = txt.toLowerCase();
        char [] string = txt.toCharArray( ); 

        for ( char c : string )
        {
            if ( c != 'a' && c != 'e' && 
                 c != 'i' && c != 'o' && 
                 c != 'u' )
            {
                answer = false; // encontrou algo que não é vogal
            }
        }
        return (txt.length() > 0) ? answer : false;
    }
//----------------------------------------


//----------------------------------------
    public static boolean IsReal(String txt) {
        boolean answer = true; 
        txt            = txt.replace(',', '.'); 

        // só é real se tiver ponto decimal
        if (!txt.contains(".")) {
            return false;
        }

        try {
            Double.parseDouble(txt); // tenta converter
        } catch (NumberFormatException e) {
            answer = false;
        }
        return (txt.length() > 0) ? answer : false;
    }
//----------------------------------------


//----------------------------------------
    public static boolean Isconsonant ( String txt )
    {
        boolean answer = true;
        txt            = txt.toLowerCase();
        char [] string = txt.toCharArray( ); 

        for ( char c : string )
        {
            if ( c == 'a' || c == 'e' || 
                 c == 'i' || c == 'o' || 
                 c == 'u' || !Character.isLetter(c))
            {
                answer = false; 
            }
        }
        return (txt.length() > 0) ? answer : false;
    }
//----------------------------------------



//----------------------------------------
    public static boolean IsNumber ( String txt )
    {
        boolean answer = true; 
        char [] string = txt.toCharArray( );

        for ( char c : string )
        {
            if ( !Character.isDigit(c) )
            {
                answer = false; 
            }
        }
        return (txt.length() > 0) ? answer : false;
    }
//----------------------------------------


//----------------------------------------
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner( System.in );
        String txt; txt = sc.nextLine( ); 

        while  ( !txt.equals("FIM") )
        {
            boolean isV = Isvowel     ( txt );
            boolean isC = Isconsonant ( txt );
            boolean isN = IsNumber    ( txt );
            boolean isR = IsReal      ( txt ); 

            System.out.print   (isV ? "SIM " : "NAO ");
            System.out.print   (isC ? "SIM " : "NAO ");
            System.out.print   (isN ? "SIM " : "NAO ");
            System.out.println (isR ? "SIM " : "NAO ");
            txt = sc.nextLine( ); 
        }
        sc.close();
    }
}
