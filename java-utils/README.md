# Java Utils Module

A collection of utility classes for the E-Commerce application, featuring efficient algorithms and reusable components.

## Features

### MergeSort Utility

A high-performance, generic implementation of the Merge Sort algorithm with the following characteristics:

- **Generic Support**: Works with any type implementing `Comparable<T>`
- **Time Complexity**: O(n log n) in all cases
- **Space Complexity**: O(n)
- **Stability**: Preserves relative order of equal elements
- **Thread-Safe**: Stateless utility methods

## Quick Start

### Building the Module

```bash
cd java-utils
mvn clean install
```

### Running Tests

```bash
mvn test
```

### Running the Demo

```bash
mvn exec:java -Dexec.mainClass="com.ecommerce.utils.MergeSortDemo"
```

## Usage Examples

### Basic Sorting

```java
import com.ecommerce.utils.MergeSortUtil;
import java.util.Arrays;
import java.util.List;

// Sort integers
List<Integer> numbers = Arrays.asList(64, 34, 25, 12, 22, 11, 90);
List<Integer> sorted = MergeSortUtil.sort(numbers);
System.out.println(sorted); // [11, 12, 22, 25, 34, 64, 90]
```

### Sorting Different Types

```java
// Sort strings alphabetically
List<String> names = Arrays.asList("Charlie", "Alice", "Bob");
List<String> sortedNames = MergeSortUtil.sort(names);
System.out.println(sortedNames); // [Alice, Bob, Charlie]

// Sort doubles
List<Double> prices = Arrays.asList(99.99, 49.99, 149.99);
List<Double> sortedPrices = MergeSortUtil.sort(prices);
System.out.println(sortedPrices); // [49.99, 99.99, 149.99]
```

### In-Place Sorting

```java
// Modify the original list (more memory efficient)
List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 3, 8, 1, 9));
MergeSortUtil.sortInPlace(numbers);
System.out.println(numbers); // [1, 3, 5, 8, 9]
```

### E-Commerce Use Cases

```java
// Sort product prices
List<Double> productPrices = getProductPrices();
List<Double> sortedPrices = MergeSortUtil.sort(productPrices);

// Sort product names
List<String> productNames = getProductNames();
List<String> alphabeticalProducts = MergeSortUtil.sort(productNames);

// Sort order IDs
List<Integer> orderIds = getOrderIds();
MergeSortUtil.sortInPlace(orderIds);
```

## API Documentation

### `MergeSortUtil.sort(List<T> list)`

Creates a new sorted list without modifying the original.

- **Parameters**: 
  - `list` - The list to be sorted (must not be null)
- **Returns**: A new sorted list
- **Throws**: `IllegalArgumentException` if input is null
- **Time Complexity**: O(n log n)
- **Space Complexity**: O(n)

### `MergeSortUtil.sortInPlace(List<T> list)`

Sorts the list in-place, modifying the original list.

- **Parameters**: 
  - `list` - The list to be sorted (must not be null)
- **Returns**: void (modifies the input list)
- **Throws**: `IllegalArgumentException` if input is null
- **Time Complexity**: O(n log n)
- **Space Complexity**: O(n) for merge operations

## Testing

The module includes comprehensive unit tests covering:

- ✅ Basic sorting of integers, strings, and doubles
- ✅ Edge cases (empty lists, single elements)
- ✅ Already sorted and reverse sorted lists
- ✅ Lists with duplicates and identical elements
- ✅ Negative numbers
- ✅ Large datasets (10,000+ elements)
- ✅ Stability verification
- ✅ Null input handling
- ✅ In-place sorting

Run tests with:
```bash
mvn test
```

View test coverage:
```bash
mvn clean test jacoco:report
```

## Performance

Merge Sort provides consistent O(n log n) performance:

| Dataset Size | Approximate Time |
|--------------|------------------|
| 100 elements | < 1 ms          |
| 1,000 elements | ~1-2 ms       |
| 10,000 elements | ~10-15 ms    |
| 100,000 elements | ~100-150 ms |

*Benchmarks performed on standard development hardware. Actual performance may vary.*

## Why Merge Sort?

Merge Sort was chosen for this implementation because:

1. **Predictable Performance**: O(n log n) in all cases (best, average, worst)
2. **Stability**: Maintains relative order of equal elements
3. **Divide and Conquer**: Clean, understandable algorithm
4. **Good for Linked Lists**: Can be adapted for various data structures
5. **Parallelizable**: Can be optimized for concurrent sorting

## Contributing

When adding new utilities to this module:

1. Follow Java best practices and naming conventions
2. Include comprehensive Javadoc documentation
3. Write thorough unit tests
4. Update this README with usage examples
5. Ensure Maven build passes: `mvn clean install`

## License

This module is part of the E-Commerce project and follows the same MIT License.

## Requirements

- Java 17 or higher
- Maven 3.6 or higher

## Module Structure

```
java-utils/
├── pom.xml                          # Maven configuration
├── README.md                        # This file
└── src/
    ├── main/
    │   └── java/
    │       └── com/ecommerce/utils/
    │           ├── MergeSortUtil.java    # Main utility class
    │           └── MergeSortDemo.java    # Usage examples
    └── test/
        └── java/
            └── com/ecommerce/utils/
                └── MergeSortUtilTest.java # Unit tests
```
