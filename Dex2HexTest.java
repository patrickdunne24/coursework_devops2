import org.junit.Test;
import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Dex2HexTest {

    @Test
    public void testValidIntegerInput() {
        // Set up output stream to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the main method with valid integer input
        Dex2Hex.main(new String[]{"255"});

        // Check if the output contains the expected hexadecimal conversion
        String output = outputStream.toString();
        assertTrue(output.contains("The Hexadecimal representation is: FF"));
    }

    @Test
    public void testInvalidInput() {
        // Set up output stream to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the main method with invalid (non-integer) input
        Dex2Hex.main(new String[]{"abc"});

        // Check if the output contains the error message for invalid input
        String output = outputStream.toString();
        assertTrue(output.contains("Error: Invalid input. Please provide an integer."));
    }

    @Test
    public void testNoInputProvided() {
        // Set up output stream to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run the main method with no input
        Dex2Hex.main(new String[]{});

        // Check if the output contains the error message for missing input
        String output = outputStream.toString();
        assertTrue(output.contains("Error: No input provided. Please provide an integer input."));
    }
}
