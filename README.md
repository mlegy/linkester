

# Linkester

Linkester is an Android library that aims to help Android developers test their deep links implementation.

The idea is to have a new launcher activity for your App (in debug builds) that will list all deep/app links available in your App and with a c lick on any of them you should be able to test it.

# Features:
- Auto colelct all deep/app links.
- Manually add some deep links.
- Write any deep link and click on "try it out" button to see how your App will react.
- Long press on a deep link will auto fill it in the edit text box so that you can edit or add query params to the deep link before testing it.

# Demo
<img src="https://github.com/mlegy/Linkester/blob/main/linkester_demo.gif" width="35%">

# How to integrate?
There is 2 ways to list the deep links from your App into Linkester:
- Automatically collect all deep links from the App.
To be able to let Linkester gather all your deep/app links automatically we only need to apply Linkester Gradle plugin into your project.
	```kotlin
	buildscript {
	    repositories {
	        maven { url "https://plugins.gradle.org/m2/" }
	    }
	    dependencies {
	        classpath "gradle.plugin.com.melegy.linkseter:linkester:$version"
	    }
	}
	```
	And then apply it into your Android module
	```kotlin
	plugins {  
	  ..
	  id 'com.melegy.linkester'  
	}
	```
- Manually add your most common used deep links yourself.
The second option you can use is to provide a json file with most common deep links in your app.
You can do that by creating a raw json file in `res/values` the file must be named `linkester_manually_added_links.json`
It should be a list of deep links where each link is an object of `title` and `link` fields
Example:
	```json
	[  
	  {  
	    "title": "Home",  
	    "link": "https://app.melegy.com/home"  
	  },  
	  {  
	    "title": "Item details",  
	    "link": "https://app.melegy.com/list?id=12"  
	  }
	]
	```
  
## Credits:
- [Quadrant](https://github.com/gaelmarhic/Quadrant) gardle plugin.
- [Deep links at Scale on iOS](https://medium.com/@albertodebo/deep-linking-at-scale-on-ios-1dd8789c389f)
