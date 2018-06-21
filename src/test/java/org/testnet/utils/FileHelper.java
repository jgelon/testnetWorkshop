package org.testnet.utils;

import java.io.File;
import java.io.IOException;

public class FileHelper {

    /**
     * This method retrieve the root folder path of the project (this makes the project independent for each machine and location)
     * @return a string with the Path to the root folder.
     */
    public static String getRootPath() {
        return System.getProperty("user.dir");
    }
}
