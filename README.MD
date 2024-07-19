## 📱 Mobile Testing Framework using Appium

This project showcases a comprehensive automation framework for mobile testing, supporting both Android and iOS platforms for web and native app testing using Appium.

### Key Features:
* **Cross-Platform Testing**: Supports both Android and iOS devices.
* **Web and Native App Testing**: Capable of testing both mobile web browsers and native applications.
* **Appium Integration**: Utilizes Appium for robust mobile automation.
* **Page Object Model**: Implements POM design pattern for better maintainability.
* **CI/CD Integration**: Integrated with Jenkins for automated testing workflows.

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
* **IDE**: Choose your preferred Java IDE (e.g., IntelliJ IDEA).

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
The project is integrated with Jenkins using a pipeline approach, allowing for parameterized builds without the need to edit the Jenkinsfile.

#### Jenkins Pipeline Setup
1. **Pipeline Job**: A pipeline job is configured in Jenkins, linked to the repository containing the Jenkinsfile.
2. **Parameterized Build**: The pipeline is set up with parameters that can be chosen at build time.

#### Key Features of the Jenkins Integration:
- **Parameterized Builds**: Users can specify the platform and emulator name when starting a build.
- **Appium Server Management**: Automatically starts and stops the Appium server.
- **Emulator/Simulator Control**: Starts the specified Android emulator or iOS simulator and waits for it to boot completely.
- **Cross-Platform Testing**: Supports both Android and iOS testing.
- **Test Execution**: Runs Maven tests based on the specified parameters.
- **Cleanup**: Ensures all processes are terminated after the build.

#### Available Build Parameters
- **PLATFORM**: Choose between 'android' or 'ios'.
- **EMULATOR_NAME**: Name of the Android emulator or iOS simulator to use.

#### Running the Jenkins Job
1. Navigate to the pipeline job in Jenkins.
2. Click "Build with Parameters".
3. You will see a form where you can specify:
    - **PLATFORM**: Choose 'android' or 'ios'.
    - **EMULATOR_NAME**: Enter the name of the emulator/simulator you want to use.
4. Click "Build" to start the pipeline with your chosen parameters.

#### Pipeline Stages
The pipeline executes the following stages:
1. Start Appium Server
2. Start Emulator/Simulator (based on selected platform and device name)
3. Run Tests
4. Cleanup

#### Test Execution
- Tests are executed using Maven with the command: `mvn test -Dplatform=${PLATFORM}`
- This allows for dynamic selection of the testing platform (Android/iOS) at runtime.

#### Troubleshooting
- Ensure the emulator/simulator name you specify exists in the Jenkins environment.
- For iOS testing, make sure the Jenkins agent is set up with the necessary iOS development tools.
- If the build fails, check the console output for specific error messages.
- Check if the job runs on the correct node (Windows pref).

#### Run Your Pipeline Job

Good luck! 