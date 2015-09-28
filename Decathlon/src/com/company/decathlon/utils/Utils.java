package com.company.decathlon.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static com.company.Consts.DOT_DELIMITER;
import static com.company.Consts.FILE_NOT_FOUND;

/**
 * Created by zygis on 27/09/2015.
 */
public class Utils {

    public static double minToSec(String min) throws NumberFormatException {
        if (min.contains(DOT_DELIMITER)) {
            String[] split = min.split("\\.");
            return new Double(split[0]) * 60 + new Double(split[1]);
        } else {
            return new Double(min);
        }
    }

    public static PrintStream newFileOutputStream(String file) {
        try {
            return new PrintStream(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(FILE_NOT_FOUND + "(" + file + "): " + e.getMessage());
        }
    }

    public static boolean existsFile(String fileName) {
        File f = new File(fileName);
        return (f != null && f.exists() && !f.isDirectory() ? true : false);
    }

}
