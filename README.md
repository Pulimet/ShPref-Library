[ ![Download](https://api.bintray.com/packages/pulimet/utils/shpref/images/download.svg) ](https://bintray.com/pulimet/utils/shpref/_latestVersion)

# ShPref-Library

Simplifies the interaction with Shared Preferences on Android.

# Installation

- Add the dependency from jCenter to your app's (not project) build.gradle file:

```sh
repositories {
    jcenter()
}

dependencies {
    compile 'net.alexandroid.utils:shpref:1.2'
}
```


- Add init line in your application class as shown below:

```sh
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ShPref.init(this, ShPref.APPLY);
    }
}
```

* Also don't forget to add: android:name=".MyApplication" at your application tag in AndroidManifest.xml
```sh
 <application
        android:name=".MyApplication"
        ...>
```

# How to use it

On initialization (at yours application class) you should pass default saving mode as a second argument. 
As you know, there are two options to save: APPLY (faster and asynchronous) or COMMIT. 


With ShPref library you can use following types: String, int, boolean, float, double, long and lists with that types.

 <br> 
- Put:
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

 <br>
- Get:
```sh
// Get a value and if it not exist get a default value that was passed as a second argument
String myString = ShPref.getString(R.string.some_key, "Default value");
int myInt= ShPref.getInt("HardCodedKey", 0); // 0 is default value

// Get values without to set default values (if not exist: getString return null, getBoolena return false and other 0)
String myString = ShPref.getString(R.string.some_key);
int myInt= ShPref.getInt("HardCodedKey"); 

// Get value as an object
Object obj = ShPref.get(R.string_some_key);

// Get a list
 List<Integer> list = ShPref.getListOfIntegers("myListKey");
```

 <br> 
- Contains:
```sh
// Checking for existence
boolean isContains = ShPref.contains(R.string.key_key);
```

 <br> 
- Remove:
```sh
ShPref.remove(R.string.key_key);
ShPref.remove("HardCodedKey");
```

 <br> 
- Clear:
```sh
// Remove all values from the preferences.
ShPref.clear();
```
 <br> 

- Mode forcing methods: <br>
For example if you have choosen as default APPLY mode and you need to save or remove something using COMMIT there are special methods for that case:
```sh
putC(); removeC(); // Force commit
putA(); removeA(); // Force apply
```

# Bonus

Option to get application Context from anywhere:
```sh
Context appContext = Contextor.getInstance().getContext();
```

Logger class:
```sh
// Just turn it on
MyLog.showLogs(true);

MyLog.d("Debug test");
MyLog.e("Error test");

// Option to change a tag
MyLog.setTag("NEW TAG");
MyLog.i("Testing setTag method ^)");
```

![alt tag](http://www.alexandroid.net/downloads/bintray_libs/logs.png)
