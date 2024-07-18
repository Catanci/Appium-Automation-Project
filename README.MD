## 📱 Mobile Testing Framework using Appium

This project showcases a comprehensive automation framework for mobile testing, supporting both Android and iOS platforms for web and native app testing using Appium.

### Key Features:
* **Cross-Platform Testing**: Supports both Android and iOS devices.
* **Web and Native App Testing**: Capable of testing both mobile web browsers and native applications.
* **Appium Integration**: Utilizes Appium for robust mobile automation.
* **Page Object Model**: Implements POM design pattern for better maintainability.
* **CI/CD Integration**: Designed for seamless integration into CI/CD pipelines for automated testing workflows.
____
### Setup Checklist
To ensure smooth execution of this project, please complete the following steps:

#### 1. Install Essentials

* **Java Development Kit (JDK)**: Version 8 or higher.
* **Apache Maven**: For building and running the project.
* **Node.js and npm**: Required for Appium.
* **Appium**: Install via npm (`npm install -g appium`).
* **Android SDK**: For Android testing.
* **Xcode**: For iOS testing (Mac only).

#### 2. Mobile-Specific Setup

* **Android**: Set up ANDROID_HOME environment variable.
* **iOS**: Install Xcode Command Line Tools.
* **Appium Doctor**: Run to verify setup (`npm install -g appium-doctor`).

#### 3. Optional (But Recommended)

* **Real Devices**: For testing on actual Android and iOS devices.
* **Simulators/Emulators**: Set up Android emulators and iOS simulators.
* **Jenkins**: For CI/CD integration.
* **IDE**: Choose your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).

#### 4. Project Setup Notes
* **Dependencies**: Managed in the project's pom.xml file. Ensure all necessary dependencies (Appium, TestNG, etc.) are included.
* **Platform** to test is specified in **pom.xml**
____
### Execution
1. **Launch Appium Server**: Start the Appium server.
2. **Connect Devices/Start Emulators**: Ensure your testing devices or emulators are ready.
3. **Run Tests**:
    * **IDE**: Run tests directly from your IDE by right-clicking on the test class or method and selecting "Run".
    * **Command Line**: Navigate to the project's root directory and execute:
        * For Android: `mvn test -Dplatform=android`
        * For iOS: `mvn test -Dplatform=ios`
    * **Run All Tests**: Execute `mvn test` to run all tests for both platforms.

____
### Jenkins Integration
#### Pre-requisites
* **Jenkins**: Ensure Jenkins is installed and running.
* **Plugins**: Install necessary plugins (e.g., Git plugin, Maven plugin).
* **Tools Configuration**: Configure JDK, Maven, and Node.js within Jenkins.

#### Create a New Freestyle Job:
1. **New Job**: Navigate to Jenkins dashboard and click "New Item".
2. **Name and Type**: Provide a name for your job and select "Freestyle" as the job type.
3. **Configure Build Steps(an example for Android)**:
   - Scroll to the "Build" section
   - Add the following build steps in order:

   a. **Start Appium Server**:
   - Click "Add build step" and select "Execute shell"
   - Enter the following command:
     ```
     appium &
     sleep 10  # Give Appium time to start
     ```

   b. **Start Emulator** (for Android):
   - Click "Add build step" and select "Execute shell"
   - Enter the following command:
     ```
     cd %ANDROID_HOME%\emulator || echo "Failed to change directory"
     start /b emulator -avd phoneName || echo "Failed to start emulator"
     timeout 60
     adb shell getprop sys.boot_completed || echo "Failed to check boot completion"
     adb wait-for-device || echo "ADB wait-for-device failed"
     ```
   Replace "YourEmulatorName" with your actual emulator name

   c. **Run Tests**:
   - Click "Add build step" and select "Invoke top-level Maven targets"
   - Add the following Maven goals:

     For Android:
     ```
     test -Dplatform=android
     ```

     For iOS:
     ```
     test -Dplatform=ios
     ```

   d. **Stop Emulator and Appium** (Post-build step):
   - Click "Add build step" and select "Execute Windows batch command"
   - Enter the following commands:
     ```
     taskkill /F /IM node.exe
     taskkill /F /IM emulator.exe
     taskkill /F /IM qemu-system-x86_64.exe
     ```

4. **Node Selection**: Ensure the Jenkins job runs on a correct node.

#### Run Your Freestyle Job

Good luck! 