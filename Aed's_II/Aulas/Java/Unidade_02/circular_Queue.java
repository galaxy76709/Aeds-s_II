public class circular_Queue 
{
    public class cell

    public    int cell;
    public    int head;
    public    int tail;
    public    int capacty;
    public [] int array;
    
// construtor padrao 
    public circular_Queue ( )
    {
        if ( cell != null )
        {
            this.capacty = 6;
            this.array   = new int [this.capacty];
            this.head    = 0;
            this.tail    = 0;
            
        }
        else  
        {System.out.println("Erro, value is null");}
    }

// construtor com valores 
    public circular_Queue (int capacty)
    {   
        if ( cell != null && this.capacty > 0 )
        {
            this.capacty = capacty;
            this.array   = new int [this.capacty];
            this.head    = 0;
            this.tail    = 0; 
        }
    }


    public boolean isEmpty ( int value )
    {
        if ( this.head == this.tail )
        return false; 

        int i = thid.head;

        while ( i != tail )
        {
            if ( this.array [ i ] == value )
            return true;
            i = ( i + 1 ) % this.capacty;
        }
        return false;
    }

    public int First_in ( int value )
    {
        if ( this.array != null && cell != null && this.capacty > 0  )
        {
            int i = this.head;

            while ( i != this.tail ) 
            {
                
            }
        }
    }
}
