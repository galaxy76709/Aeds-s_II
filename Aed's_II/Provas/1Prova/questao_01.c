#include <stdio.h>
#include <stdlib.h>


/*

1. Declaração de Variáveis

    int A, B;: Armazenam o número de elementos das sequências SA e SB, respectivamente.

    int *SA, *SB;: São ponteiros. Eles irão "apontar" para o local na memória onde os vetores das 
    sequências serão criados. Usar ponteiros nos permite criar vetores de tamanho variável durante
    a execução do programa.

    int i = 0, j = 0;: São os nossos índices.
    i percorrerá a sequência principal SA e j percorrerá a potencial subsequência SB.

2. Leitura dos Dados de Entrada

    scanf("%d %d", &A, &B);: Lê os dois primeiros números da entrada, que são os tamanhos das sequências.

    SA = (int*) malloc(A * sizeof(int));: Aloca (reserva) um bloco de memória exatamente do
    tamanho necessário para guardar todos os A inteiros da sequência SA.

    SB = (int*) malloc(B * sizeof(int));: Faz o mesmo para a sequência SB.

    if (SA == NULL || SB == NULL) { ... }: Uma verificação de segurança. Se o sistema não conseguir
    alocar a memória, malloc retorna NULL. Este trecho avisa o usuário e encerra o programa para evitar
    erros.

    for (int k = 0; ...): Estes dois laços for são usados para ler os números de cada sequência,
    um por um, e armazená-los nos vetores que acabamos de criar.

3. O Algoritmo Principal

    while (i < A && j < B): Este é o coração do programa. O laço continua executando enquanto
    ainda houver elementos para verificar em SA (condição i < A) e enquanto ainda estivermos procurando
    por elementos de SB (condição j < B).

    if (SA[i] == SB[j]): Compara o elemento atual de SA com o elemento que estamos procurando de SB.

    j++: Se os elementos forem iguais, significa que encontramos uma correspondência!
    Então, avançamos o índice j para começar a procurar o próximo elemento de SB.

    i++: Este comando é executado em todas as iterações do laço, pois,
    independentemente de ter encontrado uma correspondência ou não, sempre
    temos que avançar para o próximo elemento de SA para continuar a busca.

4. Verificação e Saída

    if (j == B): Após o término do laço while, esta é a verificação final. 
    Se o índice j é igual ao tamanho de SB, isso prova que passamos por todos os elementos de SB e
    encontramos correspondências para todos eles na ordem correta.

    printf("S\n");: Imprime 'S' (Sim), indicando que SB é uma subsequência.

    printf("N\n");: Caso contrário, imprime 'N' (Não).

5. Liberação de Memória

    free(SA); free(SB);: Libera a memória que foi alocada com malloc. Isso "devolve"
    a memória para o sistema operacional, uma prática fundamental para evitar "vazamentos de memória"
    (memory leaks) em programas maiores.

    return 0;: Sinaliza ao sistema operacional que o programa foi executado com sucesso.
*/

int main() {
    int A, B;
    int *SA, *SB;
    int i = 0, j = 0;

    scanf("%d %d", &A, &B);

    SA = (int*) malloc(A * sizeof(int));
    SB = (int*) malloc(B * sizeof(int));
    
    if (SA == NULL || SB == NULL) {
        printf("Erro ao alocar memoria.\n");
        return 1;
    }

    for (int k = 0; k < A; k++) {
        scanf("%d", &SA[k]);
    }

    for (int k = 0; k < B; k++) {
        scanf("%d", &SB[k]);
    }

    while (i < A && j < B) {
        if (SA[i] == SB[j]) {
            j++;
        }
        i++;
    }

    if (j == B) {
        printf("S\n");
    } else {
        printf("N\n");
    }

    free(SA);
    free(SB);

    return 0;
}