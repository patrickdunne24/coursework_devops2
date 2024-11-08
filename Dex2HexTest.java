import static org.junit.Assert.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.*;
import org.junit.*;

public class Dex2HexTest {
    private static final Logger logger = Logger.getLogger(Dex2HexTest.class.getName());
    private ByteArrayOutputStream logStream;
    private StreamHandler testHandler;

    @Before
    public void setUp() {
        logStream = new ByteArrayOutputStream();
        
        // Custom formatter to capture only the message
        testHandler = new StreamHandler(new PrintStream(logStream), new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord logRecord) {
                return logRecord.getMessage() + "\n";
            }
        });
        
        // Add handler to both Dex2HexTest logger and Dex2Hex logger to capture both
        Logger dex2HexLogger = Logger.getLogger(Dex2Hex.class.getName());
        dex2HexLogger.addHandler(testHandler);
        dex2HexLogger.setUseParentHandlers(false); // Avoids duplicate logs

        logger.addHandler(testHandler);
        logger.setUseParentHandlers(false);
    }

    @After
    public void tearDown() {
        logger.removeHandler(testHandler);
        Logger.getLogger(Dex2Hex.class.getName()).removeHandler(testHandler);
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
        testHandler.flush(); // Ensure previous logs are flushed
        logStream.reset(); // Clear the log stream
        action.run();
        testHandler.flush(); // Ensure all logs are captured
    }
}
