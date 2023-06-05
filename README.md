# Covid Tracker

Informative app that shows a record of covid daily cases registered in USA and sorted by date. 
<br/>
<br/>

The project has been implemented following the best practices in Android Development and Architecture such as MVVM model, dependency injection, 
consuming rest services and storing retrieved data into local database (SQLITE) as a single source of truth. 
<br/>
<br/>

The app allows choose favorite daily 
cases and see them in favorites section.
<br/>
<br/>

### Pre-Requisites

For development, the latest version of Android Studio is required. The latest version can be downloaded from [here](https://developer.android.com/studio/).
<br/>
<br/>

### Getting Started

This sample uses the Gradle build system. To build this project, use the "gradlew build" command or use "Import Project" in Android Studio.
<br/>
<br/>

### Screenshoots

![1](https://github.com/FreddyRobert17/covid-tracker/assets/57290556/76670660-2502-4236-b6de-21022fe1ff27)
![2](https://github.com/FreddyRobert17/covid-tracker/assets/57290556/5b44f917-62d8-458a-a2f0-4e390de14798)
<br/>
<br/>

### Used Libraries

* Arquitecture - A collection of libraries for design robust, testable and maintainable apps.
		 <br/>
     -Navigation - Handle everything needed for in-app navigation.\
     -LiveData - Build data objects that notify views when the underlying database changes.\
     -ViewBinding - Allow us interact easily with views.\
     -ViewModel - Store UI-related data that isn't destroyed on app rotations.
		 <br/>
		 <br/>
     
* UI - Details on why and how to use UI Components in your apps - together or separate

     -Activities\
     -Fragments -  A basic unit of composable UI.\
		 <br/>
     
* Third party and miscellaneous libraries

   -Hilt for dependency injection\
	 -Kotlin Coroutines for managing background threads with simplified code and reducing needs for callbacks\
   -Room for store data locally and use it as a single source of truth.\
	 -Retrofit - Library that allows transfer data between the cloud (server) to the UI of the app.\
		 
