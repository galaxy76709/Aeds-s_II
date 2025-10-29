package Lista;


    public class cell
{
    public int elemento;
    public cell right;
    public cell down;
    public cell left;
    public cell up;

// construtor padrao;

    public cell ( )
    {
        elemento = -1;
        right = null;
        down  = null;
        left  = null;
            up    = null; 

    }


// construtor com assinaturas
    public cell ( int elemento )
    {
        this.elemento = elemento; 
        if (elemento < 0 )
        { cell ( ); }

        else 
        {
            right = null;
            down  = null;
            left  = null;
            up   = null;
        }
    }


}

public class flexible-matrix 
{

    public cell start;
    public int row;
    public int column;
    
    
    public flexible-matrix ( )
    {
        this ( 3, 3 ); // matriz padrao 3x3
    }


    public flexible-matrix ( int row, int column )
    {
        this.row    = row;
        this.column = column;
        flexibleMatrix ( row, column );
    }
    
    public void flexibleMatrix ( int row, int column)
    {
        start           = new cell(); // crinado a primeira celula 
        cell tmp        = start;      // referencia para a primeira celula (temporaria)
        cell previous   = start;     // referencia para  a celula anterior (temporaria)

// laço para criar as primeiras colunas
        for ( int i = 0; i < elemento; i++)
        {
            tmp.right = new cell ( ); // criando celula para a direita
            tmp.right.left = tmp;    // ligando a celula criada com a celula anterior
            tmp = tmp.right;         // avancando para a celula criada
        }


        cell upperRow = start; // referencia para a primeira celula ( linha sup)

// laço para cirar as linhas 
        for (int j = 1; j < row; j++)
        {
            cell newRow    = new cell ( );      // criando nova linha
            upperRow.down =        newRow;     // ligando a  linha com a linha sup
            newRow.up     =      upperRow;    // ligando a linha suop com a nova linha

            cell current =         newRow;      // referencia para a celula atual (temporaria)
            cell tmpUp   = upperRow.right;     // referencia para a celula da linha sup (temporaria)

// laço para criar as colunas
            for ( int k = 1; k < column; k++)
            {
                current .right      =   new cell ( ); // criando celula para a direita
                current .right.left =        current; // ligando a celula com a anterior (current)
                current             = current .right; // avancando para a celula criada

                current .up       =            tmpUp; // ligando a celula atual com a celula da linha sup
                tmpUp .down       =          current; // ligando a celula da linha sup com a celula atual

                tmpUp             =       tmpUp.right;// avancando para a proxima celula da linha sup
            }

            upperRow = upperRow.down; // avancando para a proxima linha sup
        }
   }


   public void fillMatrix ( int row, int columm, int value )
   {
        cell tmp = start; 
        
        for (int i = 0; i < row; i++ ) 
        {
            cell tmpCol = tmp; // referencia temp para a coluna

            for ( int j = 0; j < column; j++ )
            {
                tmpCol.elemento = value;        // atribuindo o valor a celula
                tmoCol          = tmpCol.right; // avancando para a proxima celula da linha
            }

            tmp = tmp.down; // avanacando a linha
        }
   }


   public void show ( int row, int column )
   {
        cell tmp  = start;
        for ( int i = 0; i < row; i++ )
        {
            tmpCol = tmp; // referencia tmp para coluna

            for ( int j = 0; j < column; j++)
            {
                System.out.println(tmpCol.elemento + " ");
                tmpCol = tmpCol.right; // avancando para a proxima celula da linha

            }
            System.out.println( );
            tmp = tmp.down; // avancando a linha
        }
   }



   public void diagonalPrincipal ( int row, int column )
   {
        cell start = new cell ( );
        cell tmp = start;

        while ( tmp != null )
        {
            System.out.println(tmp.elemento + " ");

            if ( tmp.right != null && tmp.down != null)
            {tmp = tmp.right.down}

            else 
            {System.out.println("[ ]");}
        }
   }


   public void main ( String [ ] args )
   {
        flexible-matrix matrix = new flexible_matrix ( 4, 4 );
        matrix.fillMatrix ( 4, 4, 5 );
        matrix.show ( 4, 4 );
   }
}
