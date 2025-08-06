#include "io.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

//---------------------
void method_00(void)
{
  // não faz nada
}

//---------------------
void swap(int *a, int *b)
{
  int temp = *a;
  *a = *b;
  *b = temp;
}

//---------------------
void bubbleSortIncreasing(int array[], int length)
{
  if (length > 0 && array != NULL)
  {
    for (int i = 0; i < length - 1; i++)
    {
           for (int j = 0; j < length - i - 1; j++)
           {
                if (array[j] > array[j + 1])
                {
                  swap(&array[j], &array[j + 1]);
                }
           }
    }
  }
}

void bubbleSortDecreasing(int array[], int length)
{
  if (length > 0 && array != NULL)
  {
    for (int i = 0; i < length - 1; i++)
    {
           for (int j = 0; j < length - i - 1; j++)
           {
                if (array[j] < array[j + 1])
                {
                  swap(&array[j], &array[j + 1]);
                }
           }
    }
  }
}

//---------------------
void printArray(int array[], int length)
{
  for (int i = 0; i < length; i++)
  {
    IO_printf("%d ", array[i]);
  }
  IO_printf("\n");
}

//---------------------
void setArray(int array[], int length)
{
  if (length > 0)
  {
    for (int i = 0; i < length; i++)
    {
           printf("Digite o valor da posicao [%d]: ", i + 1);
           scanf("%d", &array[i]);
    }
  }
  else
  {
    IO_printf("Erro: tamanho do arranjo invalido.\n");
  }
}

//---------------------
void saveArrayToFile(const char *filename, int array[], int length)
{
  FILE *file = fopen(filename, "w");
  if (file != NULL)
  {
    fprintf(file, "%d\n", length);
    for (int i = 0; i < length; i++)
    {
           fprintf(file, "%d\n", array[i]);
    }
    fclose(file);
    IO_printf("Arquivo '%s' salvo com sucesso.\n", filename);
  }
  else
  {
    IO_printf("Erro ao abrir o arquivo '%s'.\n", filename);
  }
}

//---------------------
int loadArrayFromFile(const char *filename, int **array)
{
  FILE *file = fopen(filename, "r");
  int length = 0;

  if (file != NULL)
  {
    fscanf(file, "%d", &length);
    *array = (int *)malloc(length * sizeof(int));
    if (*array == NULL)
    {
           IO_printf("Erro ao alocar memória.\n");
           fclose(file);
           return 0;
    }

    for (int i = 0; i < length; i++)
    {
           fscanf(file, "%d", &(*array)[i]);
    }

    fclose(file);
    IO_printf("Arquivo '%s' carregado com sucesso.\n", filename);
  }
  else
  {
    IO_printf("Erro ao abrir arquivo '%s'.\n", filename);
  }

  return length;
}

//---------------------
void method_01(void)
{
  IO_id("Method_01 - Bubble Sort crescente");

  int length = IO_readint("Digite o tamanho do arranjo: ");
  int *array = (int *)malloc(length * sizeof(int));

  if (array == NULL)
  {
    IO_println("Erro ao alocar memória.");
    return;
  }

  setArray(array, length);
  IO_printf("Array original: ");
  printArray(array, length);

  bubbleSortIncreasing(array, length);
  IO_printf("Array ordenado crescente: ");
  printArray(array, length);

  saveArrayToFile("ordenado_crescente.txt", array, length);

  free(array);
  IO_pause("Apertar ENTER para continuar");
}

//---------------------
void method_02(void)
{
  IO_id("Method_02 - Bubble Sort decrescente de arquivo");

  char filename[100];
  IO_print("Digite o nome do arquivo de entrada: ");
  scanf("%s", filename);

  int *array = NULL;
  int length = loadArrayFromFile(filename, &array);

  if (length > 0 && array != NULL)
  {
    IO_printf("Array lido do arquivo: ");
    printArray(array, length);

    bubbleSortDecreasing(array, length);
    IO_printf("Array ordenado decrescente: ");
    printArray(array, length);

    saveArrayToFile("ordenado_decrescente.txt", array, length);
    free(array);
  }

  IO_pause("Apertar ENTER para continuar");
}

//---------------------
int main()
{
  int x = 0;

  do
  {
    IO_id("Menu - Ordenação de Arrays");

    IO_println("Opções:");
    IO_println(" 0 - Parar");
    IO_println(" 1 - Ordenar array crescente (teclado)");
    IO_println(" 2 - Ordenar array decrescente (arquivo)");

    x = IO_readint("Escolha uma opção: ");

    switch (x)
    {
           case 0:
                method_00();
                break;
           case 1:
                method_01();
                break;
           case 2:
                method_02();
                break;
           default:
                IO_pause(IO_concat("Valor inválido (0 a 2): ", IO_toString_d(x)));
    }

  } while (x != 0);

  IO_pause("Apertar ENTER para terminar");
  return 0;
}
