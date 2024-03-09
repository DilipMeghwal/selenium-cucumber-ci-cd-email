package utils;

import java.io.File;

public class AppConstants {
    public static final String PROJ_PATH = System.getProperty("user.dir");
    public static final String FILE_SEPARATOR = File.separator;

    public static final String OS_NAME = System.getProperty("os.name").toLowerCase();

    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String SAFARI = "safari";
    public static final String WINDOWS = "windows";
    public static final String LINUX = "linux";
    public static final String MACOS = "mac";

    private AppConstants(){

    }

}
