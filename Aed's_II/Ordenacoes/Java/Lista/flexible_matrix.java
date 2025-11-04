package Lista;

class cell {
    public int elemento;
    public cell right;
    public cell down;
    public cell left;
    public cell up;

    // construtor padrao;
    public cell() 
    { this(-1); }

    // construtor com assinaturas
    public cell(int elemento) {
        this.elemento = elemento;
        this.right    = null;
        this.down     = null;
        this.left     = null;
        this.up       = null;
    }
}

public class flexible_matrix {

    public cell start; 
    public int row;
    public int column;

    
    public flexible_matrix() {
        this(3, 3); // matriz padrao 3x3
    }

    
    public flexible_matrix(int row, int column) {
        if (row <= 0 || column <= 0) {
            throw new IllegalArgumentException("Linhas e colunas devem ser maiores que 0");
        }
        
        this.row    = row;
        this.column = column;

        
        // 1. Cria a célula inicial (0,0)
        this.start = new cell(); // crinado a primeira celula
        cell tmp   = this.start;   // referencia para a primeira celula (temporaria)

        // 2. Cria o restante da primeira linha (colunas)
        // laço para criar as primeiras colunas (Corrigido de 'elemento' para 'this.column')
        for (int i = 1; i < this.column; i++) 
        {
            tmp.right      = new cell(); // criando celula para a direita
            tmp.right.left = tmp;   // ligando a celula criada com a celula anterior
            tmp            = tmp.right;        // avancando para a celula criada
        }

        cell upperRowStart = this.start; // referencia para a primeira celula ( linha sup)

        // 3. Cria as linhas restantes
        // laço para cirar as linhas
        for (int j = 1; j < this.row; j++) {
            
            cell newRowStart = new cell();      // criando nova linha
            upperRowStart.down = newRowStart; // ligando a linha com a linha sup
            newRowStart.up = upperRowStart;   // ligando a linha suop com a nova linha

            cell current = newRowStart;       // referencia para a celula atual (temporaria)
            cell tmpUp = upperRowStart.right; // referencia para a celula da linha sup (temporaria)

            // 4. Cria as colunas restantes para a nova linha
            // laço para criar as colunas
            for (int k = 1; k < this.column; k++) {
                current.right = new cell();       // criando celula para a direita
                current.right.left = current;   // ligando a celula com a anterior (current)
                current = current.right;        // avancando para a celula criada

                if (tmpUp != null) {
                    current.up = tmpUp;     // ligando a celula atual com a celula da linha sup
                    tmpUp.down = current;   // ligando a celula da linha sup com a celula atual
                    tmpUp = tmpUp.right;    // avancando para a proxima celula da linha sup
                }
            }
            upperRowStart = newRowStart; // avancando para a proxima linha (era upperRow.down)
        }
    }

   
    public void fillMatrix(int value) {
        cell tmpRow = this.start;

        for (int i = 0; i < this.row; i++) {
            cell tmpCol = tmpRow; // referencia temp para a coluna

            for (int j = 0; j < this.column; j++) {
                if (tmpCol != null) {
                    tmpCol.elemento = value;    // atribuindo o valor a celula
                    tmpCol = tmpCol.right; // avancando para a proxima celula da linha
                }
            }
            if (tmpRow != null) {
                tmpRow = tmpRow.down; // avanacando a linha
            }
        }
    }

   
    public void show() {
        cell tmpRow = this.start;
        
        for (int i = 0; i < this.row; i++) {
            cell tmpCol = tmpRow; // referencia tmp para coluna (declarada)

            for (int j = 0; j < this.column; j++) {
                if (tmpCol != null) {
                    System.out.print(tmpCol.elemento + "\t"); // Usando print + tab
                    tmpCol = tmpCol.right; // avancando para a proxima celula da linha
                }
            }
            System.out.println(); // Pula linha aqui
            
            if (tmpRow != null) {
                tmpRow = tmpRow.down; // avancando a linha
            }
        }
    }


    
    public void diagonalPrincipal() {
        cell tmp = this.start; // Usando o 'start' da classe
        int size = Math.min(this.row, this.column);

        for (int i = 0; i < size; i++) {
            if (tmp != null) {
                System.out.print(tmp.elemento + " ");
                
                if (tmp.right != null) {
                    tmp = tmp.right.down; // Avança na diagonal
                } else {
                    tmp = null; // Encerra se não houver mais diagonal
                }
            }
        }
        System.out.println(); // Pula linha no final
    }


    
    public static void main(String[] args) {
        flexible_matrix matrix = new flexible_matrix(3, 3);
        matrix.fillMatrix(2);
        matrix.show();
        matrix.diagonalPrincipal(); // Mostrando a diagonal
    }
}