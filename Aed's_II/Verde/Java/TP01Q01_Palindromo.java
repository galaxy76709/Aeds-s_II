package Verde.Java;
import java.util.*;

public class TP01Q01_Palindromo {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text;
        
// laço para ler até encontrar "FIM"
        boolean keepRunning = true;
        while (keepRunning) {
            text = sc.nextLine();
            
// condição de parada
            if (text.equals("FIM")) {

                keepRunning = false;
            } else {

//inicializando verificao das letras 
                int start = 0;
                int end = text.length() - 1;
                boolean isPalindrome = true;
                
                while (start < end && isPalindrome) {
                    if (text.charAt(start) != text.charAt(end)) {
                        isPalindrome = false;
                    }
                    start = start + 1;
                    end   = end - 1;
                }
                
                if (isPalindrome) {
                    System.out.println("SIM");
                } else {
                    System.out.println("NAO");
                }
            }
        }
        
        sc.close();
    }
}
