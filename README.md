Popa
====
Popa aims to solved user requirements in Yangon who want to hire taxi easily like GrabTaxi and Uber.

Using Libiary
-------------

Now I am using Android annotation and Dagger 

[Android Annotation](https://github.com/excilys/androidannotations) (An open source framework that speeds up Android development)

[Dagger](square.github.io/dagger) (A fast dependency injector for Android and Java) 

[Google Play Service](http://developer.android.com/google/play-services/index.html) for Showing map


To Do
-----
* Verify the scope and design
* Database integration

Building the app
-----------------
* Clone the repo with this command and import into the Android Studio. 
```bash
git clone git@github.com:indexer/popa.git
./gradlew clean
./gradlew build
```
* Sync with gradle and you should be able to run the app.
* Add your own [Google Maps API](https://developers.google.com/maps/documentation/android/start) Key by creating a String resource file with the following contents. Typically, the file should be under `app/src/main/res/values/`
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
  <!-- map key -->
  <string name="googlemap"> Put your key here </string>
</resources>
```
* You may encounter `NullPointerException` while running the app because of Google Maps. We're aware of that and trying several workarounds to solve it.

Contributing
------------

 1. Fork it
 2. Create your feature branch (`git checkout -b my-new-feature`)
 3. Commit your changes (`git commit -am 'Added some feature'`)
 4. Push to the branch (`git push origin my-new-feature`)
 5. Create new Pull Request
