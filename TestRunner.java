import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.logging.*;

public class TestRunner {

    private static final Logger logger = Logger.getLogger(TestRunner.class.getName());

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);  // Capture all log levels
        logger.addHandler(handler);
        logger.setUseParentHandlers(false); // Disables default console output
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Dex2HexTest.class);
        int fails = 0;

        try {
            for (Failure failure : result.getFailures()) {
                logger.log(Level.SEVERE, failure.toString());
                fails++;
            }
            
            if (fails > 0) {
                throw new Exception(fails + " tests failed");
            }

            logger.log(Level.INFO, "All tests passed successfully: {0}", result.wasSuccessful());
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
            System.exit(1);
        }

        System.exit(0);
    }
}
