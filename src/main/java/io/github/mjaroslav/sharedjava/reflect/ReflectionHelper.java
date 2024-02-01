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

@UtilityClass
public class ReflectionHelper {
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

    @SuppressWarnings("unchecked")
    public <T extends Enum<T>> @NotNull T getEnumFromName(@NotNull Class<?> enumClass, @NotNull String enumName) {
        return Enum.valueOf((Class<T>) enumClass, enumName);
    }

    public @NotNull String getSimpleClassName(@NotNull Object source) {
        val className = source instanceof String string ? string :
            source instanceof Class<?> clazz ? clazz.getName() : source.getClass().getName();
        return className.contains(".") ? className.substring(className.lastIndexOf(".") + 1) : className;
    }

    public @NotNull String getPackage(@NotNull Object source) {
        val className = source instanceof String string ? string :
            source instanceof Class<?> clazz ? clazz.getName() : source.getClass().getName();
        return className.contains(".") ? className.substring(0, className.lastIndexOf(".")) : "";
    }

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

    public @NotNull Map<String, Field> getDeepDeclaredFields(@NotNull Class<?> target, @Nullable Class<?> deepLimit) {
        val result = new HashMap<String, Field>();
        var currentClass = target;
        do {
            for (val field : currentClass.getDeclaredFields()) result.put(field.getName(), field);
            currentClass = currentClass.getSuperclass();
        } while (currentClass != null && !(currentClass.equals(deepLimit)));
        return result;
    }

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

    public <T, E> void setDeepPrivateValue(@NotNull Class<? super T> target, @Nullable T instance,
                                           @Nullable E value, @Nullable Class<?> deepLimit,
                                           @NotNull String @NotNull ... fieldNames)
        throws UnableToAccessFieldException {
        try {
            findDeepField(target, deepLimit, fieldNames).set(instance, value);
        } catch (Exception e) {
            throw new UnableToAccessFieldException(e);
        }
    }

    public @NotNull Class<?> getGenericType(@NotNull Class<?> target, @Range(from = 0, to = Integer.MAX_VALUE) int index) {
        try {
            return (Class<?>) ((ParameterizedType) target.getGenericSuperclass()).getActualTypeArguments()[index];
        } catch (Exception e) {
            throw new UnableToFindGenericTypeException(e);
        }
    }
}
