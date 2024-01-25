package io.github.mjaroslav.sharedjava.io;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@SuppressWarnings("DuplicateExpressions")
class PathFilesTest {
    @TempDir
    Path tempDir;

    @Test
    void get() throws URISyntaxException, IOException {
        val tempDirAbs = tempDir.toAbsolutePath();
        var expected = tempDirAbs;
        var actual = PathFiles.get(tempDir);
        Assertions.assertEquals(expected, actual, "Path as value");

        expected = tempDirAbs.resolve("get.txt");
        actual = PathFiles.get(tempDir, "get.txt");
        Assertions.assertEquals(expected, actual, "Path as value with child");

        expected = tempDirAbs;
        actual = PathFiles.get(tempDir.toFile());
        Assertions.assertEquals(expected, actual, "File as value");

        expected = tempDirAbs.resolve("get.txt");
        actual = PathFiles.get(tempDir.toFile(), "get.txt");
        Assertions.assertEquals(expected, actual, "File as value with child");

        val uri = new URI("file://" + tempDir.toString());
        expected = Paths.get(uri);
        actual = PathFiles.get(uri);
        Assertions.assertEquals(expected, actual, "URI as value");

        expected = Paths.get(uri).resolve("get.txt");
        actual = PathFiles.get(uri, "get.txt");
        Assertions.assertEquals(expected, actual, "URI as value with child");

        var url = new URL("file://" + tempDir.toString());
        expected = Paths.get(url.getFile());
        actual = PathFiles.get(url);
        Assertions.assertEquals(expected, actual, "URL file:// as value");

        expected = Paths.get(url.getFile()).resolve("get.txt");
        actual = PathFiles.get(url, "get.txt");
        Assertions.assertEquals(expected, actual, "URL file:// as value with child");

        url = new URL("jar:file://" + tempDir.toString() + "/get.jar!/");
        val jarFile = ((JarURLConnection) url.openConnection()).getJarFileURL().getFile();
        expected = Paths.get(jarFile);
        actual = PathFiles.get(url);
        Assertions.assertEquals(expected, actual, "URL jar:file:// as value");

        // Jar file as directory? Why not :)
        expected = Paths.get(jarFile).resolve("get.txt");
        actual = PathFiles.get(url, "get.txt");
        Assertions.assertEquals(expected, actual, "URL jar:file:// as value with child");
    }

    @Test
    void removeExtension() {
        var path = Paths.get("removeExtension.txt");
        var expected = "removeExtension";
        var actual = PathFiles.removeExtension(path);
        Assertions.assertEquals(expected, actual, "Common file name");

        path = Paths.get(".removeExtension");
        expected = ".removeExtension";
        actual = PathFiles.removeExtension(path);
        Assertions.assertEquals(expected, actual, "Dot file name");

        path = Paths.get("removeExtension");
        expected = "removeExtension";
        actual = PathFiles.removeExtension(path);
        Assertions.assertEquals(expected, actual, "No extension file name");
    }

    @Test
    void replaceExtension() throws IOException {
        val originalPath = tempDir.toAbsolutePath().resolve("replaceExtension.txt");
        val renamedPath = tempDir.toAbsolutePath().resolve("replaceExtension.html");
        Files.createFile(originalPath);
        Assertions.assertTrue(Files.isRegularFile(originalPath), "Original file not created");
        Assertions.assertFalse(Files.isRegularFile(renamedPath), "Renamed file already exists");

        val expected = "txt";
        val actual = PathFiles.replaceExtension(originalPath, "html");
        Assertions.assertEquals(expected, actual, "Old extension not match");

        Assertions.assertFalse(Files.isRegularFile(originalPath), "Original file not moved");
        Assertions.assertTrue(Files.isRegularFile(renamedPath), "Renamed file not exists");
    }

    @Test
    void list() throws IOException {
        val dir = tempDir.toAbsolutePath().resolve("list");
        Files.createDirectory(dir);
        val file1 = Files.createFile(dir.resolve("1.txt")).toAbsolutePath();
        val file2 = Files.createFile(dir.resolve("2.png")).toAbsolutePath();
        val file3 = Files.createFile(dir.resolve("3.exe")).toAbsolutePath();

        Assertions.assertTrue(Files.isDirectory(dir), "Directory not created");
        Assertions.assertTrue(Files.isRegularFile(file1), "First file not created");
        Assertions.assertTrue(Files.isRegularFile(file2), "Second file not created");
        Assertions.assertTrue(Files.isRegularFile(file3), "Third file not created");

        val actual = PathFiles.list(dir).sorted(Comparator.comparing(a -> a.getFileName().toString())).collect(Collectors.toList());

        Assertions.assertIterableEquals(Arrays.asList(file1, file2, file3), actual, "Directory content not match");
    }

    @Test
    void getExtension() {
        var path = Paths.get("getExtension.txt");
        var expected = "txt";
        var actual = PathFiles.getExtension(path);
        Assertions.assertEquals(expected, actual, "Common file name");

        path = Paths.get(".getExtension");
        expected = "";
        actual = PathFiles.getExtension(path);
        Assertions.assertEquals(expected, actual, "Dot file name");

        path = Paths.get("getExtension");
        expected = "";
        actual = PathFiles.getExtension(path);
        Assertions.assertEquals(expected, actual, "No extension file name");
    }

    @Test
    void isExtension() {
        val extensions = new String[]{"txt", "png"};
        var path = tempDir.toAbsolutePath().resolve("isExtension.txt");
        var actual = PathFiles.isExtension(path, extensions);
        Assertions.assertTrue(actual, "Lower case extension");

        path = tempDir.toAbsolutePath().resolve("isExtension.PNG");
        actual = PathFiles.isExtension(path, extensions);
        Assertions.assertTrue(actual, "Upper case extension");

        path = tempDir.toAbsolutePath().resolve("isExtension.exe");
        actual = PathFiles.isExtension(path, extensions);
        Assertions.assertFalse(actual, "Extension not in list");
    }

    @Test
    void normalizePath() throws IOException, URISyntaxException {
        var expected = tempDir.toAbsolutePath();
        var actual = PathFiles.normalizePath(tempDir);
        Assertions.assertEquals(expected, actual, "Path as value");

        actual = PathFiles.normalizePath(tempDir.toFile());
        Assertions.assertEquals(expected, actual, "File as value");

        val uri = new URI("file://" + tempDir.toString());
        expected = Paths.get(uri);
        actual = PathFiles.normalizePath(uri);
        Assertions.assertEquals(expected, actual, "URI as value");

        var url = new URL("file://" + tempDir.toString());
        expected = Paths.get(url.getFile());
        actual = PathFiles.normalizePath(url);
        Assertions.assertEquals(expected, actual, "URL file:// as value");

        url = new URL("jar:file://" + tempDir.toString() + "/get.jar!/");
        val jarFile = ((JarURLConnection) url.openConnection()).getJarFileURL().getFile();
        expected = Paths.get(jarFile);
        actual = PathFiles.normalizePath(url);
        Assertions.assertEquals(expected, actual, "URL jar:file:// as value");
    }

    @Test
    void move() throws IOException {
        val originalPath = tempDir.toAbsolutePath().resolve("move.txt");
        val renamedPath = tempDir.toAbsolutePath().resolve("moved.txt");
        Files.createFile(originalPath);
        Assertions.assertTrue(Files.isRegularFile(originalPath), "Original file not created");
        Assertions.assertFalse(Files.isRegularFile(renamedPath), "Moved file already exists");

        val actual = PathFiles.move(originalPath, renamedPath);

        Assertions.assertFalse(Files.isRegularFile(originalPath), "Original file not moved");
        Assertions.assertTrue(Files.isRegularFile(renamedPath), "Moved file not exists");
    }

    @Test
    void createDirectories() {
        val expected = tempDir.toAbsolutePath().resolve("first").resolve("second").resolve("third");
        Assertions.assertFalse(Files.isDirectory(expected), "Directory tree already exists");
        val actual = PathFiles.createDirectories(expected);
        Assertions.assertEquals(expected, actual, "Created directory tree not match");
        Assertions.assertTrue(Files.isDirectory(expected), "Directory tree not created");
    }
}
