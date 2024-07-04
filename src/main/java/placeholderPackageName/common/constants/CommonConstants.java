package placeholderPackageName.common.constants;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.appium.java_client.remote.MobileBrowserType.ANDROID;

public class CommonConstants {
    private final static Logger logger = LogManager.getLogger();
    public static final String MOBILE_PLATFORM_NAME = getPlatformName();

    private static String getPlatformName() {
        String platformNameFromPomXml = System.getProperty("platform");
        String platformName;

        if (platformNameFromPomXml != null)
            platformName = platformNameFromPomXml;
        else {
            logger.warn("The Maven Profile is missing the platform configuration.");
            logger.warn("The default platform '{}' will be enabled for this run.", ANDROID);
            platformName = ANDROID;
        }

        return platformName.toLowerCase();
    }
}
