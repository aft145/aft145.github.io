# IoT based Smart Home
## Introduction
The advancement in the technology has made us to enter into a different world where we can interact with the objects. An IoT-based smart home automation system is a system in which all the devices like a bulb, fan, and other home appliances are controlled using the mobile application via the Internet. Users can control and monitor all devices remotely if they are on job or far from home they can easily control every device in the house that is connected to the system. Like they can switch on and off the electric bulb or fan from their work location. A smart home is highly efficient in saving electricity. Biometric door locks are used to secure the house. 
## Getting Started
### Cloning the repository
git clone https://github.com/aft145/aft145.github.io.git
### Software Requirement
1. Windows OS
3. Arduino IDE
4. Android Studio
5. Firebase DB
### Hardware Requirement
1. Node MCU(Microcontroller)
2. Relay
3. LEDs
4. Senosrs:
  * Temperature Sensors LM35 or DHT11
  * Light Dependent Resistor LDR
  * Finger Print Sensor R307
  * Motion Sensor HC-SR501
## Setting up Android Studio
### Run the project in Android Studio
To run  this project in the Android Studio, Click on File option and go to open. Now select the location where you clone this project. After the project is open in the android studio, Connect you application to the Firebase and give required permissions in the AndroidManifest file. After these steps run the application by pressing Shift+F10.
### Connect your App to Firebase
Create a firebase project by going to [Firebase](https://firebase.google.com/ "Firebase site") . When a project is created go to the console and select Android to register your app. Add your package name and SHA-1 (Debug signing certificate). After registering your app, download the google-services.json file. To add this file to the project, Switch to the project view in android studio and Move this file to the app module root directory.
#### Dependencies in Build.Gradle file
You have to write the following dependencies in the build.gradle file in order to connect it to firebase. Also apply the google plugin after dependencies.

```
dependencies {
    implementation 'com.google.firebase:firebase-analytics'
    implementation 'com.google.firebase:firebase-auth:20.0.1'
    }
apply plugin: 'com.google.gms.google-services'
```

Now your application is connected to the firebase.
### Permissions in Manifest file
IoT based  smart home application need following permissions in the android manifest file. In order to run the files in the android studio, you need to specify these permissions in the AndroidManifest.xml file.

```
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
```

### Setting Up Arduino IDE
Open the Arduino IDE software and press CTRL+O to open the files. Select the directory where you have clone the repository. Now the Files are open inside the IDE.
### Installing COM/Serial Port Driver
In order to upload code to the Node MCU and use the serial console, you need to install the COM/Serial port drivers. You can [Download](https://github.com/nodemcu/nodemcu-devkit/tree/master/Drivers "COM/Serial Port") download these drivers by clicking on the download.
### Selecting the appropriate board (Install the ESP8266 Board Package)
Before uploading the files in the microcontroller board, you need to select the appropriate board. In this project we are using Node MCU. To select this board, Click on Tool option and select board.
Click on File option, go to Perferences–>Settings. Enter the link and click “OK” to save your changes.
### Upload code on the Node MCU Microcontroller
Use the USB cable to connect your NodeMCU to the computer,you will see the blue onboard LED flicker when powered up. Click on Verify option to compile the code. Now click on Upload button to upload the code on the Node MCU board. The code will be uploaded to the Node MCU board.

<a href="https://github.com/aft145/aft145.github.io/network"><img alt="GitHub forks" src="https://img.shields.io/badge/IoT%20based%20Smart%20Home-Final%20Year%20Project-red"></a>

## Stats

[![My GitHub Stats](https://github-readme-stats.vercel.app/api/?username=aft145&count_private=true&theme=tokyonight&showicons=true)]()
