
import  java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Dex2HexTest {

    // Instance of Dex2Hex (if needed for non-static methods in the future)
    Dex2Hex dex2hex;

    @Before
    public void setUp() {
        // Initialize Dex2Hex if it contains non-static methods in the future
        dex2hex = new Dex2Hex();
    }

    @Test
    public void testNoInput() {
        String[] args = {};
        String output = captureOutput(args);
        assertEquals("Expected output for no input", 
                     "Error: No input provided. Please enter an integer value.", 
                     output.trim());
    }

    @Test
    public void testNonIntegerInput() {
        String[] args = {"abc"};
        String output = captureOutput(args);
        assertEquals("Expected output for non-integer input", 
                     "Error: Non-integer input provided. Please enter a valid integer value.", 
                     output.trim());
    }

    @Test
    public void testValidIntegerInput() {
        String[] args = {"255"};
        String output = captureOutput(args);
        assertEquals("Expected output for valid integer input", 
                     "Converting the Decimal Value 255 to Hex...\nHexadecimal representation is: FF\nThe number has been converted successfully!", 
                     output.trim());
    }

    // Utility method to capture console output
    private String captureOutput(String[] args) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        
        // Run Dex2Hex.main with the specified args
        Dex2Hex.main(args);

        // Restore original System.out
        System.setOut(originalOut);

        return outputStream.toString();
    }
}

