package com.company.decathlon.utils;

import static com.company.Consts.DOT_DELIMITER;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Logger;

import com.company.Consts;

/**
 * Created by zygis on 27/09/2015.
 */
public final class Utils {

    public static double minToSec(String min) throws NumberFormatException {
        if (min.contains(DOT_DELIMITER)) {
            String[] split = min.split("\\.");
            return new Double(split[0]) * 60 + new Double(split[1]);
        } else {
            return new Double(min);
        }
    }

	public static PrintStream newFileOutputStream(String file)
			throws IOException {
		PrintStream printStream = null;
		try {
			printStream = new PrintStream(new FileOutputStream(file));
			return printStream;
		} catch (Exception e) {
			logInfo(Consts.FILE_NOT_FOUND);
			return printStream;
		}
	}

    public static boolean existsFile(String fileName) {
        File f = new File(fileName);
        return (f != null && f.exists() && !f.isDirectory() ? true : false);
    }

    public static Logger logger() {
        return Logger.getLogger("Utils");
    }

    public static void logInfo(String message) {
        logger().info(message);
    } 
    
    public static void logWarning(String message) {
        logger().warning(message);
    }     
    
}
