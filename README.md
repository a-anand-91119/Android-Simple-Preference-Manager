<h1 align="center">Android Simple Preference Manager</h1>
<p align="center">
  <a href="https://app.codacy.com/manual/a-anand-91119/Android-Simple-Preference-Manager?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=a-anand-91119/Android-Simple-Preference-Manager&amp;utm_campaign=Badge_Grade_Dashboard" rel="nofollow"><img src="https://camo.githubusercontent.com/8bfcb96fada23923d178481242a6574bd3388f1d/68747470733a2f2f6170692e636f646163792e636f6d2f70726f6a6563742f62616467652f47726164652f6134646434613739393266663465393738666465386565363038363137366564" alt="Codacy Badge" data-canonical-src="https://api.codacy.com/project/badge/Grade/a4dd4a7992ff4e978fde8ee6086176ed" style="max-width:100%;"></a>
  <a href="https://jitpack.io/#a-anand-91119/Android-Simple-Preference-Manager"> <img src="https://jitpack.io/v/a-anand-91119/Android-Simple-Preference-Manager/month.svg" /></a>
  <a href="https://jitpack.io/#a-anand-91119/Android-Simple-Preference-Manager"> <img src="https://jitpack.io/v/a-anand-91119/Android-Simple-Preference-Manager.svg" /></a>
  <a href="https://circleci.com/gh/a-anand-91119/Android-Simple-Preference-Manager/tree/library"> <img src="https://circleci.com/gh/a-anand-91119/Android-Simple-Preference-Manager/tree/library.svg?style=shield" /></a>
  <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-blue.svg"/></a>
  <br /><br />
  Simple Preference Manager that help to store and retrive data easily from Android SharedPreferences.
</p>


# Support Simple Preference Manager

Simple Preference Manager is an independent project with ongoing development and support. If you wish to support my works by donating please consider

  - [One-time donation via PayPal](https://www.paypal.me/notyouraveragedev)
  - [Become a backer or sponsor on Patreon](https://www.patreon.com/not_your_average_dev)

<a href="https://www.patreon.com/join/not_your_average_dev?" alt="Become a Patron"><img src="https://c5.patreon.com/external/logo/become_a_patron_button.png" /></a>

# Simple Preference Manager

Simple Preference Manager enables to perform save and retrieve operations on Android Shared Preferences with ease. Simple Preference Manager supports
 
  - Storing and retrieving objects such as String, Integer, Float, Long, Boolean
  - Storing and retrieving Set&lt;String&gt;
  - Storing and retrieving any custom objects that are Serializable
  - Storing all entries from Map<String, ?>

## How to integrate into your app

Integrating the library into you app is extremely easy. A few changes in the build gradle and your all ready to use Simple Preference Manager. Make the following changes.

Step 1. Add the JitPack repository to your build file. Add it in your root build.gradle at the end of repositories:

```java
allprojects {
  repositories {
    ...
    maven { url "https://jitpack.io" }
  }
}
```
Step 2. Add the dependency

```java
dependencies {
        implementation 'com.github.a-anand-91119:Android-Simple-Preference-Manager:<latest-version>'
}
```

## How to use the library
Once you integrated the library in your project but **how do you use it**? Well its really easy just follow the steps below.

```java

// Using Default SharedPreferences
// Create an instance of SimplePreferenceManager using SimplePreferenceManagerBuilder
SimplePreferenceManager preferenceManager = new SimplePreferenceManager.SimplePreferenceManagerBuilder(this)
                  .build();

// Saving and Retrieving Objects
// Enabling Object Support Using Builder
SimplePreferenceManager preferenceManager = new SimplePreferenceManager.SimplePreferenceManagerBuilder(this)
                .withObjectStorageSupport()
                .build();

// Create SharedPreference File With Custom Names
SimplePreferenceManager preferenceManager = new SimplePreferenceManager.SimplePreferenceManagerBuilder(this)
                .havingFileName("CustomFileName")
                .build();

// Specifying the Operation Mode
SimplePreferenceManager preferenceManager = new SimplePreferenceManager.SimplePreferenceManagerBuilder(this)
                .havingFileName("CustomFileName")
                .usingOperationMode(Context.MODE_PRIVATE)
                .build();

// Saving Data To Shared Preferences
preferenceManager.saveString("Key_1", "Value");
preferenceManager.saveLong("Key_2", (long) 100);
preferenceManager.saveFloat("Key_3", (float) 1000.1);
preferenceManager.saveInteger("Key_4", 10);
preferenceManager.saveBoolean("Key_5", true);

// Retrieving Data From Shared Preferences
String s = preferenceManager.fetchString("Key_1");
Long l = preferenceManager.fetchLong("Key_2");
Float f = preferenceManager.fetchFloat("Key_3");
Integer i = preferenceManager.fetchInteger("Key_4");
Boolean b = preferenceManager.fetchBoolean("Key_5");


// Save Objects
preferenceManager.saveObject("Key_6", new CustomObject("data", "99"));
// Retrieving Objects
(CustomObject)preferenceManager.fetchObject("Key_6", CustomObject.class);

// Saving Map of Key-Value Pairs
Map<String, Float> floatMap = new HashMap<>();
floatMap.put("ONE_F", 1f);
floatMap.put("TWO_F", 2f);
floatMap.put("THREE_F", 3f);
preferenceManager.putAll(floatMap);
// Retrieve the data
Float f = preferenceManager.fetchFloat("ONE_F");


// Additional Options
// Remove all data from Shared Preference File
preferenceManager.removeAll();

// Remove particular Key-Value Pair from Shared Preferences
preferenceManager.removeData("KEY");

// check whether Shared Preference contains particular key
Boolean contains = preferenceManager.contains("KEY");

```
That's pretty much it.

## Author
Maintained by A Anand [Not Your Average Dev](https://notyouraveragedev.in)

## Contribution

  - Bug reports and pull requests are welcome.

## Change Log
  - Version 1.1
     - added support for storing and retrieving set of strings
  - version 1.0
     - initial commit
    
## License
  ```
  MIT License

  Copyright (c) 2020 A Anand

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.
  ```
