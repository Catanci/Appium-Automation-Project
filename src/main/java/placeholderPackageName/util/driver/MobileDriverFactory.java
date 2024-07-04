package placeholderPackageName.util.driver;

import java.security.InvalidParameterException;

import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;
import static placeholderPackageName.common.constants.CommonConstants.MOBILE_PLATFORM_NAME;

public class MobileDriverFactory {
    public MobileDriverService getDriverService() {
        MobileDriverService driver;

        switch (MOBILE_PLATFORM_NAME) {
            case ANDROID:
                driver = new AndroidDriverServiceImpl();
                break;
            case IOS:
                driver = new IOSMobileDriver();
                break;
            default:
                throw new InvalidParameterException("Please use platform as '" + ANDROID + "' or '" + IOS + "'");
        }

        return driver;
    }
}
