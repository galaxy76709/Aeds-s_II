package Lista;

public class binare_Tree 
{
    // criacao de cada no da arvore 
    public class node 
    {
        nodeQueueLinked list; 
        node left, right; 

        public node ( int item )
        {
            list = new nodeQueueLinked();
            list.enqueue(item);
            left = right = null;
        }
    }

public class nodeQueue
    {
        int data;
        nodeQueue next;
        nodeQueue prev;

        public nodeQueue ( int item )
        {
            data = item;
            next = null;
            prev = null;
        }
    }

public class nodeQueueLinked 
    {
        public nodeQueue head;
        public nodeQueue tail;
        public int       size;

        public nodeQueueLinked() 
        {
            head = null;
            tail = null;
            size = 0;
        }

        public void enqueue ( int item )
        {
            nodeQueue newNode = new nodeQueue ( item );
            if ( head == null )
            {
                head = newNode;
                tail = newNode;
                size++;
            } 
            else {
                tail.next    = newNode;
                newNode.prev = tail; 
                tail = newNode;
                size++;
            }
        }

        public int dequeue ( )
        {
            if ( head == null )
            {
                throw new IllegalStateException("A fila está vazia!");
            }
            int removeData = head.data;
            head = head.next;
            
            if ( head != null )
            { head.prev = null; }
            else 
            { tail = null; }
            size--;
            return removeData;
        }
        
        public boolean isEmpty ( )
        { return size == 0; }

        public int getSize ( )
        { return size; }

        public void printList()
        {
            nodeQueue current = head;
            while ( current != null )
            {
                System.out.print(current.data + " ");
                current = current.next;
            }
        }
    }
    
    node root; 

    public binare_Tree()
    { 
        root = null; 
    }

    public void insert ( int data )
    { root = insertRec ( root, data); }

    public node insertRec ( node currentNode, int data )
    {
        if ( currentNode == null) 
        { 
            currentNode = new node ( data );  
            return currentNode; 
        }
        
        int currentValue = currentNode.list.head.data;

        if ( data < currentValue ) 
        {
            currentNode.left = insertRec(currentNode.left, data);
        }
        else if ( data > currentValue ) 
        {
            currentNode.right = insertRec(currentNode.right, data);
        }
        else 
        {
            currentNode.list.enqueue(data);
        }

        return currentNode;
    }

    public void inorder() { inorderRec(root); }
    public void postorder() { postorderRec(root); }
    public void preorder() { preorderRec(root); }

public void inorderRec ( node currentNode) 
{
    if ( currentNode != null )
    {
        inorderRec(currentNode.left); 
        System.out.print(" Nó com lista: ");
        currentNode.list.printList();
        System.out.println();
        inorderRec(currentNode.right); 
    }
}

public void postorderRec ( node currentNode ) 
{
    if ( currentNode != null )
    {
        postorderRec( currentNode.left );
        postorderRec(currentNode.right);
        System.out.print(" Nó com lista: ");
        currentNode.list.printList();
        System.out.println();
    }
}

public void preorderRec ( node currentNode ) 
{
    if ( currentNode != null )
    {
        System.out.print(" Nó com lista: ");
        currentNode.list.printList();
        System.out.println();
        preorderRec(currentNode.left);
        preorderRec(currentNode.right);
    }
}

    public boolean search ( int data ) 
    { return searchRec ( root, data); }

    public boolean searchRec ( node currentNode, int data)
    {
        if ( currentNode == null)
        { return false; }

        int currentValue = currentNode.list.head.data;

        if ( currentValue == data )
        { return true; }

        if ( data < currentValue)
        { return searchRec ( currentNode.left, data); }
        else 
        { return searchRec ( currentNode.right, data); }
    } 

    public void delete ( int data )
    { root = deleteRec ( root, data); }

    public node deleteRec ( node currentNode, int data)
    {
        if ( currentNode == null)
        { return currentNode; } 

        int currentValue = currentNode.list.head.data;
        
        if ( data < currentValue )
        { currentNode.left = deleteRec ( currentNode.left, data); }
        else if ( data > currentValue )
        { currentNode.right = deleteRec ( currentNode.right, data); }
        else 
        {
            if (currentNode.left == null && currentNode.right == null)
            { return null; }
            else if (currentNode.left == null)
            { return currentNode.right;  }
            else if (currentNode.right == null)
            { return currentNode.left; }
            else 
            {
                node successor = FindminRec(currentNode.right);
                currentNode.list = successor.list;
                currentNode.right = deleteRec(currentNode.right, successor.list.head.data);
            }
        }
        return currentNode; 
    } 

    public int Findmin ( )
    { 
        if (root == null) {
            throw new IllegalStateException("A árvore está vazia!");
        }
        node minNode = FindminRec ( root ); 
        return minNode.list.head.data; 
    }

    public node FindminRec ( node currentNode )
    {
        if ( currentNode == null || currentNode.left == null)
        {  return currentNode; }
        return FindminRec ( currentNode.left);
    }

    public int Findmax ( )
    { 
        if (root == null) {
            throw new IllegalStateException("A árvore está vazia!");
        }
        node maxNode = FindmaxRec ( root ); 
        return maxNode.list.head.data; 
    }

    public node FindmaxRec ( node currentNode )
    {
        if ( currentNode == null || currentNode.right == null)
        { return currentNode; }

        return FindmaxRec ( currentNode.right );
    }

    public int getHeight( )
    { return getHeightRec ( root); }

    public int getHeightRec ( node currentNode )
    {
        if ( currentNode == null)
        { return -1; }
        
        int leftHeight  = getHeightRec ( currentNode.left);
        int rightHeight = getHeightRec ( currentNode.right);

        return Math.max ( leftHeight, rightHeight) + 1;
    }


   public static void main ( String[] args )
    {
        binare_Tree tree = new binare_Tree();

        System.out.println("==== TESTE 1: INSERÇÃO SIMPLES ====");
        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);
        tree.inorder();

        System.out.println("\n==== TESTE 2: INSERÇÃO REPETIDA (LISTAS NOS NÓS) ====");
        tree.insert(30);
        tree.insert(30);
        tree.insert(70);
        tree.insert(70);
        tree.inorder();

        System.out.println("\n==== TESTE 3: BUSCA ====");
        System.out.println("Busca 30: " + tree.search(30));
        System.out.println("Busca 70: " + tree.search(70));
        System.out.println("Busca 25: " + tree.search(25));
        System.out.println("Busca 90: " + tree.search(90));

        System.out.println("\n==== TESTE 4: ALTURA DA ÁRVORE ====");
        System.out.println("Altura: " + tree.getHeight());

        System.out.println("\n==== TESTE 5: REMOÇÃO DE NÓS ====");
        System.out.println("Removendo nó folha (20)...");
        tree.delete(20);
        tree.inorder();

        System.out.println("\nRemovendo nó com 1 filho (60)...");
        tree.delete(60);
        tree.inorder();

        System.out.println("\nRemovendo nó com 2 filhos (70)...");
        tree.delete(70);
        tree.inorder();

        System.out.println("\n==== TESTE 6: MIN, MAX e ALTURA ====");
        System.out.println("Mínimo: " + tree.Findmin());
        System.out.println("Máximo: " + tree.Findmax());
        System.out.println("Altura atual: " + tree.getHeight());

        System.out.println("\n==== TESTE 7: PERCURSOS COMPLETOS ====");
        System.out.println("\n--- In-Order ---");
        tree.inorder(); 
        System.out.println("\n--- Pré-Order ---");
        tree.preorder();
        System.out.println("\n--- Pós-Order ---");
        tree.postorder();

        System.out.println("\n==== TESTES CONCLUÍDOS ====");
    }
}
