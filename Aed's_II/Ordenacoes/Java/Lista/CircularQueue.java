package Lista;

public class CircularQueue {

    private int[] array;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.array = new int[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return this.size;
    }

    public boolean enqueue(int value) {
        if (isFull()) {
            System.out.println("Error: The queue is already full. Unable to add " + value);
            return false;
        }

        array[rear] = value;
        rear = (rear + 1) % capacity;
        size++;
        return true;
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Error: The queue is empty. Cannot remove an element.");
            return -1; // Retorna -1 para indicar erro
        }

        int value = array[front];
        front = (front + 1) % capacity;
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Error: The queue is empty. Cannot peek.");
            return -1; // Retorna -1 para indicar erro
        }
        return array[front];
    }

    public boolean insertAtFront(int value) {
        if (isFull()) {
            System.out.println("Error: The queue is already full. Unable to add " + value + " at the beginning.");
            return false;
        }

        front = (front - 1 + capacity) % capacity;
        array[front] = value;
        size++;
        return true;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("The queue is empty!");
            return;
        }

        System.out.print("Queue: ");
        int i = front;
        for (int count = 0; count < size; count++) {
            System.out.print(array[i] + " ");
            i = (i + 1) % capacity;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("--- Testing the Circular Queue ---");
        CircularQueue fila = new CircularQueue(5);

        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);
        fila.display();

        fila.insertAtFront(5);
        fila.display();

        System.out.println("Removed: " + fila.dequeue());
        fila.display();

        fila.enqueue(40);
        fila.enqueue(50);
        fila.display();

        System.out.println("Is full? " + fila.isFull());
        fila.enqueue(60);

        System.out.println("First element: " + fila.peek());
        System.out.println("Current size: " + fila.size());
        
        System.out.println("\n--- Emptying the queue ---");
        System.out.println("Removed: " + fila.dequeue());
        System.out.println("Removed: " + fila.dequeue());
        System.out.println("Removed: " + fila.dequeue());
        System.out.println("Removed: " + fila.dequeue());
        System.out.println("Removed: " + fila.dequeue());
        
        System.out.println("Trying to remove one more item...");
        System.out.println("Removed: " + fila.dequeue()); // Aqui ele vai imprimir o erro e o valor -1
        
        fila.display();
    }
}