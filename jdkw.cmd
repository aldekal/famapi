@echo off
setlocal enabledelayedexpansion
echo [JDKW] ------------------------------------------------------------------------

echo [JDKW] ".---------------------------------------------------------------------."
echo [JDKW] "|  ____________________________  _______  __          __              |"
echo [JDKW] "| |                            ||  __ \ \ \ \        / /              |"
echo [JDKW] "| |   JDK Wrapper Installer    || |  | | \ \ \  /\  / /               |"
echo [JDKW] "| |____________________________||_|  |_|  \_\\ \/  \/                 |"
echo [JDKW] "|                                                                     |"
echo [JDKW] ".---------------------------------------------------------------------."
echo [JDKW] =========================================================================
echo [JDKW] JDK Wrapper Script
echo [JDKW] =========================================================================
echo [JDKW] -------------------------------------------------------------------------

REM Path to the jdk-wrapper.properties file
set "PROPERTIES_FILE=%~dp0.jdk\wrapper\jdk-wrapper.properties"

REM Parse properties
for /F "tokens=1,2 delims==" %%A in (%PROPERTIES_FILE%) do (
    set "%%A=%%B"
)

REM Set target folder and zip file path
set "TARGET_DIR=%USERPROFILE%\.jdk\jdk-21.0.5+11"
set "JDK_ZIP=%USERPROFILE%\.jdk\jdk.zip"

REM Create target folder if it doesn't exist
if not exist "%USERPROFILE%\.jdk" (
    mkdir "%USERPROFILE%\.jdk"
)

REM Check if the desired JDK is already installed
if exist "!TARGET_DIR!" (
    echo [JDKW] JDK is already installed at !TARGET_DIR!.
) else (
    REM Download JDK if the distribution URL is set
    if defined distributionUrl (
        echo [JDKW] Downloading JDK from !distributionUrl!
        curl -L -o "!JDK_ZIP!" !distributionUrl!

        REM Check if the download was successful
        if exist "!JDK_ZIP!" (
            REM Unzip the downloaded JDK
            echo [JDKW] Unzipping JDK to !TARGET_DIR!
            mkdir "!TARGET_DIR!" 2>nul
            tar -xf "!JDK_ZIP!" -C "!TARGET_DIR!" --strip-components=1

            REM Cleanup
            del "!JDK_ZIP!"
            echo [JDKW] JDK installation complete.
        ) else (
            echo [JDKW] Failed to download JDK.
        )
    ) else (
        echo [JDKW] distributionUrl not found in properties file.
    )
)

echo [JDKW] ------------------------------------------------------------------------
endlocal
