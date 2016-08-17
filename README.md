# ShPref-Library
Shared Preferences helper library.

ShPref is a small library that simplfies  getting and setting shared preferences on Android.

# Installation

- Add the dependency from jCenter to your app's (not project) build.gradle file:

```sh
repositories {
    jcenter()
}

dependencies {
    compile 'net.alexandroid.sh-pref:sh-pref:1.0'
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

Coming soon...
