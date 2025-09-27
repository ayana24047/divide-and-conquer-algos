package com.myproject.algorithms;

import java.util.Arrays;
import java.util.Comparator;

public class ClosestPair {

    public static int recursionDepth = 0; // максимальная глубина рекурсии
    public static int comparisons = 0;     // количество сравнений
    private static int currentDepth = 0;

    // Евклидово расстояние
    private static double dist(Point p1, Point p2) {
        comparisons++;
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y));
    }

    // Брутфорс для маленьких наборов
    private static double bruteForce(Point[] points, int n) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                double d = dist(points[i], points[j]);
                if (d < min) min = d;
            }
        }
        return min;
    }

    // Минимум в полосе
    private static double stripClosest(Point[] strip, int size, double d) {
        double min = d;
        Arrays.sort(strip, 0, size, Comparator.comparingDouble(p -> p.y));

        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; j++) {
                double distVal = dist(strip[i], strip[j]);
                if (distVal < min) min = distVal;
            }
        }
        return min;
    }

    private static double closestUtil(Point[] points, int left, int right) {
        currentDepth++;
        recursionDepth = Math.max(recursionDepth, currentDepth);

        double result;
        if (right - left <= 3) {
            result = bruteForce(Arrays.copyOfRange(points, left, right + 1), right - left + 1);
        } else {
            int mid = (left + right) / 2;
            Point midPoint = points[mid];

            double dl = closestUtil(points, left, mid);
            double dr = closestUtil(points, mid + 1, right);
            double d = Math.min(dl, dr);

            Point[] strip = new Point[right - left + 1];
            int j = 0;
            for (int i = left; i <= right; i++) {
                if (Math.abs(points[i].x - midPoint.x) < d) {
                    strip[j++] = points[i];
                }
            }

            result = Math.min(d, stripClosest(strip, j, d));
        }

        currentDepth--;
        return result;
    }

    public static double closest(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
        return closestUtil(points, 0, points.length - 1);
    }
}
