[ ![Download](https://api.bintray.com/packages/pulimet/utils/shpref/images/download.svg) ](https://bintray.com/pulimet/utils/shpref/_latestVersion)       [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ShPref-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/4744)
<a href="http://www.methodscount.com/?lib=net.alexandroid.utils%3Ashpref%3A1.6"><img src="https://img.shields.io/badge/Methods and size-104 | 8 KB-e91e63.svg"/></a> [![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)

# ShPref-Library

Simplifies the interaction with Shared Preferences on Android. (Bonus: Logger)

# Installation

- Add the dependency from jCenter to your app's (not project) build.gradle file:

```sh
repositories {
    jcenter()
}

dependencies {
    implementation 'net.alexandroid.utils:shpref:1.7' // Java version
    implementation 'net.alexandroid.utils:shprefkt:1.7' // Kotlin version
}
```


- Add init line in your application class as shown below:

```sh
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ShPref.init(this, ShPref.APPLY); // Java

        ShPrefKt shpref = ShPrefKt(this); // Kotlin
    }
}
```

* Also don't forget to add: android:name=".MyApplication" at your application tag in AndroidManifest.xml
```sh
 <application
        android:name=".MyApplication"
        ...>
```

# Release notes
* 1.7 - Android X migration + versions update + Added Kotlin version
* 1.6 - Logger separated (https://github.com/Pulimet/MyLogLibrary), appcompat dependency removed
* 1.5 - Logger methods for visual customization
* 1.4 - Added remove method to Editor
* 1.3 - Tests and bug fixes
* 1.2 - Save doubles support. Put null as a value bugfix. Logs are removed. Added contains and clear methods. New bonus Contextor and Logger classes. Chainning by using Editor. Ability to get values without default value.
* 1.1 - JavaDoc, readme file, bug fixes

# How to use it

On initialization (at yours application class) you should pass default saving mode as a second argument. 
As you know, there are two options to save: APPLY (faster and asynchronous) or COMMIT. 

With ShPref library you can use following types: String, int, boolean, float, double, long and lists with that types.
<br><br>
- Put:
<br><br>
```sh
// Save using a resource as a key
ShPref.put(R.string.some_key, "Sample text");

// Save using a hard coded string
ShPref.put("HardCodedKey", 25);

// Option to chain using Editor class
new ShPref.Editor()
    .put("key1", "key1value")
    .put("key2", "key2value")
    .put("key3", "key3value")
    .commit();

// Saving a List
ArrayList<Integer> list = new ArrayList<>();
for (int i = 0; i < 10; i++) {
    list.add(i);
}
ShPref.putList("myListKey", list);    
```

 <br> <br>
- Get:
 <br> <br>
```sh
// Get a value and if it not exist get a default value that was passed as a second argument
String myString = ShPref.getString(R.string.some_key, "Default value");
int myInt= ShPref.getInt("HardCodedKey", 0); // 0 is default value

// Get values without to set default values (if not exist: getString return null, getBoolena return false and other 0)
String myString = ShPref.getString(R.string.some_key);
int myInt= ShPref.getInt("HardCodedKey"); 

// Get value as an object
Object obj = ShPref.get(R.string_some_key); //excluding double

// Get a list
 List<Integer> list = ShPref.getListOfIntegers("myListKey");
```

 <br><br> 
- Contains:
 <br>  <br> 
```sh
// Checking for existence
boolean isContains = ShPref.contains(R.string.key_key);
```

 <br>  <br> 
- Remove:
 <br>  <br> 
```sh
ShPref.remove(R.string.key_key);
ShPref.remove("HardCodedKey");
```

 <br>  <br> 
- Clear:
 <br>  <br> 
```sh
// Remove all values from the preferences.
ShPref.clear();
```
 <br>  <br> 

- Mode forcing methods: <br>
For example if you have choosen as default APPLY mode and you need to save or remove something using COMMIT there are special methods for that case:
```sh
putC(); removeC(); // Force commit
putA(); removeA(); // Force apply
```
 <br>  <br>  <br> 
# Bonus

Option to get application Context from anywhere:
```sh
Context appContext = Contextor.getInstance().getContext();
```

 <br>  <br>  <br> 
# License

```
Copyright 2016 Alexey Korolev

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
