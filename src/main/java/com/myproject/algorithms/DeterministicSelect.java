package com.myproject.algorithms;

import java.util.Arrays;

public class DeterministicSelect {

    // Счётчики для анализа
    public static int recursionDepth = 0;
    public static int comparisons = 0;
    private static int currentDepth = 0;

    public static int select(int[] arr, int k) {
        currentDepth++;
        recursionDepth = Math.max(recursionDepth, currentDepth);

        if (arr.length == 1) {
            currentDepth--;
            return arr[0];
        }

        int pivot = medianOfMedians(arr);

        int[] less = Arrays.stream(arr).filter(x -> {
            comparisons++;
            return x < pivot;
        }).toArray();

        int[] equal = Arrays.stream(arr).filter(x -> {
            comparisons++;
            return x == pivot;
        }).toArray();

        int[] greater = Arrays.stream(arr).filter(x -> {
            comparisons++;
            return x > pivot;
        }).toArray();

        int result;
        if (k < less.length) {
            result = select(less, k);
        } else if (k < less.length + equal.length) {
            result = pivot;
        } else {
            result = select(greater, k - less.length - equal.length);
        }

        currentDepth--;
        return result;
    }

    private static int medianOfMedians(int[] arr) {
        if (arr.length <= 5) {
            Arrays.sort(arr);
            return arr[arr.length / 2];
        }

        int numMedians = (int) Math.ceil((double) arr.length / 5);
        int[] medians = new int[numMedians];

        for (int i = 0; i < numMedians; i++) {
            int start = i * 5;
            int end = Math.min(start + 5, arr.length);
            int[] group = Arrays.copyOfRange(arr, start, end);
            Arrays.sort(group);
            medians[i] = group[group.length / 2];
        }

        return medianOfMedians(medians);
    }

    // Для теста
    public static void main(String[] args) {
        int[] arr = {12, 3, 5, 7, 4, 19, 26};
        int k = 3;
        int result = select(arr, k - 1);
        System.out.println(k + "-й наименьший элемент: " + result);
        System.out.println("Глубина рекурсии: " + recursionDepth);
        System.out.println("Количество сравнений: " + comparisons);
    }
}
