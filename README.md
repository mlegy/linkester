# Linkester

[![Maven Central][mavenbadge-svg]][mavencentral]

Linkester is an Android library that aims to help Android developers test their deep links implementation.

The idea is to have a new launcher activity for your App (in debug builds) that will list all deep/app links available in your App and with a click on any of them you can test it.

The gradle plugin will collect all deep/app links registered for your App and will list them automatically.

You still also have the option to list some links manually (most used for example or some links with predefined query params)

## Features
- Auto colelct all deep/app links.
- Manually add some deep links.
- Write any deep link and click on "try it out" button to see how your App will react.
- Long press on a deep link will auto fill it in the edit text box so that you can edit or add query params to the deep link before testing it.

## Demo
<img src="https://github.com/mlegy/Linkester/blob/main/linkester_demo.gif" width="35%">

## Getting started

1. To use Linkester, add the Linkester dependency to your app’s build.gradle file:
	<details>
	<summary>Groovy</summary>

	```groovy
	dependencies {
	  // debugImplementation because Linkester should only run in debug builds.
	  debugImplementation 'com.mlegy.linkester:linkester:<current-version>'
	}
	```
	</details>
	<details open>
	<summary>Kotlin</summary>

	```kotlin
	dependencies {
	  // debugImplementation because Linkester should only run in debug builds.
	  debugImplementation("com.mlegy.linkester:linkester:<current-version>")
	}
	```
	</details>
2. There is 2 ways to list the deep links from your App into Linkester:

	> Please note that the gradle plugin will try to find all deep links in all modules in your App, so applying it only one module (app module) is enough to collect all deep links from other feature modules too.

	1. Automatically collect all deep links from the App.
	To be able to let Linkester gather all your deep/app links automatically we only need to apply Linkester Gradle plugin into your project.

		#### Build script snippet for use in all Gradle versions:
		<details>
		<summary>Groovy</summary>

		```groovy
		buildscript {
		  repositories {
		    maven {
		      url "https://plugins.gradle.org/m2/"
		    }
		  }
		  dependencies {
		    classpath "com.mlegy.linkester:gradle-plugin:<current-version>"
		  }
		}

		apply plugin: "com.mlegy.linkester"
		```
		</details>
		<details open>
		<summary>Kotlin</summary>

		```kotlin
		buildscript {
		  repositories {
		    maven {
		      url = uri("https://plugins.gradle.org/m2/")
		    }
		  }
		  dependencies {
		    classpath("com.mlegy.linkester:gradle-plugin:<current-version>")
		  }
		}

		apply(plugin = "com.mlegy.linkester")
		```
		</details>

	2. Manually add your most common used deep links yourself.
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
  
## Credits
- [Quadrant](https://github.com/gaelmarhic/Quadrant)
- [Deep links at Scale on iOS](https://medium.com/@albertodebo/deep-linking-at-scale-on-ios-1dd8789c389f)

🙏 If you like Linkester you can show support by starring ⭐ and/or sponsoring 💟 this repository.

## License

    Copyright 2021 Ahmad Melegy.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[mavenbadge-svg]: https://maven-badges.herokuapp.com/maven-central/com.mlegy.linkester/linkester/badge.svg
[mavencentral]: https://search.maven.org/artifact/com.mlegy.linkester/linkester
