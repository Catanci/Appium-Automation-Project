pipeline {
    agent any

    parameters {
        string(name: 'EMULATOR_NAME', defaultValue: 'nexus5', description: 'Name of the Android emulator to use')
    }

    stages {
        stage('Start Appium Server') {
            steps {
                bat '''
                    start "Appium Server" /B cmd /c "appium > appium.log 2>&1"
                    timeout 10
                    tasklist /FI "IMAGENAME eq node.exe" 2>NUL | find /I /N "node.exe">NUL
                    if "%ERRORLEVEL%"=="0" (
                        echo Appium is running
                    ) else (
                        echo Appium failed to start
                        exit 1
                    )
                '''
            }
        }

        stage('Start Android Emulator') {
            steps {
                bat '''
                    cd %ANDROID_HOME%\\emulator || echo "Failed to change directory"
                    start /b emulator -avd %EMULATOR_NAME% || echo "Failed to start emulator"
                    timeout 120
                    adb shell getprop sys.boot_completed || echo "Failed to check boot completion"
                    adb wait-for-device || echo "ADB wait-for-device failed"
                '''
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            bat '''
                taskkill /F /IM node.exe
                taskkill /F /IM emulator.exe
                taskkill /F /IM qemu-system-x86_64.exe
            '''
        }
    }
}