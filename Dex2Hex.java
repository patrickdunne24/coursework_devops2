import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

class Dex2Hex {
    private static final Logger logger = Logger.getLogger(Dex2Hex.class.getName());

    static {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);  // Capture all log levels
        logger.addHandler(handler);
        logger.setUseParentHandlers(false);  // Disables default console output
    }

    public static void main(String[] args) {
        // Check if no input is provided
        if (args.length == 0) {
            logger.log(Level.SEVERE, "Error: No input provided. Please enter an integer value.");
            return;
        }

        int arg1;
        try {
            // Attempt to parse the input as an integer
            arg1 = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Error: Non-integer input provided. Please enter a valid integer value.");
            return;
        }

        // Proceed with the hex conversion if input is valid
        char ch[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int rem = arg1;
	int num = arg1;
        String hexadecimal = "";

        logger.log(Level.INFO, "Converting the Decimal Value {0} to Hex...", new Object[]{num});

        while (num != 0) {
            rem = num % 16;
            hexadecimal.insert(0, ch[rem]);  // Prepend the character to build hex representation
            num = num / 16;
        }

        logger.log(Level.INFO, "Hexadecimal representation is: {0}", hexadecimal);
        logger.log(Level.INFO, "The number has been converted successfully!");
	}
}
