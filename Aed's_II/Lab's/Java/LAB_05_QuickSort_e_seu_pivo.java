import java.util.Arrays;

public class LAB_05_QuickSort_e_seu_pivo 
{

    private int [ ] array; 

// funcoa para realizar o swap 
    private void swap ( int x, int y )
    {
        int temp    = array [ x ];
        array [ x ] = array [ y ];
        array [ y ] = temp; 
    }


    private void quick_Sort ( int esq, int dir )
    {
        int i = esq; int j = dir; 
        int pivot = array [( esq + dir ) / 2 ]; 
        while ( i <= j )
        {
            while ( array [ i ] < pivot )
                    { i++; }
            while ( array [ j ] > pivot )
                    { j--; }
            if ( i <= j )
                {swap ( i, j ); i++; j--;}    
        }
        if (esq < j )   {quick_Sort( esq, j );}
        if (i  < dir)   {quick_Sort( i, dir );}
    }// end

    public void sort ( int [ ] array_sort )
    {
        if ( array_sort == null || array_sort.length <= 0 )
        {
            return ; 
        }

        this.array = array_sort;
        int lengh = array_sort.length; 
        quick_Sort ( 0 , lengh - 1 ); 
    }

    public static void main ( String [ ] args )
    {
        LAB_05_QuickSort_e_seu_pivo sorter = new LAB_05_QuickSort_e_seu_pivo();
        int [ ]   myArray = {10, 7, 8, 9, 1, 5, 12, -3};

        System.out.println("Array original: " + Arrays.toString(myArray));    
    
        sorter.sort ( myArray );
        
        System.out.println("Array ordenado: " + Arrays.toString(myArray));
    
    }
}
