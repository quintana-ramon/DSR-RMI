package E1;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Input {

    private Logger logger = Logger.getLogger(Input.class.getName());

    public Input() {
        logger.setLevel(Level.INFO);
    }

    public int isInt(String message) {
        Scanner read = new Scanner(System.in);
        logger.info(message);
        Integer i = Integer.parseInt(read.next());
        int value = i.intValue();
        read.close();
        return value;
    }

    public String isStr(String message) {
        Scanner read = new Scanner(System.in);
        logger.info(message);
        String value = read.next();
        if (read.hasNextLine()) {
            value += read.nextLine();
        }
        read.close();
        return value;
    }

    public float isFlt(String message) {
        Scanner read = new Scanner(System.in);
        logger.info(message);
        Float f = Float.parseFloat(read.next());
        float value = f.floatValue();
        read.close();
        return value;
    }

    public double isDob(String message) {
        Scanner read = new Scanner(System.in);
        logger.info(message);
        Double d = Double.parseDouble(read.next());
        double value = d.doubleValue();
        return value;
    }
}
