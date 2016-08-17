[ ![Download](https://api.bintray.com/packages/pulimet/sh-pref/sh-pref/images/download.svg) ](https://bintray.com/pulimet/sh-pref/sh-pref/_latestVersion)

# ShPref-Library

ShPref is a small library that simplifies the way to get, set and remove shared preferences on Android.

# Installation

- Add the dependency from jCenter to your app's (not project) build.gradle file:

```sh
repositories {
    jcenter()
}

dependencies {
    compile 'net.alexandroid.shpref:sh-pref:1.0'
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


# How to use it

Firstable on initialization (at yours application class) you should pass default saving mode as a second argument. As you know, there are two options: APPLY or COMMIT. 


With ShPref library you can use following types: String, int, boolean, float and long.

- Put:
```sh
ShPref.put(R.string.some_key, "Sample text");
ShPref.put("HardCodedKey", 25);
```

- Get:
```sh
ShPref.getString(R.string.some_key, "Default value");
ShPref.getInt("HardCodedKey", 0); // 0 is default value
```

- Remove:
```sh
ShPref.remove(R.string.key_key);
ShPref.remove("HardCodedKey");
```


