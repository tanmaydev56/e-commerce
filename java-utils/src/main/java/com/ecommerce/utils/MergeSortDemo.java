package com.ecommerce.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Demonstration class showing various use cases of the MergeSortUtil.
 * <p>
 * This class provides practical examples of how to use the MergeSort utility
 * in different scenarios, including sorting different data types and handling
 * real-world use cases in an e-commerce context.
 * </p>
 * <p>
 * To run this demo:
 * </p>
 * <pre>{@code
 * cd java-utils
 * mvn clean compile
 * mvn exec:java -Dexec.mainClass="com.ecommerce.utils.MergeSortDemo"
 * }</pre>
 *
 * @author E-Commerce Team
 * @version 1.0.0
 */
public class MergeSortDemo {

    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("MergeSort Utility - Sample Usage Demonstration");
        System.out.println("=".repeat(70));
        System.out.println();

        // Example 1: Sorting product prices
        demonstrateProductPriceSorting();

        // Example 2: Sorting product names alphabetically
        demonstrateProductNameSorting();

        // Example 3: Sorting customer ratings
        demonstrateRatingSorting();

        // Example 4: In-place sorting for memory efficiency
        demonstrateInPlaceSorting();

        // Example 5: Sorting large dataset
        demonstrateLargeDatasetSorting();

        // Example 6: Handling edge cases
        demonstrateEdgeCases();

        System.out.println("=".repeat(70));
        System.out.println("Demo completed successfully!");
        System.out.println("=".repeat(70));
    }

    /**
     * Demonstrates sorting product prices in an e-commerce scenario.
     */
    private static void demonstrateProductPriceSorting() {
        System.out.println("Example 1: Sorting Product Prices");
        System.out.println("-".repeat(70));

        List<Double> prices = Arrays.asList(99.99, 49.99, 149.99, 19.99, 79.99, 129.99, 39.99);
        System.out.println("Original prices: " + prices);

        List<Double> sortedPrices = MergeSortUtil.sort(prices);
        System.out.println("Sorted prices:   " + sortedPrices);

        System.out.println("✓ Lowest price: $" + sortedPrices.get(0));
        System.out.println("✓ Highest price: $" + sortedPrices.get(sortedPrices.size() - 1));
        System.out.println();
    }

    /**
     * Demonstrates sorting product names alphabetically.
     */
    private static void demonstrateProductNameSorting() {
        System.out.println("Example 2: Sorting Product Names Alphabetically");
        System.out.println("-".repeat(70));

        List<String> products = Arrays.asList(
            "Wireless Mouse",
            "Laptop Stand",
            "USB Cable",
            "Keyboard",
            "Monitor",
            "Headphones",
            "Webcam"
        );

        System.out.println("Original order:");
        products.forEach(p -> System.out.println("  - " + p));

        List<String> sortedProducts = MergeSortUtil.sort(products);
        System.out.println("\nAlphabetically sorted:");
        sortedProducts.forEach(p -> System.out.println("  - " + p));
        System.out.println();
    }

    /**
     * Demonstrates sorting customer ratings.
     */
    private static void demonstrateRatingSorting() {
        System.out.println("Example 3: Sorting Customer Ratings");
        System.out.println("-".repeat(70));

        List<Integer> ratings = Arrays.asList(5, 3, 4, 2, 5, 4, 1, 5, 3, 4);
        System.out.println("Customer ratings: " + ratings);

        List<Integer> sortedRatings = MergeSortUtil.sort(ratings);
        System.out.println("Sorted ratings:   " + sortedRatings);

        // Calculate average rating
        double average = sortedRatings.stream()
            .mapToInt(Integer::intValue)
            .average()
            .orElse(0.0);
        System.out.println("✓ Average rating: " + String.format("%.1f", average) + " stars");
        System.out.println();
    }

    /**
     * Demonstrates in-place sorting for memory efficiency.
     */
    private static void demonstrateInPlaceSorting() {
        System.out.println("Example 4: In-Place Sorting (Memory Efficient)");
        System.out.println("-".repeat(70));

        List<Integer> orderIds = new ArrayList<>(Arrays.asList(1052, 1001, 1089, 1034, 1012, 1067));
        System.out.println("Order IDs before sorting: " + orderIds);

        MergeSortUtil.sortInPlace(orderIds);
        System.out.println("Order IDs after sorting:  " + orderIds);
        System.out.println("✓ Same list object modified in-place");
        System.out.println();
    }

    /**
     * Demonstrates sorting a large dataset efficiently.
     */
    private static void demonstrateLargeDatasetSorting() {
        System.out.println("Example 5: Sorting Large Dataset");
        System.out.println("-".repeat(70));

        // Generate a large list of random numbers
        int size = 10000;
        List<Integer> largeList = new ArrayList<>(size);
        for (int i = size; i > 0; i--) {
            largeList.add(i);
        }

        System.out.println("Sorting " + size + " elements...");
        long startTime = System.nanoTime();
        List<Integer> sortedList = MergeSortUtil.sort(largeList);
        long endTime = System.nanoTime();

        double durationMs = (endTime - startTime) / 1_000_000.0;
        System.out.println("✓ Sorted in " + String.format("%.2f", durationMs) + " ms");
        System.out.println("✓ First element: " + sortedList.get(0));
        System.out.println("✓ Last element: " + sortedList.get(sortedList.size() - 1));
        System.out.println("✓ Time complexity: O(n log n)");
        System.out.println();
    }

    /**
     * Demonstrates handling edge cases.
     */
    private static void demonstrateEdgeCases() {
        System.out.println("Example 6: Handling Edge Cases");
        System.out.println("-".repeat(70));

        // Empty list
        List<Integer> emptyList = new ArrayList<>();
        System.out.println("Empty list: " + emptyList);
        List<Integer> sortedEmpty = MergeSortUtil.sort(emptyList);
        System.out.println("Sorted:     " + sortedEmpty);

        // Single element
        List<Integer> singleElement = Arrays.asList(42);
        System.out.println("\nSingle element: " + singleElement);
        List<Integer> sortedSingle = MergeSortUtil.sort(singleElement);
        System.out.println("Sorted:         " + sortedSingle);

        // Duplicate elements
        List<Integer> withDuplicates = Arrays.asList(5, 2, 5, 1, 2, 5);
        System.out.println("\nWith duplicates: " + withDuplicates);
        List<Integer> sortedDuplicates = MergeSortUtil.sort(withDuplicates);
        System.out.println("Sorted:          " + sortedDuplicates);

        // Negative numbers
        List<Integer> withNegatives = Arrays.asList(-5, 3, -1, 0, -10);
        System.out.println("\nWith negatives: " + withNegatives);
        List<Integer> sortedNegatives = MergeSortUtil.sort(withNegatives);
        System.out.println("Sorted:         " + sortedNegatives);

        System.out.println("\n✓ All edge cases handled correctly");
        System.out.println();
    }
}
