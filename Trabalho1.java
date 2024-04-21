
package com.mycompany.trabalho1;

import java.util.Arrays;
import java.util.Scanner;

public class Trabalho1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Menu de Opções:");
            System.out.println("1. Ordenação por Inserção");
            System.out.println("2. Ordenação por Seleção");
            System.out.println("3. Ordenação Bolha");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    performSorting("Ordenação por Inserção", SortingAlgorithms::insertionSort);
                    break;
                case 2:
                    performSorting("Ordenação por Seleção", SortingAlgorithms::selectionSort);
                    break;
                case 3:
                    performSorting("Ordenação Bolha", SortingAlgorithms::bubbleSort);
                    break;
                case 4:
                    exit = true;
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void performSorting(String sortingType, SortingFunction sortingFunction) {
        int[] originalArray = InputHandler.getIntegerArrayInput();
        int[] array = Arrays.copyOf(originalArray, originalArray.length);
        long startTime = System.nanoTime();
        sortingFunction.sort(array);
        long endTime = System.nanoTime();

        System.out.println("Vetor original: " + arrayToString(originalArray));
        System.out.println("Vetor ordenado pelo método de " + sortingType + ": " + arrayToString(array));
        System.out.println("Tempo de execução: " + (endTime - startTime) + " nanosegundos.");
    }

    private static String arrayToString(int[] array) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < array.length; i++) {
            builder.append(array[i]);
            if (i < array.length - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    interface SortingFunction {
        void sort(int[] array);
    }
}

class InputHandler {
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] getIntegerArrayInput() {
        System.out.print("Digite o tamanho do vetor: ");
        int size = scanner.nextInt();

        int[] array = new int[size];
        System.out.println("Digite os elementos do vetor:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }
}

class SortingAlgorithms {
    public static void insertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}

