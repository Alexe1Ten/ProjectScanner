package project_scanner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testScanDirectory_withEmptyDirectory() throws IOException {
        Path tempDir = Files.createTempDirectory("testEmptyDir");

        assertDoesNotThrow(() -> App.scanDirectory(tempDir, "", true));
    }

    @Test
    public void testScanDirectory_withSingleFile() throws IOException {
        Path tempDir = Files.createTempDirectory("testSingleFile");
        Path tempFile = Files.createFile(tempDir.resolve("testFile.txt"));

        assertDoesNotThrow(() -> App.scanDirectory(tempDir, "", true));

        Files.delete(tempFile);
        Files.delete(tempDir);
    }

    @Test
    public void testMain_withInvalidArguments() {
        String[] args = {};
        App.main(args);
    }

    @Test
    public void testScanDirectory_withNonExistenPath() throws IOException {
        Path nonexistentPath = Paths.get("noneexistent_directory");

        assertDoesNotThrow(() -> App.scanDirectory(nonexistentPath, "", true));
    }
}
