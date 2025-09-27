package com.myproject.algorithms;

import java.util.Arrays;
import java.util.Random;

public class Main {

    private static final Random rand = new Random();

    // Генератор случайного массива целых чисел
    private static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(100000); // числа от 0 до 99999
        }
        return arr;
    }

    // Генератор случайных точек
    private static Point[] generateRandomPoints(int n) {
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(rand.nextDouble() * 1000, rand.nextDouble() * 1000);
        }
        return points;
    }

    public static void main(String[] args) {

        System.out.println("=== ПРИМЕРЫ ===\n");

        // ---- MergeSort маленький пример ----
        int[] arr1 = {38, 27, 43, 3, 9, 82, 10};
        MergeSort.recursionDepth = 0;
        MergeSort.comparisons = 0;
        System.out.println("MergeSort - До сортировки: " + Arrays.toString(arr1));
        long startMS = System.nanoTime();
        MergeSort.mergeSort(arr1);
        long endMS = System.nanoTime();
        System.out.println("MergeSort - После сортировки: " + Arrays.toString(arr1));
        System.out.println("MergeSort - Время: " + (endMS - startMS) + " нс");
        System.out.println("MergeSort - Глубина рекурсии: " + MergeSort.recursionDepth);
        System.out.println("MergeSort - Количество сравнений: " + MergeSort.comparisons + "\n");

        // ---- QuickSort маленький пример ----
        int[] arr2 = {12, 4, 5, 6, 7, 3, 1, 15};
        QuickSort.recursionDepth = 0;
        QuickSort.comparisons = 0;
        System.out.println("QuickSort - До сортировки: " + Arrays.toString(arr2));
        long startQS = System.nanoTime();
        QuickSort.sort(arr2);
        long endQS = System.nanoTime();
        System.out.println("QuickSort - После сортировки: " + Arrays.toString(arr2));
        System.out.println("QuickSort - Время: " + (endQS - startQS) + " нс");
        System.out.println("QuickSort - Глубина рекурсии: " + QuickSort.recursionDepth);
        System.out.println("QuickSort - Количество сравнений: " + QuickSort.comparisons + "\n");

        // ---- Deterministic Select маленький пример ----
        int[] arr3 = {7, 10, 4, 3, 20, 15};
        int k = 3; // хотим 3-й по величине элемент
        long startSel = System.nanoTime();
        int kth = DeterministicSelect.select(arr3, k - 1);
        long endSel = System.nanoTime();
        System.out.println("Select - " + k + "-й элемент: " + kth);
        System.out.println("Select - Время: " + (endSel - startSel) + " нс\n");

        // ---- Closest Pair маленький пример ----
        Point[] points = {
                new Point(2.1, 3.2),
                new Point(12.3, 30.4),
                new Point(40.0, 50.1),
                new Point(5.0, 1.0),
                new Point(12.0, 10.0),
                new Point(3.0, 4.0)
        };
        ClosestPair.recursionDepth = 0;
        ClosestPair.comparisons = 0;
        long startCP = System.nanoTime();
        double minDist = ClosestPair.closest(points);
        long endCP = System.nanoTime();
        System.out.println("ClosestPair - минимальное расстояние: " + minDist);
        System.out.println("ClosestPair - Время: " + (endCP - startCP) + " нс");
        System.out.println("ClosestPair - Глубина рекурсии: " + ClosestPair.recursionDepth);
        System.out.println("ClosestPair - Количество сравнений: " + ClosestPair.comparisons + "\n");

        System.out.println("=== МЕТРИКИ ДЛЯ РАЗНЫХ N ===\n");

        int[] sizes = {100, 1000, 5000}; // размеры массивов/точек

        // ---- MergeSort метрики ----
        for (int n : sizes) {
            int[] arr = generateRandomArray(n);
            MergeSort.recursionDepth = 0;
            MergeSort.comparisons = 0;
            long start = System.nanoTime();
            MergeSort.mergeSort(arr);
            long end = System.nanoTime();
            System.out.println("MergeSort, n=" + n +
                    ", time=" + (end - start) + " ns" +
                    ", depth=" + MergeSort.recursionDepth +
                    ", comparisons=" + MergeSort.comparisons);
        }

        // ---- QuickSort метрики ----
        for (int n : sizes) {
            int[] arr = generateRandomArray(n);
            QuickSort.recursionDepth = 0;
            QuickSort.comparisons = 0;
            long start = System.nanoTime();
            QuickSort.sort(arr);
            long end = System.nanoTime();
            System.out.println("QuickSort, n=" + n +
                    ", time=" + (end - start) + " ns" +
                    ", depth=" + QuickSort.recursionDepth +
                    ", comparisons=" + QuickSort.comparisons);
        }

        // ---- Deterministic Select метрики ----
        for (int n : sizes) {
            int[] arr = generateRandomArray(n);
            int kMedian = n / 2;
            long start = System.nanoTime();
            int result = DeterministicSelect.select(arr, kMedian);
            long end = System.nanoTime();
            System.out.println("DeterministicSelect, n=" + n +
                    ", k=" + kMedian +
                    ", value=" + result +
                    ", time=" + (end - start) + " ns");
        }

        // ---- Closest Pair метрики ----
        for (int n : sizes) {
            Point[] pts = generateRandomPoints(n);
            ClosestPair.recursionDepth = 0;
            ClosestPair.comparisons = 0;
            long start = System.nanoTime();
            double distMin = ClosestPair.closest(pts);
            long end = System.nanoTime();
            System.out.println("ClosestPair, n=" + n +
                    ", minDist=" + distMin +
                    ", time=" + (end - start) + " ns" +
                    ", depth=" + ClosestPair.recursionDepth +
                    ", comparisons=" + ClosestPair.comparisons);
        }
    }
}
