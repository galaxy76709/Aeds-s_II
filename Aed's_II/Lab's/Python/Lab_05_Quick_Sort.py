import random
import time
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import sys

# aumentar limite de recursao (para evitar erro em casos grandes)
sys.setrecursionlimit(20000)

# --- Implementacoes de QuickSort em Python ---

def quicksort_first(arr, left, right):
    while left < right:
        p = partition_first(arr, left, right)
        if p-left < right-p:
            quicksort_first(arr, left, p-1)
            left = p+1
        else:
            quicksort_first(arr, p+1, right)
            right = p-1

def partition_first(arr, left, right):
    pivot = arr[left]
    i = left + 1
    j = right
    while i <= j:
        while i <= j and arr[i] <= pivot:
            i += 1
        while i <= j and arr[j] > pivot:
            j -= 1
        if i < j:
            arr[i], arr[j] = arr[j], arr[i]
    arr[left], arr[j] = arr[j], arr[left]
    return j

def quicksort_last(arr, left, right):
    while left < right:
        p = partition_last(arr, left, right)
        if p-left < right-p:
            quicksort_last(arr, left, p-1)
            left = p+1
        else:
            quicksort_last(arr, p+1, right)
            right = p-1

def partition_last(arr, left, right):
    pivot = arr[right]
    i = left - 1
    for j in range(left, right):
        if arr[j] <= pivot:
            i += 1
            arr[i], arr[j] = arr[j], arr[i]
    arr[i+1], arr[right] = arr[right], arr[i+1]
    return i+1

def quicksort_random(arr, left, right):
    while left < right:
        p = partition_random(arr, left, right)
        if p-left < right-p:
            quicksort_random(arr, left, p-1)
            left = p+1
        else:
            quicksort_random(arr, p+1, right)
            right = p-1

def partition_random(arr, left, right):
    rand_index = random.randint(left, right)
    arr[rand_index], arr[right] = arr[right], arr[rand_index]
    return partition_last(arr, left, right)

def quicksort_median3(arr, left, right):
    while left < right:
        p = partition_median3(arr, left, right)
        if p-left < right-p:
            quicksort_median3(arr, left, p-1)
            left = p+1
        else:
            quicksort_median3(arr, p+1, right)
            right = p-1

def partition_median3(arr, left, right):
    mid = (left+right)//2
    if arr[left] > arr[mid]:
        arr[left], arr[mid] = arr[mid], arr[left]
    if arr[left] > arr[right]:
        arr[left], arr[right] = arr[right], arr[left]
    if arr[mid] > arr[right]:
        arr[mid], arr[right] = arr[right], arr[mid]
    arr[mid], arr[right] = arr[right], arr[mid]
    return partition_last(arr, left, right)

# --- Funcao para medir tempo ---
def measure_time(sort_func, arr):
    a = arr.copy()
    start = time.time()
    sort_func(a, 0, len(a)-1)
    end = time.time()
    return (end-start)*1000  # em ms

# --- Configuracao dos testes ---
sizes = [100, 1000, 5000]
strategies = {
    "Primeiro": quicksort_first,
    "Último": quicksort_last,
    "Aleatório": quicksort_random,
    "Mediana3": quicksort_median3
}
cases = {
    "Ordenado": lambda n: list(range(n)),
    "Quase Ordenado": lambda n: list(range(n)) if n<10 else list(range(n))[:-10] + random.sample(range(n-10,n),10),
    "Aleatório": lambda n: random.sample(range(n*2), n)
}

# --- Coletar tempos ---
results = []
for size in sizes:
    for case_name, case_gen in cases.items():
        base = case_gen(size)
        for strat_name, strat_func in strategies.items():
            t = measure_time(strat_func, base)
            results.append((size, case_name, strat_name, t))

df = pd.DataFrame(results, columns=["Tamanho", "Cenário", "Estratégia", "Tempo (ms)"])

# --- Mostrar tabela ---
print("\nResultados dos testes:")
print(df)

# --- Gerar graficos ---
for case_name in cases.keys():
    subset = df[df["Cenário"]==case_name]
    plt.figure(figsize=(8,5))
    for strat_name in strategies.keys():
        data = subset[subset["Estratégia"]==strat_name]
        plt.plot(data["Tamanho"], data["Tempo (ms)"], marker='o', label=strat_name)
    plt.title(f"QuickSort - {case_name}")
    plt.xlabel("Tamanho do Array")
    plt.ylabel("Tempo (ms)")
    plt.legend()
    plt.grid(True)
    plt.show()
