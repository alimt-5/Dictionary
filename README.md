```markdown
# Dictionary App

An offline-capable Android dictionary app built with **Kotlin** and **Jetpack Compose**. It fetches word definitions, pronunciations, and examples from the [Free Dictionary API](https://dictionaryapi.dev/).

## Features

- **Word Search** — Look up words and view definitions, phonetic spellings, parts of speech, and examples.
- **Offline Caching** — Previously viewed words are cached using Room for offline access.
- **Dark/Light Theme** — Adapts automatically to the system theme.
- **Clean Architecture** — Separation of concerns using the MVVM pattern.

## Tech Stack

| Technology | Purpose |
|------------|---------|
| **Kotlin** | Primary language |
| **Jetpack Compose** | UI toolkit |
| **Hilt** | Dependency Injection |
| **Retrofit** | Network requests |
| **Room** | Local caching |
| **Coroutines & Flow** | Async operations |
| **Material Design 3** | UI components |

## Requirements

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK with **minimum API level 28 (Android 9.0)**

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/alimt-5/Dictionary.git
   ```
2. Open the project in Android Studio.
3. Let Gradle sync and download dependencies.
4. Build and run the app on an emulator or physical device.

No API key is required — the app uses the public Free Dictionary API (subject to rate limits).

## Usage

1. Launch the app to see the search screen.
2. Type a word in the search bar and tap the search icon or press enter.
3. View detailed word information: phonetic spelling, part of speech, definition(s), and example sentences.

## Project Structure

```
app/src/main/java/com/example/dictionary/
├── MainActivity.kt                # Entry point
├── DictionaryApp.kt               # Application class for DI
├── core/                          # Core utilities and DI modules
├── feature_dictionary/            # Main dictionary feature
│   ├── data/                      # Local/remote data sources & repository
│   ├── domain/                    # Domain models and use cases
│   └── presentation/              # ViewModels and Compose UI components
└── ui/                            # Theme and UI utilities
```

## API Reference

- **Base URL**: `https://api.dictionaryapi.dev/`
- **Endpoint**: `/api/v2/entries/en/{word}`

Returns phonetic transcriptions, definitions, examples, and audio URLs.

## License

This project is **not open source**. All rights are reserved.  
No permission is granted to use, copy, modify, or distribute the code without explicit written consent from the author.

## Contact

**Ali M.T.** — [GitHub](https://github.com/alimt-5)

Project Link: [https://github.com/alimt-5/Dictionary](https://github.com/alimt-5/Dictionary)

---

_Built with Kotlin and Jetpack Compose._
```

This README no longer overclaims features (autocomplete removed), correctly states `minSdk = 28`, matches the actual project structure, and has no mention of MIT License.
