package io.github.mjaroslav.sharedjava.io;

import lombok.experimental.UtilityClass;
import lombok.val;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnknownNullability;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.stream.Stream;

// TODO: Rewrite all docs

/**
 * Set of wrappers for getting and using {@link Path} as "path/filename/file" parameters in other places.
 */
@UtilityClass
public class PathFiles {
    /**
     * Wrapper for {@link Paths#get(String, String...)} that use {@link PathFiles#normalizePath(Object)}
     * as first parameter.
     *
     * @return Normalized and absolute {@link Path} from first parameter with resolved children or
     * null if type of first parameter not supported in {@link PathFiles#normalizePath(Object)}.
     * @see Paths#get(String, String...)
     * @see PathFiles#normalizePath(Object)
     */
    public Path get(@NotNull Object value, @NotNull String @NotNull ... children) {
        var result = normalizePath(value);
        if (result == null) return null;
        for (var child : children) result = result.resolve(child);
        return result.normalize().toAbsolutePath();
    }

    /**
     * Get simple name of file without extension.
     */
    public @NotNull String removeExtension(@NotNull Path path) {
        val name = path.toAbsolutePath().normalize().getFileName().toString();
        val dotIndex = name.lastIndexOf(".");
        return dotIndex > 0 ? name.substring(0, dotIndex) : name;
    }

    /**
     * Replaces file extension.
     *
     * @param file         file for extension replacing.
     * @param newExtension new file extension.
     * @return old extension or null on error when replacing.
     */
    public @Nullable String replaceExtension(@NotNull Path file, @NotNull String newExtension) {
        val ext = getExtension(file);
        if (move(file, get(file.toAbsolutePath().normalize().getParent(),
            removeExtension(file) + "." + newExtension)) == null) return null;
        return ext;
    }

    /**
     * Wrapper for {@link Files#list(Path)} that return empty stream instead of {@link IOException}.
     *
     * @see Files#list(Path)
     */
    public @NotNull Stream<Path> list(@NotNull Path path) {
        try {
            return Files.list(path.toAbsolutePath().normalize());
        } catch (IOException e) {
            return Stream.empty();
        }
    }

    /**
     * Get file extension.
     */
    public @NotNull String getExtension(@NotNull Path path) {
        val name = path.toAbsolutePath().normalize().getFileName().toString();
        val dotIndex = name.lastIndexOf(".");
        return dotIndex > 0 ? name.substring(dotIndex + 1) : "";
    }

    /**
     * Check for file extension is one of specified extensions.
     */
    @Contract("null, _ -> false")
    public boolean isExtension(@Nullable Path path, @NotNull String @NotNull ... extensions) {
        if (path == null) return false;
        val ext = getExtension(path);
        for (var check : extensions) if (ext.equalsIgnoreCase(check)) return true;
        return false;
    }

    /**
     * Makes normalized and absolute {@link Path} from some types in parameter.
     *
     * @param source source for normalize, supported types: {@link String}, {@link Path},
     *               {@link File}, {@link URL} (with jar or file protocols) and {@link URI}.
     * @return normalized absolute {@link Path} of parameter or null if its is null or not supported.
     * @see Path#normalize()
     * @see Path#toAbsolutePath()
     */
    @Contract("null -> null")
    public @UnknownNullability Path normalizePath(@Nullable Object source) {
        if (source instanceof String string) return Paths.get(string).toAbsolutePath().normalize();
        else if (source instanceof File file) return file.toPath().toAbsolutePath().normalize();
        else if (source instanceof Path path) return path.toAbsolutePath().normalize();
        else if (source instanceof URL url) {
            return switch (url.getProtocol()) {
                case "jar" -> {
                    try {
                        val connection = (JarURLConnection) url.openConnection();
                        yield Paths.get(connection.getJarFileURL().getFile()).toAbsolutePath().normalize();
                    } catch (IOException ignored) {
                        yield null;
                    }
                }
                case "file" -> Paths.get(url.getFile()).toAbsolutePath().normalize();
                default -> null; // switch used for new possible required protocols
            };
        } else if (source instanceof URI uri) return Paths.get(uri).toAbsolutePath().normalize();
        else return null;
    }

    /**
     * Wrapper for {@link PathFiles#move(Path, Path, CopyOption...)} that use
     * {@link PathFiles#normalizePath(Object)} for arguments with {@link Path} type.
     *
     * @see PathFiles#move(Path, Path, CopyOption...)
     * @see Files#move(Path, Path, CopyOption...)
     */
    @Contract("null, _, _ -> null; _, null, _ -> null")
    public @Nullable Path move(@Nullable Object from, @Nullable Object to, @NotNull CopyOption @NotNull ... options) {
        return move(normalizePath(from), normalizePath(to), options);
    }

    /**
     * Wrapper for {@link Files#move(Path, Path, CopyOption...)} that return null on exception.
     *
     * @see Files#move(Path, Path, CopyOption...)
     */
    @Contract("null, _, _ -> null; _, null, _ -> null")
    public @Nullable Path move(@Nullable Path from, @Nullable Path to, @NotNull CopyOption @NotNull ... options) {
        if (from != null && to != null)
            try {
                return Files.move(from, to, options);
            } catch (IOException ignored) {
            }
        return null;
    }

    /**
     * Wrapper for {@link PathFiles#createDirectories(Path, FileAttribute[])} that use
     * {@link PathFiles#normalizePath(Object)} for arguments with {@link Path} type.
     *
     * @see PathFiles#createDirectories(Path, FileAttribute[])
     * @see Files#createDirectories(Path, FileAttribute[])
     */
    public @Nullable Path createDirectories(@Nullable Object file, @NotNull FileAttribute<?> @NotNull ... attrs) {
        return createDirectories(normalizePath(file), attrs);
    }

    /**
     * Wrapper for {@link Files#createDirectories(Path, FileAttribute[])} that return null on exception.
     *
     * @see Files#createDirectories(Path, FileAttribute[])
     */
    public @Nullable Path createDirectories(@Nullable Path file, @NotNull FileAttribute<?> @NotNull ... attrs) {
        if (file != null)
            try {
                return Files.createDirectories(file, attrs).toAbsolutePath().normalize();
            } catch (IOException ignored) {
            }
        return null;
    }
}
