package io.github.mjaroslav.sharedjava.reflect;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class with some hacky methods for reflection.
 *
 * @author MJaroslav
 * @since 0.1.0
 */
@UtilityClass
public class ReflectionHelper {
    /**
     * Get enum by name or return fallback value.
     *
     * @param enumClass    class of specified enum
     * @param enumName     name of specified enum value
     * @param fallbackEnum default value if specified enum can't be taken
     * @param <T>          enum type
     * @return enum with specified name; fallback value when specified can't be taken
     * @since 0.1.0
     */
    @SuppressWarnings("unchecked")
    @Contract("null, _, _ -> param3; _, null, _ -> param3")
    public <T extends Enum<T>> @NotNull T getEnumFromName(@Nullable Class<?> enumClass, @Nullable String enumName,
                                                          @NotNull T fallbackEnum) {
        if (enumClass == null || enumName == null) return fallbackEnum;
        try {
            return Enum.valueOf((Class<T>) enumClass, enumName);
        } catch (IllegalArgumentException e) {
            return fallbackEnum;
        }
    }

    /**
     * Get enum by name.
     *
     * @param enumClass class of specified enum
     * @param enumName  name of specified enum value
     * @param <T>       enum type
     * @return enum with specified name
     * @throws IllegalArgumentException if the specified enum class has no constant with the specified name,
     *                                  or the specified class object does not represent an enum class
     * @since 0.1.0
     */
    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> @NotNull T getEnumFromName(@NotNull Class<?> enumClass, @NotNull String enumName) {
        return Enum.valueOf((Class<T>) enumClass, enumName);
    }

    /**
     * Get simple class name by source (String class name, Class or Object).
     *
     * @param source Class, String or Object for getting name
     * @return simple class name of source
     * @since 0.1.0
     */
    public @NotNull String getSimpleClassName(@NotNull Object source) {
        val className = source instanceof String string ? string :
            source instanceof Class<?> clazz ? clazz.getName() : source.getClass().getName();
        return className.contains(".") ? className.substring(className.lastIndexOf(".") + 1) : className;
    }

    /**
     * Get package by source (String class name, Class or Object).
     * If last part of String starts with lowerCase it's will be package name.
     *
     * @param source Class, String or Object or package getting.
     * @return package of source, empty string for default package.
     * @since 0.1.0
     */
    public @NotNull String getPackage(@NotNull Object source) {
        val name = source instanceof String string ? string :
            source instanceof Class<?> clazz ? clazz.getName() : source.getClass().getName();
        if (name.contains(".")) {
            val lastPartBeginIndex = name.lastIndexOf(".");
            return Character.isLowerCase(name.charAt(lastPartBeginIndex + 1)) ? name : name.substring(0, lastPartBeginIndex);
        } else return Character.isLowerCase(name.charAt(0)) ? name : "";
    }

    /**
     * Find declared field in class and superclasses up to limit of deep.
     *
     * @param target     class for search
     * @param deepLimit  search deep limit of superclasses (exclusive), use null for search up to Object.
     * @param fieldNames field name with synonyms (if you have obfuscated environment)
     * @return field of class or superclasses with specified name
     * @throws UnableToFindFieldException if no field with specified name found
     * @since 0.1.0
     */
    public @NotNull Field findDeepField(@NotNull Class<?> target, @Nullable Class<?> deepLimit,
                                        @NotNull String @NotNull ... fieldNames) throws UnableToFindFieldException {
        Exception failed = null;
        val fields = getDeepDeclaredFields(target, deepLimit);
        for (val fieldName : fieldNames)
            try {
                if (fields.containsKey(fieldName)) {
                    val f = fields.get(fieldName);
                    f.setAccessible(true);
                    return f;
                }
            } catch (Exception e) {
                failed = e;
            }
        throw new UnableToFindFieldException(failed != null ? failed : new NoSuchFieldException());
    }

    /**
     * Get all fields of class with fields of superclasses up to limit of deep.
     *
     * @param target    class for getting fields
     * @param deepLimit deep limit of superclasses (exclusive), use null for get all fields up to Object.
     * @return new HashMap with fields of class and superclasses
     * @since 0.1.0
     */
    @Contract("_, _ -> new")
    public @NotNull Map<String, Field> getDeepDeclaredFields(@NotNull Class<?> target, @Nullable Class<?> deepLimit) {
        val result = new HashMap<String, Field>();
        var currentClass = target;
        do {
            for (val field : currentClass.getDeclaredFields()) result.put(field.getName(), field);
            currentClass = currentClass.getSuperclass();
        } while (currentClass != null && !(currentClass.equals(deepLimit)));
        return result;
    }

    /**
     * Get private value of class or superclasses field up to limit of deep.
     *
     * @param target     class for field getting
     * @param instance   instance of class for value getting
     * @param deepLimit  deep limit of superclasses (exclusive), use null for searching up to Object
     * @param fieldNames field name with synonyms (if you have obfuscated environment)
     * @param <T>        type of specified field, just for cast
     * @param <E>        type of specified class, just for instance
     * @return private value of specified field of class or superclasses
     * @throws UnableToAccessFieldException if field with specified name can't be found
     * @since 0.1.0
     */
    @SuppressWarnings("unchecked")
    public <T, E> T getDeepPrivateValue(@NotNull Class<? super E> target, @Nullable E instance,
                                        @Nullable Class<?> deepLimit,
                                        @NotNull String @NotNull ... fieldNames)
        throws UnableToAccessFieldException {
        try {
            return (T) findDeepField(target, deepLimit, fieldNames).get(instance);
        } catch (Exception e) {
            throw new UnableToAccessFieldException(e);
        }
    }

    /**
     * Set private value of class or superclasses field up to limit of deep.
     *
     * @param target     class for field setting
     * @param instance   instance of class for value setting
     * @param value      new field value
     * @param deepLimit  deep limit of superclasses (exclusive), use null for searching up to Object
     * @param fieldNames field name with synonyms (if you use obfuscated environment)
     * @param <T>        type of specified class, just for instance
     * @throws UnableToAccessFieldException if field with specified name can't be found
     * @since 0.1.0
     */
    public <T> void setDeepPrivateValue(@NotNull Class<? super T> target, @Nullable T instance,
                                        @Nullable Object value, @Nullable Class<?> deepLimit,
                                        @NotNull String @NotNull ... fieldNames)
        throws UnableToAccessFieldException {
        try {
            findDeepField(target, deepLimit, fieldNames).set(instance, value);
        } catch (Exception e) {
            throw new UnableToAccessFieldException(e);
        }
    }

    /**
     * Gets generic type of class by index. Works only with directly declared generics types.
     *
     * @param target class for getting generic type
     * @param index  index of generic type
     * @return generic type class
     * @throws UnableToFindGenericTypeException if no generics with specified index or can't get generics at all
     * @since 0.1.0
     */
    public @NotNull Class<?> getGenericType(@NotNull Class<?> target, @Range(from = 0, to = Integer.MAX_VALUE) int index) {
        try {
            return (Class<?>) ((ParameterizedType) target.getGenericSuperclass()).getActualTypeArguments()[index];
        } catch (Exception e) {
            throw new UnableToFindGenericTypeException(e);
        }
    }
}
