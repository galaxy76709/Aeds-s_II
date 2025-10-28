class Celula 
{
    public int elemento;
    public Celula inf, sup, esq, dir;

    public Celula() 
    {
        this(0);
    }

    public Celula(int elemento) 
    {
        this.elemento = elemento;
        this.inf = this.sup = this.esq = this.dir = null; // referencias anuladas 
    }
}

class Matriz 
{
    private Celula inicio;
    private int linha, coluna;

    public Matriz() 
    {
        this(3, 3); // matriz padrao 3x3
    }

    public Matriz(int linha, int coluna) 
    {
        this.linha  = linha;
        this.coluna = coluna;
        matrizFlex(linha, coluna);
    }

    //Criar Matriz Flexivel
    public void matrizFlex(int linha, int coluna) 
    {
        inicio = new Celula();
        Celula tmp = inicio;
        Celula anterior = inicio;

        for (int i = 0; i < linha; i++)
        {
            tmp.dir = new Celula();
            tmp.dir.esq = tmp;
            tmp = tmp.dir;
        }

        Celula linhaCima = inicio;
        for (int j = 1; j < linha; j++) 
        {
            Celula novaLinha = new Celula();
            linhaCima.inf = novaLinha;
            novaLinha.sup = linhaCima;

            Celula atual = novaLinha;
            Celula tmpCima = linhaCima.dir;

            for (int k = 1; k < coluna; k++) 
            {
                atual.dir = new Celula();
                atual.dir.esq = atual;
                atual = atual.dir;

                atual.sup = tmpCima;
                tmpCima.inf = atual;

                tmpCima = tmpCima.dir;
            }

            linhaCima = linhaCima.inf;
        }
    }

    // preencher matriz
    public void preencherMatriz(int linha, int coluna, int x) 
    {
        Celula tmpLinha = inicio;
        Celula tmpColuna;

        for (int i = 0; i < linha; i++) 
        {
            tmpColuna = tmpLinha;
            for (int j = 0; j < coluna; j++) 
            {
                tmpColuna.elemento = x; //para fazer o usuario  digitar a matriz em vez de passar X é so colocar tmpColuna.elemento = sc.nextInt();
                tmpColuna = tmpColuna.dir;
            }
            tmpLinha = tmpLinha.inf;
        }
    }

    // mostra elemento da matriz
    public void mostrar(int linha, int coluna) 
    {
        Celula tmpLinha = inicio;
        Celula tmpColuna;

        for (int i = 0; i < linha; i++) 
        {
            tmpColuna = tmpLinha;
            for (int j = 0; j < coluna; j++) 
            {
                System.out.print(tmpColuna.elemento + " ");
                tmpColuna = tmpColuna.dir;
            }
            System.out.println();
            tmpLinha = tmpLinha.inf;
        }
    }

    // pegar Diagonal Principal
    public void diagonalPrincipal(int linha, int coluna) 
    {
        Celula inicio = new Celula();
        Celula tmp = inicio;
        while (tmp != null) 
        {
            System.out.print(tmp.elemento + " ");
            if (tmp.dir != null && tmp.inf != null) 
            { tmp = tmp.dir.inf;} 
            
            else 
            { break;}
        }

    }

    // pegar Diagonal Secundaria
    public void diagonalSecundaria(int linha, int coluna) 
    {
        Celula inicio = new Celula();
        Celula tmp = inicio;
        while (tmp != null) 
        {
            System.out.print(tmp.elemento + " ");
            if (tmp.dir != null && tmp.inf != null) 
            {
                tmp = tmp.dir.inf;
            } 
            else 
            {
                break;
            }
        }
    }

    public static void main(String[] args) 
    {
        int valores = 5;
        Matriz m = new Matriz(3, 3);
        m.preencherMatriz(3, 3, valores);
        m.mostrar(3, 3);
        m.diagonalPrincipal(3, 3);
        m.diagonalSecundaria(3, 3);
    }
}