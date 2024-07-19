pipeline {
    agent any

    parameters {
        choice(name: 'PLATFORM', choices: ['android', 'ios'], description: 'Choose the platform to test on')
        string(name: 'EMULATOR_NAME', defaultValue: 'nexus5', description: 'Name of the Android emulator or iOS simulator to use')
    }

    stages {
        stage('Start Appium Server') {
            steps {
                bat '''
                    start "Appium Server" /B appium
                    ping -n 11 127.0.0.1 > nul
                    tasklist /FI "IMAGENAME eq node.exe" 2>NUL | find /I /N "node.exe">NUL
                    if %ERRORLEVEL% == 0 (
                        echo Appium is running
                    ) else (
                        echo Appium failed to start
                        exit 1
                    )
                '''
            }
        }

        stage('Start Emulator/Simulator') {
            steps {
                script {
                    if (params.PLATFORM == 'android') {
                        bat '''
                            cd "%ANDROID_HOME%\\emulator" || echo "Failed to change directory"
                            start /b "%ANDROID_HOME%\\emulator\\emulator" -avd %EMULATOR_NAME%
                            echo Waiting for emulator to boot...
                            :loop
                            adb shell getprop sys.boot_completed | find "1" > nul
                            if errorlevel 1 (
                                timeout /t 5 > nul
                                goto loop
                            )
                            echo Emulator booted successfully
                            adb wait-for-device
                        '''
                    } else if (params.PLATFORM == 'ios') {
                        bat '''
                            echo Starting iOS simulator %EMULATOR_NAME%
                            start "iOS Simulator" /B open -a Simulator --args -CurrentDeviceUDID %EMULATOR_NAME%
                            echo Waiting for simulator to boot...
                            :loop
                            xcrun simctl bootstatus %EMULATOR_NAME% | find "Booted" > nul
                            if errorlevel 1 (
                                timeout /t 5 > nul
                                goto loop
                            )
                            echo Simulator booted successfully
                        '''
                    }
                }
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test -Dplatform=%PLATFORM%'
            }
        }
    }

    post {
        always {
            bat '''
                tasklist /FI "IMAGENAME eq node.exe" 2>NUL | find /i "node.exe" >NUL && taskkill /F /IM node.exe
                tasklist /FI "IMAGENAME eq emulator.exe" 2>NUL | find /i "emulator.exe" >NUL && taskkill /F /IM emulator.exe
                tasklist /FI "IMAGENAME eq qemu-system-x86_64.exe" 2>NUL | find /i "qemu-system-x86_64.exe" >NUL && taskkill /F /IM qemu-system-x86_64.exe
                tasklist /FI "IMAGENAME eq Simulator" 2>NUL | find /i "Simulator" >NUL && taskkill /F /IM Simulator
            '''
        }
    }
}
