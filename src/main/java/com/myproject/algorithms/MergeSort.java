package com.myproject.algorithms;

import java.util.Arrays;

public class MergeSort {

    // Счетчики
    public static int recursionDepth = 0;
    public static int comparisons = 0;
    private static int currentDepth = 0;

    // Метод для сортировки массива
    public static void mergeSort(int[] arr) {
        currentDepth++;
        recursionDepth = Math.max(recursionDepth, currentDepth);

        if (arr.length > 1) {
            int mid = arr.length / 2;

            // Левая и правая половина
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            // Рекурсивно сортируем половины
            mergeSort(left);
            mergeSort(right);

            // Сливаем обратно
            merge(arr, left, right);
        }

        currentDepth--;
    }

    private static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            comparisons++; // считаем каждое сравнение
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // Для теста
    public static void main(String[] args) {
        int[] numbers = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("До сортировки: " + Arrays.toString(numbers));
        mergeSort(numbers);
        System.out.println("После сортировки: " + Arrays.toString(numbers));

        System.out.println("Глубина рекурсии: " + recursionDepth);
        System.out.println("Количество сравнений: " + comparisons);
    }
}
