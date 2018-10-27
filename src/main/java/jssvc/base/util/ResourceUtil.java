package jssvc.base.util;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ResourceUtil {
    // static String propertiesFileName="config.properties";//SRC_ROOT_FOLDER
    static String propertiesFileName = "config.properties";

    static Properties p = null;

    static {
        p = new Properties();
        try {
            p.load(ResourceUtil.class.getClassLoader().getResourceAsStream(propertiesFileName));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static String getText(String key) {

        String s = p.getProperty(key);
        System.out.println("value for key: " + key + " = " + s);
        return s;

    }

    public static void readAllProperties() {
        try {

            Enumeration<?> en = p.propertyNames();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String Property = p.getProperty(key);
                System.out.println("line: " + key + " = " + Property);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("ConfigInfoError" + e.toString());
        }
    }

}
