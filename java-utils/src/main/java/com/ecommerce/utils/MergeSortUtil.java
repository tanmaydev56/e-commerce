package com.ecommerce.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class providing a generic implementation of the Merge Sort algorithm.
 * <p>
 * Merge Sort is a divide-and-conquer algorithm that divides the input array into two halves,
 * recursively sorts them, and then merges the two sorted halves. It has a time complexity of
 * O(n log n) in all cases (best, average, and worst), making it efficient for large datasets.
 * </p>
 * <p>
 * This implementation supports sorting any type that implements the {@link Comparable} interface,
 * providing type-safe generic sorting capabilities.
 * </p>
 * <p>
 * <strong>Key Features:</strong>
 * </p>
 * <ul>
 *   <li>Generic implementation supporting any Comparable type</li>
 *   <li>Stable sorting algorithm (preserves relative order of equal elements)</li>
 *   <li>O(n log n) time complexity</li>
 *   <li>O(n) space complexity</li>
 *   <li>Thread-safe (stateless utility methods)</li>
 * </ul>
 * <p>
 * <strong>Example Usage:</strong>
 * </p>
 * <pre>{@code
 * // Sorting integers
 * List<Integer> numbers = Arrays.asList(64, 34, 25, 12, 22, 11, 90);
 * List<Integer> sorted = MergeSortUtil.sort(numbers);
 * System.out.println(sorted); // [11, 12, 22, 25, 34, 64, 90]
 * 
 * // Sorting strings
 * List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
 * List<String> sortedNames = MergeSortUtil.sort(names);
 * System.out.println(sortedNames); // [Alice, Bob, Charlie]
 * 
 * // In-place sorting
 * List<Double> prices = new ArrayList<>(Arrays.asList(99.99, 49.99, 149.99));
 * MergeSortUtil.sortInPlace(prices);
 * System.out.println(prices); // [49.99, 99.99, 149.99]
 * }</pre>
 *
 * @author E-Commerce Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class MergeSortUtil {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * All methods are static and should be accessed through the class name.
     */
    private MergeSortUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    /**
     * Sorts a list of comparable elements using the Merge Sort algorithm.
     * <p>
     * This method creates a new sorted list without modifying the original input list.
     * If you need to sort the list in-place, use {@link #sortInPlace(List)} instead.
     * </p>
     *
     * @param <T>  the type of elements in the list, must implement Comparable
     * @param list the list to be sorted (not modified)
     * @return a new sorted list containing all elements from the input list
     * @throws IllegalArgumentException if the input list is null
     * @see #sortInPlace(List)
     */
    public static <T extends Comparable<T>> List<T> sort(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("Input list cannot be null");
        }

        // Return a copy for empty or single-element lists
        if (list.size() <= 1) {
            return new ArrayList<>(list);
        }

        // Create a copy of the list to avoid modifying the original
        List<T> result = new ArrayList<>(list);
        mergeSort(result, 0, result.size() - 1);
        return result;
    }

    /**
     * Sorts a list of comparable elements in-place using the Merge Sort algorithm.
     * <p>
     * This method modifies the original list directly. The sorting is performed
     * in O(n log n) time with O(n) additional space for the merge operations.
     * </p>
     *
     * @param <T>  the type of elements in the list, must implement Comparable
     * @param list the list to be sorted (modified in-place)
     * @throws IllegalArgumentException if the input list is null
     * @see #sort(List)
     */
    public static <T extends Comparable<T>> void sortInPlace(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("Input list cannot be null");
        }

        if (list.size() <= 1) {
            return;
        }

        mergeSort(list, 0, list.size() - 1);
    }

    /**
     * Recursively performs the merge sort algorithm on a sublist.
     * <p>
     * This is the core recursive method that implements the divide-and-conquer approach:
     * </p>
     * <ol>
     *   <li>Divide: Split the list into two halves</li>
     *   <li>Conquer: Recursively sort each half</li>
     *   <li>Combine: Merge the two sorted halves</li>
     * </ol>
     *
     * @param <T>   the type of elements in the list
     * @param list  the list being sorted
     * @param left  the starting index of the sublist
     * @param right the ending index of the sublist
     */
    private static <T extends Comparable<T>> void mergeSort(List<T> list, int left, int right) {
        if (left < right) {
            // Find the middle point to divide the array into two halves
            int middle = left + (right - left) / 2;

            // Recursively sort the first and second halves
            mergeSort(list, left, middle);
            mergeSort(list, middle + 1, right);

            // Merge the sorted halves
            merge(list, left, middle, right);
        }
    }

    /**
     * Merges two sorted sublists into a single sorted sublist.
     * <p>
     * This method takes two adjacent sorted sublists [left...middle] and [middle+1...right]
     * and merges them into a single sorted sublist. The merge operation is performed in
     * O(n) time where n is the total number of elements in both sublists.
     * </p>
     *
     * @param <T>    the type of elements in the list
     * @param list   the list containing the sublists
     * @param left   the starting index of the first sublist
     * @param middle the ending index of the first sublist
     * @param right  the ending index of the second sublist
     */
    private static <T extends Comparable<T>> void merge(List<T> list, int left, int middle, int right) {
        // Calculate sizes of the two sublists
        int leftSize = middle - left + 1;
        int rightSize = right - middle;

        // Create temporary lists for the left and right sublists
        List<T> leftList = new ArrayList<>(leftSize);
        List<T> rightList = new ArrayList<>(rightSize);

        // Copy data to temporary lists
        for (int i = 0; i < leftSize; i++) {
            leftList.add(list.get(left + i));
        }
        for (int j = 0; j < rightSize; j++) {
            rightList.add(list.get(middle + 1 + j));
        }

        // Merge the temporary lists back into the original list
        int i = 0; // Initial index of left sublist
        int j = 0; // Initial index of right sublist
        int k = left; // Initial index of merged sublist

        while (i < leftSize && j < rightSize) {
            if (leftList.get(i).compareTo(rightList.get(j)) <= 0) {
                list.set(k, leftList.get(i));
                i++;
            } else {
                list.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        // Copy remaining elements of leftList, if any
        while (i < leftSize) {
            list.set(k, leftList.get(i));
            i++;
            k++;
        }

        // Copy remaining elements of rightList, if any
        while (j < rightSize) {
            list.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}
