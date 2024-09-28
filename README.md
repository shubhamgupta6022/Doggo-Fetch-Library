# Doggo-Fetch-Library
The Doggo Fetch Library is a simple Android library fetches random dog images from the [Dog API](https://dog.ceo/dog-api/). With this library, you can easily get one or multiple dog images and navigate them easily.

# Getting Started
## Step 1: Add JitPack to your project
To include the library in your project, add the JitPack repository to your root settings.gradle file:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}
```

## Step 2: Add the dependency
In the dependencies section of your app-level build.gradle file, add the following line to include the Doggo Fetch Library:

```groovy
dependencies {
    implementation 'com.github.shubhamgupta6022:Doggo-Fetch-Library:$latest_version'
}
```

## Usage
### Initialization
To start using the DoggoFetch library, initialize it in your application or activity class.
```kotlin
import com.sgupta.doggofetch.DogBreedSDK

val doggoFetch = DoggoFetch.getInstance(context)
```

## Helper Functions
### The library provides four main suspend functions:

### 1. Get a Random Dog Image
```kotlin
suspend fun getImage(): String {
    return doggoFetch.getImage()
}
```

### 2. Get Multiple Dog Images
```kotlin
suspend fun getImages(number: Int): List<String> {
    return doggoFetch.getImages(number)
}
```

### 3. Get Next Dog Image
```kotlin
suspend fun getNextImage(): String {
    return doggoFetch.getNextImage()
}
```

### 4. Get Previous Dog Image
```kotlin
suspend fun getPreviousImage(): String {
    return doggoFetch.getPreviousImage()
}
```

# Example
Here’s how you can use the suspend functions in a coroutine:
```kotlin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var doggoFetch: DoggoFetch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doggoFetch = DoggoFetch.getInstance(this)

        // Launch a coroutine to fetch and display a single dog image
        CoroutineScope(Dispatchers.IO).launch {
            val imageUrl = doggoFetch.getImage()
            // Handle the result, update the UI on the main thread
        }

        // Fetch and display multiple images
        CoroutineScope(Dispatchers.IO).launch {
            val imagesList = doggoFetch.getImages(5)
            // Handle the result
        }
    }
}
```

## Coroutine Support
All helper functions are suspend functions, meaning they must be called within a coroutine. You can use CoroutineScope, viewModelScope, or any other coroutine context as per your use case.

# Screenshots
<div style="display: flex; justify-content: center; align-items: center;">
  <img src="/docs/media_1.jpeg" style="margin: 0 20px;" height="600px">
  <img src="/docs/media_2.jpeg" style="margin: 0 20px;" height="600px">
  <img src="/docs/media_3.jpeg" style="margin: 0 20px;" height="600px">
</div>



