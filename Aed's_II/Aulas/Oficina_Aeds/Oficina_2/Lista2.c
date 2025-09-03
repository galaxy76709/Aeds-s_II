#include <stdio.h> 
#define MAX_EMERGENCY 10
#define MAX_PERIODIC 20
#define MAX_BACKGROUND 30


/*
Pilha Estática (Para tarefas de tempo real)
•Implemente com array de tamanho fixo
•Operações: push_emergency(), pop_emergency()


Fila Circular (Para tarefas periódicas)
•Implemente com array e índices circulares
•Operações: enqueue_periodic(), dequeue_periodic()


Lista Ordenada (Para tarefas de background)
•Mantenha ordenada por prioridade (0 = mais alta, 10 = mais baixa)
•Operações: insert_background(), remove_background(), get_highest_priority()
Observação: seu código não está restrito somente às funções sugeridas!

*/


 typedef struct {
    int task_id;
    int priority; // 0-10 (0 = maxima prioridade)
 } Task;

 /*
 Que retorna a próxima tarefa a ser executada seguindo as regras:
•Sempre verifica primeiro a pilha de emergência
•Depois a fila circular de tarefas periódicas
•Finalmente a lista de tarefas de background
 */
Task get_next_task ()


 Task push_emerpush_emergency





/*

void promote_to_emergency(int task_id);


void print_system_stats ();   


int main ( )
{

}


*/
