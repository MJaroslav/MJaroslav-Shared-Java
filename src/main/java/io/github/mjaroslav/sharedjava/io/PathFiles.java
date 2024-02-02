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

/**
 * Set of wrappers for getting and using {@link Path} as "path/filename/file" parameters in other places.
 *
 * @author MJaroslav
 * @since 1.0.0
 */
@UtilityClass
public class PathFiles {
    /**
     * Wrapper for {@link Paths#get(String, String...)} that use {@link PathFiles#normalizePath(Object)}
     * as first parameter.
     *
     * @param parent   supported type parent directory
     * @param children additional strings to be joined to form the path string
     * @return normalized and absolute {@link Path} from first parameter with resolved children or
     * null if type of first parameter not supported in {@link PathFiles#normalizePath(Object)}
     * @see Paths#get(String, String...)
     * @see PathFiles#normalizePath(Object)
     * @since 1.0.0
     */
    public Path get(@NotNull Object parent, @NotNull String @NotNull ... children) {
        var result = normalizePath(parent);
        if (result == null) return null;
        for (var child : children) result = result.resolve(child);
        return result.normalize().toAbsolutePath();
    }

    /**
     * Get simple name of file without extension.
     *
     * @param path file of interest
     * @return file name without extension; file name if file without extension
     * @since 1.0.0
     */
    public @NotNull String removeExtension(@NotNull Path path) {
        val name = path.toAbsolutePath().normalize().getFileName().toString();
        val dotIndex = name.lastIndexOf(".");
        return dotIndex > 0 ? name.substring(0, dotIndex) : name;
    }

    /**
     * Replaces file extension by moving it.
     *
     * @param path         file for extension replacing.
     * @param newExtension new file extension.
     * @return old extension; null otherwise.
     * @since 1.0.0
     */
    public @Nullable String replaceExtension(@NotNull Path path, @NotNull String newExtension) {
        val ext = getExtension(path);
        if (move(path, get(path.toAbsolutePath().normalize().getParent(),
            removeExtension(path) + "." + newExtension)) == null) return null;
        return ext;
    }

    /**
     * Wrapper for {@link Files#list(Path)} that return empty stream instead of {@link IOException}.
     *
     * @param path the path to the directory
     * @return the Stream describing the content of the directory; empty Stream on IOException
     * @see Files#list(Path)
     * @since 1.0.0
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
     *
     * @param path file with the extension of interest
     * @return file extension or empty string of file is dot file or without extension
     * @since 1.0.0
     */
    public @NotNull String getExtension(@NotNull Path path) {
        val name = path.toAbsolutePath().normalize().getFileName().toString();
        val dotIndex = name.lastIndexOf(".");
        return dotIndex > 0 ? name.substring(dotIndex + 1) : "";
    }

    /**
     * Check for file extension is one of specified extensions.
     *
     * @param path       file for extension check.
     * @param extensions array of required extensions
     * @return true if file extension one of specified extensions with case ignoring; false otherwise
     * @since 1.0.0
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
     *               {@link File}, {@link URL} (with <code>jar:</code> or <code>file://</code> protocols)
     *               and {@link URI} (schemas with file only)
     * @return normalized absolute {@link Path} of supported parameter; null otherwise
     * @see Path#normalize()
     * @see Path#toAbsolutePath()
     * @since 1.0.0
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
     * @param from    the path to the file to move
     * @param to      the path to the target file (maybe associated with a different provider to the source path)
     * @param options options specifying how the move should be done
     * @return the path to the target file or null on IOException
     * @see PathFiles#move(Path, Path, CopyOption...)
     * @see Files#move(Path, Path, CopyOption...)
     * @since 1.0.0
     */
    @Contract("null, _, _ -> null; _, null, _ -> null")
    public @Nullable Path move(@Nullable Object from, @Nullable Object to, @NotNull CopyOption @NotNull ... options) {
        return move(normalizePath(from), normalizePath(to), options);
    }

    /**
     * Wrapper for {@link Files#move(Path, Path, CopyOption...)} that return null on IOException.
     *
     * @param from    the path to the file to move
     * @param to      the path to the target file (maybe associated with a different provider to the source path)
     * @param options options specifying how the move should be done
     * @return the path to the target file or null on IOException
     * @see Files#move(Path, Path, CopyOption...)
     * @since 1.0.0
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
     * @param path  directory to create
     * @param attrs an optional list of file attributes to set atomically when creating the directory
     * @return the directory or null on IOException
     * @see PathFiles#createDirectories(Path, FileAttribute[])
     * @see Files#createDirectories(Path, FileAttribute[])
     * @since 1.0.0
     */
    public @Nullable Path createDirectories(@Nullable Object path, @NotNull FileAttribute<?> @NotNull ... attrs) {
        return createDirectories(normalizePath(path), attrs);
    }

    /**
     * Wrapper for {@link Files#createDirectories(Path, FileAttribute[])} that return null on IOException.
     *
     * @param path  directory to create
     * @param attrs an optional list of file attributes to set atomically when creating the directory
     * @return the directory or null on IOException
     * @see Files#createDirectories(Path, FileAttribute[])
     * @since 1.0.0
     */
    public @Nullable Path createDirectories(@Nullable Path path, @NotNull FileAttribute<?> @NotNull ... attrs) {
        if (path != null)
            try {
                return Files.createDirectories(path, attrs).toAbsolutePath().normalize();
            } catch (IOException ignored) {
            }
        return null;
    }
}
