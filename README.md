## How to install

* Make sure you have [ActionBarSherlock](github.com/mstaessen/actionbarsherlock) in your workspace. You can use Michiels [repo](github.com/mstaessen/actionbarsherlock) for that.
* Make sure you have [AccordionWidget](github.com/hamsterready/android-accordion-view) in your workspace. You _must_ use Brams fork, which you can find [here](github.com/bgotink/android-accordion-view)
* Add the project found at 
```
/Path/To/Your/AndroidSDK/extras/google/google_play_services/libproject/google-play-services_lib
```
to your workspace. If you don't have this project yet, use the Android SDK Manager to install "Google Play services".

* Close your eclipse (!)
* Clone this repository in your workspace. Copy AndroidManifest.xml.dist to AndroidManifest.xml and enter your API key (see below).
* Open eclipse, and add the project to the workspace. You will have to fix the reference to the google-play-services_lib project, as the current configuration works for Brams installation of Android.
* You can now build and run the project.

## API Key

You'll need a Google API key:

* Create a new project in the google API console.
* Get your signing key by issuing the following command:
```
keytool -list -keystore ~/.android/debug.keystore
```
The password is 'android' (without the quotes). The key signature you need is androiddebugkey.
* Create a new Android Key in the 'API Access' tab of the Google API Console
* Enter the following:
```
<hash of the key you just got using the keytool command>;com.moodspaces
```
* Go to the 'Services' tab and activate 'Google Maps Android API v2'
* Enter the key you've generated in AndroidManifest.xml
