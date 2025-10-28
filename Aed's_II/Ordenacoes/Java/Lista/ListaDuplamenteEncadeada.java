package Lista;
// import java.util.Scanner; // (Não estamos a usar o Scanner, por isso pode ser removido)

// CORREÇÃO 1: Removido o 'public' desta classe auxiliar
class cell 
{
    public  int elemento; // -> sera quem vai armazenar dados
    public  cell prox;   // -> referencia para a proxima celula
    public  cell ant;   // -> referencia para a celula anterior


    public cell () // construtor padrao
    {
        this.elemento = 0; 
        this.prox     = null;
        this.ant      = null;
    }

    // ideia voltada somente para numeros positivos
    public cell ( int elemento ) // construtor com assinatura 
    {
        // protecao de valores nulos ou negativos
        if ( elemento  >= 0  )
        { this.elemento = elemento; } 
        
        else 
        { this.elemento   = 0;}

        this.prox     = null;
        this.ant      = null;
    }
    
}


/**
 * Classe principal - O ficheiro DEVE ter este nome: ListaDuplamenteEncadeada.java
 */
public class ListaDuplamenteEncadeada 
{
    public cell first;
    public cell last;


    // construtor padrao
    public ListaDuplamenteEncadeada ( )
    {
        this.first = null;
        this.last  = null;
    }

    // construtor pensado que ja contem um elemento 
    public ListaDuplamenteEncadeada ( int elemento )
    {
        // criando celula com elemento passado 
        cell newCell = new cell ( elemento );
        this.first = newCell; 
        this.last  = newCell;
    }


    public void insertStart ( int elemento)
    {
        cell newCell = new cell ( elemento );
        // condicao que preve caso a lista esteja vazia 
        if (this.first == null ) 
        {
            this.first = newCell;
            this.last  = newCell; 
        }
        // condicao que preve caso ja tenha algum valor
        else 
        {
            // 1. O 'prox' da nova célula aponta para o 'primeiro' antigo.
            newCell.prox     = this.first;
            // 2. O 'ant' (anterior) do 'primeiro' antigo aponta para a nova célula.
            this.first.ant   = newCell;
            // 3. A lista atualiza quem é o seu 'primeiro': é a nova célula.
            this.first       = newCell;
        }
    }


    public void insertEnd ( int elemento )
    {
        cell newCell = new cell ( elemento );
        // condicoa que preve caso da lista esteja vazia
        if (this.first == null )
        {
            this.first = newCell;
            this.last  = newCell;
        }

        else
        {
            // o prox da ultima celula aponta para a nova
            this.last.prox = newCell;
            // o ant da nova celula aponta para a ultima antiga
            newCell.ant   = this.last;
            // a lista atualiza quem e a ultima celula
            this.last     = newCell;
        }
    }


    public void Print ( )
    {
        // tmp receberar o primeiro elemento
        cell tmp = this.first;
        System.out.println("=== My List ===");
        
        // enquanto ele nn for nulo (ter algo dentro dele)
        while (tmp != null )
        {
            // CORREÇÃO 3: Removidas as chaves {} extra
            System.out.println("["+tmp.elemento+"]"); // -> vai printar o elemento
            tmp = tmp.prox;                     // -> tmp recebe o proximo elemento
        }
        // CORREÇÃO 3: Linha movida para FORA do loop 'while'
        System.out.println("===================");
    }

    public void PrintReverse ( )
    {
        // tmp receberar o ultimo elemento
        cell tmp = this.last; // Agora 'this.last' funciona
        System.out.println("=== My List Reversed ===");
        
        // enquanto ele n for nulo (ter algum valor dentro dele)
        while ( tmp != null)
        {
            System.out.println("["+tmp.elemento+"]"); // -> vai printar o elemento
            tmp = tmp.ant;                      // -> tmp recebe o elemento anterior
        }
        System.out.println("=========================");
    }
    
    public void removeStart ( )
    {
        // CORREÇÃO 4: Corrigido o erro de digitação 'fitst' para 'first'
        if ( this.first == null )
        { System.out.println("A lista ja esta vazia...");}

        // condicao que preve caso a lista contenha somente um valor nela 
        else if (this.first == this.last )
        {
            // caso a condicao seja verdadeira, anularemos as referencias
            this.first = null;
            this.last  = null;
        }
        // condicoa caso tenha mais de um elemento na lista
        else 
        {
            // o primeiro recebe o proximo do primeiro
            this.first       = this.first.prox;
            // o ant do novo primeiro recebe nulo
            this.first.ant   = null;
        }
    }


    public void removeEnd ( )
    {
        // condicao que preve caso a lista esteja vazia 
        if ( this.first == null )
        { System.out.println("A lista ja esta vazia...");} 

        // condicao que preve caso a lista contenha somente um valor nela 
        else if ( this.first == this.last )
        {
            // caso a condicao seja verdadeira, anularemos as referencias
            this.first = null;
            this.last  = null;
        }
        // condicao caso tenha mais de um elemento na lista
        else 
        {
            // o ultimo recebe o anterior do ultimo
            this.last       = this.last.ant;
            // o prox do novo ultimo recebe nulo
            this.last.prox = null;
        }
          
    }


    /**
     * MÉTODO MAIN PARA TESTAR TUDO
     */
    public static void main (String[] args)
    {
        System.out.println("--- Iniciando Teste da Lista ---");
        
        // 1. Criar a lista (usando o construtor vazio)
        ListaDuplamenteEncadeada minhaLista = new ListaDuplamenteEncadeada();

        // 2. Adicionar itens
        minhaLista.insertEnd(10);  // Lista: [10]
        minhaLista.insertEnd(20);  // Lista: [10] [20]
        minhaLista.insertStart(5); // Lista: [5] [10] [20]
        
        // 3. Mostrar a lista (normal e reversa)
        minhaLista.Print();
        minhaLista.PrintReverse(); // Agora funciona!

        // 4. Remover itens
        System.out.println("\n--- Testando Remocao ---");
        minhaLista.removeStart(); // Remove o 5. Lista: [10] [20]
        minhaLista.Print();
        
        minhaLista.removeEnd();   // Remove o 20. Lista: [10]
        minhaLista.Print();

        // 5. Testar remocao do último item (Cenário B)
        minhaLista.removeStart(); // Remove o 10. Lista fica vazia.
        minhaLista.Print();

        // 6. Testar remocao da lista vazia (Cenário A)
        minhaLista.removeStart(); // Deve dar a mensagem de erro.
    }
}
// CORREÇÃO 5: Removida a chave '}' extra que estava aqui.