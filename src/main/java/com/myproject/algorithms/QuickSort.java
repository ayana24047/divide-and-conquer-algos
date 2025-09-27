package com.myproject.algorithms;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {
    private static final Random rand = new Random();

    // Счётчики
    public static int recursionDepth = 0;
    public static int comparisons = 0;
    private static int currentDepth = 0;

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int left, int right) {
        currentDepth++;
        recursionDepth = Math.max(recursionDepth, currentDepth);

        while (left < right) {
            int pivotIndex = partition(array, left, right);

            if (pivotIndex - left < right - pivotIndex) {
                quickSort(array, left, pivotIndex - 1);
                left = pivotIndex + 1;
            } else {
                quickSort(array, pivotIndex + 1, right);
                right = pivotIndex - 1;
            }
        }

        currentDepth--;
    }

    private static int partition(int[] array, int left, int right) {
        int pivotIndex = left + rand.nextInt(right - left + 1);
        int pivotValue = array[pivotIndex];
        swap(array, pivotIndex, right);

        int storeIndex = left;
        for (int i = left; i < right; i++) {
            comparisons++; // считаем каждое сравнение
            if (array[i] < pivotValue) {
                swap(array, i, storeIndex);
                storeIndex++;
            }
        }
        swap(array, storeIndex, right);
        return storeIndex;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Для теста
    public static void main(String[] args) {
        int[] arr = {9, 3, 7, 1, 4, 8, 2};
        System.out.println("Before: " + Arrays.toString(arr));
        QuickSort.sort(arr);
        System.out.println("After:  " + Arrays.toString(arr));
        System.out.println("Глубина рекурсии: " + recursionDepth);
        System.out.println("Количество сравнений: " + comparisons);
    }
}
