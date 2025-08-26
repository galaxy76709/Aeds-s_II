import java.util.Scanner;

public class LAB_02_Combinador 
{


    public static char [ ] Combinador (String txt, String txt_S )
    {
        int lenght               =   txt.length( ) + txt_S.length();
        char [ ] array           =   txt.toCharArray( );
        char [ ] array_2         =   txt_S.toCharArray( );
        char [ ] resp            =   new char[ lenght + 1 ];  
        int      i               =   0;
        int      j               =   0;
        int resp_index           =   0;

        while ( i < txt.length( ) || j < txt_S.length( ) )
        {
            if (  i < txt.length( ) )
            {
            //System.out.print("eu nao odeio a vida do bidu (eu minto)");
                resp [ resp_index ] = array [ i ];
                i = i + 1; 
                resp_index = resp_index + 1;  
            }

            if ( j < txt_S.length( ) )
            {
                resp [ resp_index ] = array_2 [ j ];
                j = j + 1;
                resp_index = resp_index + 1;  
            }

        } 

       resp [resp_index] = '\0';

       return resp; 
    }


    public static void main(String[] args) 
    {
        Scanner sc = new Scanner( System.in );

        String txt   = "";
        String txt_S = "";

        while ( sc.hasNextLine( ))
        {
            txt         = sc.nextLine( );
            //System.out.println(txt);
            txt_S       = sc.nextLine( );
            //System.out.println(txt_S);
            char [ ]final_resp = Combinador( txt, txt_S );
            System.out.println( final_resp );
        }



        sc.close();
        
    }
    
}