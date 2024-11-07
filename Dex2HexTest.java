
import  java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Dex2HexTest {


    Dex2Hex dex2hex;

    @Before
    public void setUp() {
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


    private String captureOutput(String[] args) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        

        Dex2Hex.main(args);


        System.setOut(originalOut);

        return outputStream.toString();
    }
}

