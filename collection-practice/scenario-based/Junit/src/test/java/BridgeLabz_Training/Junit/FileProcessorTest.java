package BridgeLabz_Training.Junit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FileProcessorTest {

    FileProcessor processor = new FileProcessor();
    String filename = "testfile.txt";

    @Test
    void testWriteAndReadFile() throws IOException {
        String content = "Hello JUnit File Test";

        processor.writeToFile(filename, content);
        String result = processor.readFromFile(filename);

        assertEquals(content, result);
    }

    @Test
    void testFileExistsAfterWrite() throws IOException {
        processor.writeToFile(filename, "Test Data");

        File file = new File(filename);
        assertTrue(file.exists());
    }

    @Test
    void testReadFileNotFoundException() {
        assertThrows(IOException.class, () -> {
            processor.readFromFile("nonexistent.txt");
        });
    }
}
