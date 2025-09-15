package Verde.Java;
import java.util.Scanner;

public class TP03Q02_Inversao_Recursiva 
{

    public static String inverterRecursivo(String txt) 
    {
        // CASO BASE: Se a string for vazia ou tiver apenas um caractere,
        // a inversão é a própria string. Isso para a recursão.
        if (txt == null || txt.length() <= 1) 
        { return txt; }

        char primeiroCaractere = txt.charAt(0);
            String restoDaString = txt.substring(1);
            return inverterRecursivo(restoDaString) + primeiroCaractere;
    }


    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String txt = ""; 

        while (sc.hasNextLine()) 
        {
            txt = sc.nextLine();
            String new_txt = inverterRecursivo(txt);
            System.out.println(new_txt);
        }
        
        sc.close();
    }
}