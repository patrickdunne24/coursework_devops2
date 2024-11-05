
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Dex2HexTest {

    private Dex2Hex dex2Hex;

    @Before
    public void setUp() {
        dex2Hex = new Dex2Hex();
    }

    @Test
    public void testConvertToHexadecimal() {
        // Test case for a positive integer
        assertEquals("The Hexadecimal representation should be 'A'", "A", convertAndCaptureOutput("10"));

        // Test case for zero
        assertEquals("The Hexadecimal representation of zero should be empty", "", convertAndCaptureOutput("0"));

        // Test case for another integer
        assertEquals("The Hexadecimal representation should be '1F4'", "1F4", convertAndCaptureOutput("500"));
    }

    @Test
    public void testInvalidInput() {
        // Test for non-integer input
        assertEquals("Should return an error message for non-integer input",
                     "Error: Invalid input. Please provide an integer.",
                     convertAndCaptureOutput("abc"));
    }

    @Test
    public void testNoInputProvided() {
        // Test for no input provided
        assertEquals("Should return an error message for no input provided",
                     "Error: No input provided Please provide an integer input.",
                     convertAndCaptureOutput());
    }

    // Utility method to capture system output from Dex2Hex
    private String convertAndCaptureOutput(String... args) {
        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outputStream));
        Dex2Hex.main(args);
        System.setOut(System.out); // Reset System.out
        return outputStream.toString().trim();
    }
}
