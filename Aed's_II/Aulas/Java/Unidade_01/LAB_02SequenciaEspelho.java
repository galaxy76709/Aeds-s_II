import java.util.*;

public class LAB_02SequenciaEspelho {


    public static void mirror(int F_value, int S_value) {
        for (int x = F_value; x <= S_value; x++) 
        {  System.out.print(x); }

        for (int y = S_value; y >= F_value; y--) 
        {    System.out.print(invert(y)); }
    }

    public static String invert(int n) {
        if (n < 10)
         {return String.valueOf(n);}

        String str = String.valueOf(n);
        String invertido = "";

        for (int i = str.length() - 1; i >= 0; i--)
         { invertido += str.charAt(i); }

        return invertido;
    }

 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        while (sc.hasNext()) {
            int number_1 = sc.nextInt();
            int number_2 = sc.nextInt();
            
            mirror(number_1, number_2);
            
            System.out.println(); 
        }

        sc.close();
    }
}