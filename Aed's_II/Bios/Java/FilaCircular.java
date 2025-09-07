package Bios.Java;

public class FilaCircular {

    private int[] data;
    private int head; // Aponta para o início da filas
    private int tail; // Aponta para o final da filas
    private int size; // Tamanho atual da fila
    private int capacity; // Capacidade máxima da fila

    /**
     * Construtor para inicializar a fila circular.
     * @param k O tamanho máximo da fila.
     */
    public FilaCircular(int k) {
        capacity = k;
        data = new int[k];
        head = -1;
        tail = -1;
        size = 0;
    }

    /**
     * Insere um elemento na fila circular. Retorna true se a operação for bem-sucedida.
     * @param value O valor a ser inserido.
     * @return boolean Retorna true em caso de sucesso, false caso contrário.
     */
    public boolean enqueue(int value) {
        if (isFull()) {
            System.out.println("A fila está cheia. Não é possível adicionar o elemento.");
            return false;
        }
        if (isEmpty()) {
            head = 0;
        }
        tail = (tail + 1) % capacity;
        data[tail] = value;
        size++;
        return true;
    }

    /**
     * Remove um elemento da fila circular. Retorna true se a operação for bem-sucedida.
     * @return boolean Retorna true em caso de sucesso, false caso contrário.
     */
    public boolean dequeue() {
        if (isEmpty()) {
            System.out.println("A fila está vazia. Não é possível remover o elemento.");
            return false;
        }
        if (head == tail) {
            // A fila se tornará vazia após esta operação
            head = -1;
            tail = -1;
        } else {
            head = (head + 1) % capacity;
        }
        size--;
        return true;
    }

    /**
     * Obtém o elemento da frente da fila.
     * @return int Retorna o elemento da frente ou -1 se a fila estiver vazia.
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return data[head];
    }

    /**
     * Obtém o último elemento da fila.
     * @return int Retorna o último elemento ou -1 se a fila estiver vazia.
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return data[tail];
    }

    /**
     * Verifica se a fila circular está vazia.
     * @return boolean Retorna true se estiver vazia, false caso contrário.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Verifica se a fila circular está cheia.
     * @return boolean Retorna true se estiver cheia, false caso contrário.
     */
    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        FilaCircular fila = new FilaCircular(5);
        fila.enqueue(1);
        fila.enqueue(2);
        fila.enqueue(3);
        fila.enqueue(4);
        fila.enqueue(5);

        System.out.println("Elemento da frente: " + fila.getFront()); // Saída: 1
        System.out.println("Último elemento: " + fila.getRear());   // Saída: 5

        fila.dequeue();
        fila.enqueue(6);

        System.out.println("Elemento da frente após dequeue e enqueue: " + fila.getFront()); // Saída: 2
        System.out.println("Último elemento após dequeue e enqueue: " + fila.getRear());   // Saída: 6

        System.out.println("A fila está cheia? " + fila.isFull()); // Saída: true
    }
}