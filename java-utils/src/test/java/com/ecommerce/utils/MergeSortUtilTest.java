package com.ecommerce.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive test suite for the MergeSortUtil class.
 * Tests various scenarios including edge cases, different data types,
 * and validation of both sort methods.
 */
@DisplayName("MergeSort Utility Tests")
class MergeSortUtilTest {

    @Test
    @DisplayName("Should sort a list of integers in ascending order")
    void testSortIntegersAscending() {
        List<Integer> input = Arrays.asList(64, 34, 25, 12, 22, 11, 90);
        List<Integer> expected = Arrays.asList(11, 12, 22, 25, 34, 64, 90);
        
        List<Integer> result = MergeSortUtil.sort(input);
        
        assertEquals(expected, result);
        // Verify original list is unchanged
        assertEquals(Arrays.asList(64, 34, 25, 12, 22, 11, 90), input);
    }

    @Test
    @DisplayName("Should sort a list of strings alphabetically")
    void testSortStringsAlphabetically() {
        List<String> input = Arrays.asList("Zebra", "Apple", "Mango", "Banana", "Cherry");
        List<String> expected = Arrays.asList("Apple", "Banana", "Cherry", "Mango", "Zebra");
        
        List<String> result = MergeSortUtil.sort(input);
        
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should sort a list of doubles")
    void testSortDoubles() {
        List<Double> input = Arrays.asList(99.99, 49.99, 149.99, 19.99, 79.99);
        List<Double> expected = Arrays.asList(19.99, 49.99, 79.99, 99.99, 149.99);
        
        List<Double> result = MergeSortUtil.sort(input);
        
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should handle empty list")
    void testSortEmptyList() {
        List<Integer> input = new ArrayList<>();
        List<Integer> result = MergeSortUtil.sort(input);
        
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should handle single element list")
    void testSortSingleElement() {
        List<Integer> input = Arrays.asList(42);
        List<Integer> result = MergeSortUtil.sort(input);
        
        assertEquals(Arrays.asList(42), result);
    }

    @Test
    @DisplayName("Should handle already sorted list")
    void testSortAlreadySorted() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        List<Integer> result = MergeSortUtil.sort(input);
        
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should handle reverse sorted list")
    void testSortReverseSorted() {
        List<Integer> input = Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        List<Integer> result = MergeSortUtil.sort(input);
        
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should handle list with duplicate elements")
    void testSortWithDuplicates() {
        List<Integer> input = Arrays.asList(5, 2, 8, 2, 9, 1, 5, 5);
        List<Integer> expected = Arrays.asList(1, 2, 2, 5, 5, 5, 8, 9);
        
        List<Integer> result = MergeSortUtil.sort(input);
        
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should handle list with all identical elements")
    void testSortAllIdentical() {
        List<Integer> input = Arrays.asList(7, 7, 7, 7, 7);
        List<Integer> expected = Arrays.asList(7, 7, 7, 7, 7);
        
        List<Integer> result = MergeSortUtil.sort(input);
        
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should handle negative numbers")
    void testSortNegativeNumbers() {
        List<Integer> input = Arrays.asList(-5, 3, -1, 0, -10, 15, -3);
        List<Integer> expected = Arrays.asList(-10, -5, -3, -1, 0, 3, 15);
        
        List<Integer> result = MergeSortUtil.sort(input);
        
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Should handle large list efficiently")
    void testSortLargeList() {
        List<Integer> input = new ArrayList<>();
        for (int i = 1000; i >= 1; i--) {
            input.add(i);
        }
        
        List<Integer> result = MergeSortUtil.sort(input);
        
        // Verify the list is sorted
        for (int i = 0; i < result.size() - 1; i++) {
            assertTrue(result.get(i) <= result.get(i + 1), 
                "Elements at index " + i + " and " + (i + 1) + " are not in order");
        }
        assertEquals(1000, result.size());
        assertEquals(Integer.valueOf(1), result.get(0));
        assertEquals(Integer.valueOf(1000), result.get(999));
    }

    @Test
    @DisplayName("Should throw exception when input is null")
    void testSortNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            MergeSortUtil.sort(null);
        });
    }

    @Test
    @DisplayName("Should sort in-place and modify original list")
    void testSortInPlace() {
        List<Integer> input = new ArrayList<>(Arrays.asList(64, 34, 25, 12, 22, 11, 90));
        List<Integer> expected = Arrays.asList(11, 12, 22, 25, 34, 64, 90);
        
        MergeSortUtil.sortInPlace(input);
        
        assertEquals(expected, input);
    }

    @Test
    @DisplayName("Should handle empty list for in-place sort")
    void testSortInPlaceEmptyList() {
        List<Integer> input = new ArrayList<>();
        
        assertDoesNotThrow(() -> MergeSortUtil.sortInPlace(input));
        assertTrue(input.isEmpty());
    }

    @Test
    @DisplayName("Should handle single element for in-place sort")
    void testSortInPlaceSingleElement() {
        List<Integer> input = new ArrayList<>(Arrays.asList(42));
        
        MergeSortUtil.sortInPlace(input);
        
        assertEquals(Arrays.asList(42), input);
    }

    @Test
    @DisplayName("Should throw exception when in-place sort input is null")
    void testSortInPlaceNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            MergeSortUtil.sortInPlace(null);
        });
    }

    @Test
    @DisplayName("Should maintain stability (preserve order of equal elements)")
    void testSortStability() {
        // Create a custom comparable class to test stability
        class Item implements Comparable<Item> {
            int value;
            int originalIndex;

            Item(int value, int originalIndex) {
                this.value = value;
                this.originalIndex = originalIndex;
            }

            @Override
            public int compareTo(Item other) {
                return Integer.compare(this.value, other.value);
            }

            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Item item = (Item) obj;
                return value == item.value && originalIndex == item.originalIndex;
            }
        }

        List<Item> input = Arrays.asList(
            new Item(5, 0),
            new Item(2, 1),
            new Item(5, 2),
            new Item(2, 3),
            new Item(5, 4)
        );

        List<Item> result = MergeSortUtil.sort(input);

        // Check that equal elements maintain their relative order
        assertEquals(2, result.get(0).value);
        assertEquals(1, result.get(0).originalIndex); // First 2
        assertEquals(2, result.get(1).value);
        assertEquals(3, result.get(1).originalIndex); // Second 2
        assertEquals(5, result.get(2).value);
        assertEquals(0, result.get(2).originalIndex); // First 5
        assertEquals(5, result.get(3).value);
        assertEquals(2, result.get(3).originalIndex); // Second 5
        assertEquals(5, result.get(4).value);
        assertEquals(4, result.get(4).originalIndex); // Third 5
    }

    @ParameterizedTest
    @MethodSource("provideListsForSorting")
    @DisplayName("Should correctly sort various test cases")
    void testSortParameterized(List<Integer> input, List<Integer> expected) {
        List<Integer> result = MergeSortUtil.sort(input);
        assertEquals(expected, result);
    }

    private static Stream<Arguments> provideListsForSorting() {
        return Stream.of(
            Arguments.of(Arrays.asList(3, 1, 2), Arrays.asList(1, 2, 3)),
            Arguments.of(Arrays.asList(100, 50, 25), Arrays.asList(25, 50, 100)),
            Arguments.of(Arrays.asList(1), Arrays.asList(1)),
            Arguments.of(Arrays.asList(5, 5), Arrays.asList(5, 5)),
            Arguments.of(Arrays.asList(-1, -2, -3), Arrays.asList(-3, -2, -1))
        );
    }

    @Test
    @DisplayName("Should not be instantiable")
    void testUtilityClassNotInstantiable() {
        try {
            var constructor = MergeSortUtil.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
            fail("Expected UnsupportedOperationException to be thrown");
        } catch (Exception e) {
            // When using reflection, the UnsupportedOperationException is wrapped in InvocationTargetException
            assertTrue(e.getCause() instanceof UnsupportedOperationException,
                "Expected UnsupportedOperationException as cause");
            assertEquals("Utility class cannot be instantiated", e.getCause().getMessage());
        }
    }
}
