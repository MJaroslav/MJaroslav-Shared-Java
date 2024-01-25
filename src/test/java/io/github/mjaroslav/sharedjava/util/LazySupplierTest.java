package io.github.mjaroslav.sharedjava.util;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class LazySupplierTest {
    @Test
    void initialize() {
        val supplier = new LazySupplier<>(() -> 1);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        supplier.initialize();
        Assertions.assertTrue(supplier.isInitialized(), "Not initialized after calling initialize");
    }

    @Test
    void get() {
        val expected = 4;
        val supplier = new LazySupplier<>(() -> expected);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        val actual = supplier.get();
        Assertions.assertEquals(expected, actual, "Value not initialized");
    }

    @Test
    void isPresent() {
        var supplier = new LazySupplier<>(() -> true);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        Assertions.assertTrue(supplier.isPresent(), "Existing value isn't present");
        supplier = new LazySupplier<>(() -> null);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        Assertions.assertFalse(supplier.isPresent(), "Not existing value is present");
    }

    @Test
    void isEmpty() {
        var supplier = new LazySupplier<>(() -> true);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        Assertions.assertFalse(supplier.isEmpty(), "Existing value is empty");
        supplier = new LazySupplier<>(() -> null);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        Assertions.assertTrue(supplier.isEmpty(), "Not existing value isn't empty");
    }

    @Test
    void ifPresent() {
        val supplierPresent = new LazySupplier<>(() -> true);
        Assertions.assertFalse(supplierPresent.isInitialized(), "Initialized without calling initialize");
        Assertions.assertThrows(RuntimeException.class, () -> supplierPresent.ifPresent(value -> {
            throw new RuntimeException("Value is present");
        }), "Action not ran for presented value");
        val supplierEmpty = new LazySupplier<>(() -> null);
        Assertions.assertFalse(supplierEmpty.isInitialized(), "Initialized without calling initialize");
        Assertions.assertDoesNotThrow(() -> supplierEmpty.ifPresent(value -> {
            throw new RuntimeException("Value is not present");
        }), "Action ran for not presented value");
    }

    @Test
    void ifPresentOrElse() {
        val supplierPresent = new LazySupplier<>(() -> true);
        Assertions.assertFalse(supplierPresent.isInitialized(), "Initialized without calling initialize");
        Assertions.assertThrows(ClassCastException.class, () -> supplierPresent.ifPresentOrElse(value -> {
            throw new ClassCastException("Value is present");
        }, () -> {
            throw new IllegalArgumentException("Value is present");
        }), "Action not ran for presented value");
        val supplierEmpty = new LazySupplier<>(() -> null);
        Assertions.assertFalse(supplierEmpty.isInitialized(), "Initialized without calling initialize");
        Assertions.assertThrows(IllegalArgumentException.class, () -> supplierEmpty.ifPresentOrElse(value -> {
            throw new ClassCastException("Value is not present");
        }, () -> {
            throw new IllegalArgumentException("Value is not present");
        }), "Action ran for not presented value");
    }

    @Test
    void orElse() {
        var expected = -1;
        var supplier = new LazySupplier<>(() -> -1);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        var actual = supplier.orElse(1);
        Assertions.assertEquals(expected, actual, "Presented value not match");
        supplier = new LazySupplier<>(() -> null);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        expected = 1;
        actual = supplier.orElse(expected);
        Assertions.assertEquals(expected, actual, "Else value not match");
    }

    @Test
    void orElseGet() {
        var expected = -1;
        var supplier = new LazySupplier<>(() -> -1);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        var actual = supplier.orElseGet(() -> 1);
        Assertions.assertEquals(expected, actual, "Presented value not match");
        supplier = new LazySupplier<>(() -> null);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        expected = 1;
        actual = supplier.orElseGet(() -> 1);
        Assertions.assertEquals(expected, actual, "Else value not match");
    }

    @Test
    void orElseThrow() {
        val supplierPresent = new LazySupplier<>(() -> -1);
        Assertions.assertFalse(supplierPresent.isInitialized(), "Initialized without calling initialize");
        Assertions.assertDoesNotThrow(() -> supplierPresent.orElseThrow(), "Throw on present value");
        val supplierEmpty = new LazySupplier<>(() -> null);
        Assertions.assertFalse(supplierEmpty.isInitialized(), "Initialized without calling initialize");
        Assertions.assertThrows(NoSuchElementException.class, supplierEmpty::orElseThrow, "Not throw on empty value");
    }

    @Test
    void OrElseThrow$exception() {
        val supplierPresent = new LazySupplier<>(() -> -1);
        Assertions.assertFalse(supplierPresent.isInitialized(), "Initialized without calling initialize");
        Assertions.assertDoesNotThrow(() -> supplierPresent.orElseThrow(RuntimeException::new), "Throw on present value");
        val supplierEmpty = new LazySupplier<>(() -> null);
        Assertions.assertFalse(supplierEmpty.isInitialized(), "Initialized without calling initialize");
        Assertions.assertThrows(RuntimeException.class, () -> supplierEmpty.orElseThrow(RuntimeException::new),
            "Not throw on empty value");
    }

    @Test
    void stream() {
        val expected = 0;
        var supplier = new LazySupplier<>(() -> expected);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        var stream = supplier.stream();
        val actual = stream.findFirst().orElse(1);
        stream.close();
        Assertions.assertEquals(expected, actual, "Presented value not match");
        supplier = new LazySupplier<>(() -> null);
        stream = supplier.stream();
        Assertions.assertEquals(0, stream.count(), "Not presented value stream isn't empty");
        stream.close();
    }

    @Test
    void isInitialized() {
        var supplier = new LazySupplier<>(() -> 1);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling initialize");
        supplier.initialize();
        Assertions.assertTrue(supplier.isInitialized(), "Not initialized after calling initialize");
        supplier = new LazySupplier<>(() -> null);
        Assertions.assertFalse(supplier.isInitialized(), "Initialized without calling null-value initialize");
        supplier.initialize();
        Assertions.assertTrue(supplier.isInitialized(), "Not initialized after calling null-value initialize");
    }
}
