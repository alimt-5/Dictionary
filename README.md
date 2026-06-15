# 📖 Dictionary App

An offline-capable Android dictionary app built with **Kotlin** and **Jetpack Compose**. It fetches word definitions, phonetic transcriptions, and example sentences from the [Free Dictionary API](https://dictionaryapi.dev/).

## ✨ Features

- **Word Search:** Look up words and view their complete information, including phonetic spelling, part of speech, definitions, and example sentences.
- **Offline Caching:** Searched words are cached locally using **Room**, ensuring you can access their definitions even without an internet connection.
- **Modern UI:** User interface built entirely with Jetpack Compose and follows the Material Design 3 guidelines.
- **Dark/Light Theme:** Automatically adapts to your device's system theme for a seamless visual experience.
- **Clean Architecture:** Built with a separation of concerns using the **MVVM** pattern, making the codebase modular, testable, and maintainable.

## 🛠️ Tech Stack

| Technology | Purpose |
| :--- | :--- |
| **Kotlin** | Primary programming language |
| **Jetpack Compose** | Modern UI toolkit for building native interfaces |
| **Hilt** | Dependency Injection library |
| **Retrofit** | Type-safe HTTP client for network requests |
| **Room** | SQLite object mapping library for local data caching |
| **Coroutines & Flow** | For asynchronous and reactive programming |
| **Material Design 3** | Latest Material Design components and theming |

## 🚀 Setup & Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/alimt-5/Dictionary.git
    ```
2.  **Open the project** in Android Studio.
3.  Let Gradle sync and download all the necessary dependencies.
4.  **Run the app** on an emulator or a physical Android device (API 28+).

> **No API Key Required:** The app uses the public [Free Dictionary API](https://dictionaryapi.dev/), which does not require an API key. Please be aware of its public rate limits.

## 🎮 How to Use

1.  Launch the app to see the main search screen.
2.  Type a word into the search bar and tap the search icon or press the enter key on your keyboard.
3.  The app will display detailed information for the searched word, including:
    - Phonetic spellings (text and audio, if available).
    - A list of meanings, each with its part of speech.
    - Multiple definitions for each meaning.
    - Example sentences for each definition.

## 📁 Project Structure

The project follows a modular and clean architecture, organized by feature. Here is the structure of the main codebase:

```
app/src/main/java/com/example/dictionary/
├── DictionaryApp.kt               # Application class for Hilt DI
├── MainActivity.kt                # Main entry point for the app's UI
├── core/                          # Core utilities and modules
├── feature_dictionary/            # The main dictionary feature module
    ├── data/                      # Data layer (local/remote sources & repository)
    ├── di/                        # Hilt modules for dependency injection
    ├── domain/                    # Domain layer (business logic & use cases)
    └── presentation/              # Presentation layer (ViewModels & Composables)

```

## 🌐 API Reference

- **Base URL:** `https://api.dictionaryapi.dev/` 
- **Endpoint:** `/api/v2/entries/en/{word}`
- **Data Returned:** The API returns a JSON object containing phonetic transcriptions, definitions, parts of speech, example sentences, and audio URLs for the requested word.


## 👤 Contact

- **Author:** Alireza Motlagh.
- **GitHub:** [alimt-5](https://github.com/alimt-5)

**Project Link:** [https://github.com/alimt-5/Dictionary](https://github.com/alimt-5/Dictionary)

---

_Built with ❤️ using Kotlin and Jetpack Compose._
