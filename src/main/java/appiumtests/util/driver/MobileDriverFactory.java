package appiumtests.util.driver;

import java.security.InvalidParameterException;

import static appiumtests.constants.PlatformConstants.MOBILE_PLATFORM_NAME;
import static appiumtests.constants.DriverConstants.ANDROID;
import static appiumtests.constants.DriverConstants.IOS;

public class MobileDriverFactory {
    public MobileDriverService getDriverService() {
        MobileDriverService driver;

        switch (MOBILE_PLATFORM_NAME) {
            case ANDROID:
                driver = new AndroidDrivers();
                break;
            case IOS:
                driver = new IOSDrivers();
                break;
            default:
                throw new InvalidParameterException("Please use platform like '" + ANDROID + "' or '" + IOS + "'");
        }
        return driver;
    }
}
