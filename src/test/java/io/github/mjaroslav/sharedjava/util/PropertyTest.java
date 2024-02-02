package io.github.mjaroslav.sharedjava.util;

import lombok.val;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

class PropertyTest {
    @Test
    void getWithValue() {
        val prop = new Property<>(1);
        Assertions.assertEquals(1, prop.get(), "Property value after initialization");
        Assertions.assertNotEquals(-1, prop.get(), "Property value after initialization");
        prop.set(-1);
        Assertions.assertEquals(-1, prop.get(), "Property value after changing value");
        Assertions.assertNotEquals(1, prop.get(), "Property value after changing value");
    }

    @Test
    void getWithConvention() {
        val prop = new Property<Integer>().setConvention(1);
        Assertions.assertEquals(1, prop.get(), "Property value after convention initialization");
        Assertions.assertNotEquals(-1, prop.get(), "Property value after convention initialization");
        prop.setConvention(-1);
        Assertions.assertEquals(-1, prop.get(), "Property value after changing convention");
        Assertions.assertNotEquals(1, prop.get(), "Property value after changing convention");
        prop.set(1);
        Assertions.assertEquals(1, prop.get(), "Property value after changing value");
        Assertions.assertNotEquals(-1, prop.get(), "Property value after changing value");
    }

    @Test
    void getWithConventionProperty() {
        val sourceProp = new Property<>(1);
        val prop = new Property<Integer>().setConventionProperty(sourceProp);
        Assertions.assertEquals(1, prop.get(), "Property value after source property initialization");
        Assertions.assertNotEquals(-1, prop.get(), "Property value after source property initialization");
        sourceProp.set(-1);
        Assertions.assertEquals(-1, prop.get(), "Property value after changing source property");
        Assertions.assertNotEquals(1, prop.get(), "Property value after changing source property");
    }

    @Test
    void registerListener() {
        val prop = new Property<String>();
        Assertions.assertDoesNotThrow(() -> prop.set("1"), "No listeners registered");
        final Consumer<String> listener = newValue -> {
            throw new RuntimeException(newValue);
        };
        prop.registerListener(listener);
        Assertions.assertThrows(RuntimeException.class, () -> prop.set("-1"), "Listener not registered");
        Assertions.assertDoesNotThrow(() -> prop.set("-1"), "Listener runs when nothing changed");
        val sourceProp = new Property<>("1");
        Assertions.assertThrows(RuntimeException.class, () -> prop.setConventionProperty(sourceProp), "Source property with different value");
        Assertions.assertThrows(RuntimeException.class, () -> sourceProp.set("-1"), "Changing source property value");
        Assertions.assertDoesNotThrow(() -> sourceProp.set("-1"), "Listener runs when nothing changed");
        prop.unregisterListener(listener);
        Assertions.assertDoesNotThrow(() -> sourceProp.set("1"), "Source property listener not unregistered");
        Assertions.assertDoesNotThrow(() -> sourceProp.setConventionProperty(null), "Source property Listener not unregistered");
        Assertions.assertDoesNotThrow(() -> prop.set("-1"), "Listener not unregistered");
    }

    @Test
    void equalsAndHashCode() {
        EqualsVerifier.simple().forClass(Property.class).withPrefabValues(Property.class,
                new Property<>().set(""), new Property<>().set(1))
            .withOnlyTheseFields("value", "convention", "conventionProperty").verify();
    }
}
