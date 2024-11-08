import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;

public class Dex2HexTest {

    private static final Logger logger = Logger.getLogger(Dex2Hex.class.getName());
    private ByteArrayOutputStream logStream;
    private StreamHandler testHandler;

    @Before
    public void setUp() {
        logStream = new ByteArrayOutputStream();
        testHandler = new StreamHandler(new PrintStream(logStream), new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord logrecord) {
                return record.getMessage() + "\n";  // Only log the message without metadata
            }
        });

        logger.addHandler(testHandler);
        logger.setUseParentHandlers(false); // Avoids duplicate log entries in console
    }

    @After
    public void tearDown() {
        logger.removeHandler(testHandler);
    }

    @Test
    public void testNoInput() {
        String[] args = {};
        captureLoggerOutput(() -> Dex2Hex.main(args));
        
        String output = logStream.toString().trim();
        assertEquals("Error: No input provided. Please enter an integer value.", output);
    }

    @Test
    public void testNonIntegerInput() {
        String[] args = {"abc"};
        captureLoggerOutput(() -> Dex2Hex.main(args));
        
        String output = logStream.toString().trim();
        assertEquals("Error: Non-integer input provided. Please enter a valid integer value.", output);
    }

    @Test
    public void testValidIntegerInput() {
        String[] args = {"255"};
        captureLoggerOutput(() -> Dex2Hex.main(args));
        
        String expectedOutput = "Converting the Decimal Value 255 to Hex...\n" +
                                "Hexadecimal representation is: FF\n" +
                                "The number has been converted successfully!";
        String output = logStream.toString().trim();
        assertEquals(expectedOutput, output);
    }

    private void captureLoggerOutput(Runnable action) {
        testHandler.flush(); // Clear any existing log messages
        action.run();
        testHandler.flush(); // Ensure all logs are captured
    }
}
