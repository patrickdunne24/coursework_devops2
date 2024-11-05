import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(Dex2HexTest.class);
        
        int failCount = 0;

        // Print each failure's details
        for (Failure failure : result.getFailures()) {
            System.out.println("Test failed: " + failure.toString());
            failCount++;
        }

        // Report final result
        if (result.wasSuccessful()) {
            System.out.println("All tests passed successfully.");
        } else {
            System.out.println(failCount + " test(s) failed.");
            System.exit(1);  // Exit with error code if there are failures
        }
        
        System.exit(0);  // Exit with success code if all tests passed
    }
}
