Here's a comprehensive English README for your repository based on my analysis of the code:

---

# 📖 Dictionary App

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-purple.svg?logo=kotlin)](https://kotlinlang.org)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-1.5.0-green.svg)](https://developer.android.com/jetpack/compose)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg)](https://android-arsenal.com/api?level=21)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A modern, offline-capable Android dictionary application built with **Kotlin** and **Jetpack Compose**. Query word definitions, pronunciations, and examples using the **Free Dictionary API** with a clean and responsive Material Design interface.

---

## 🚀 Features

- 🔍 **Instant Word Search** — Real-time lookup with autocomplete suggestions
- 📚 **Rich Definitions** — Displays multiple meanings, parts of speech, and usage examples
- 🔊 **Pronunciation Guide** — Phonetic transcriptions and audio playback (where available)
- 💾 **Offline Caching** — Previously searched words are cached for offline access
- 🌙 **Dark/Light Theme** — Seamlessly adapts to system theme settings
- 📱 **Responsive UI** — Built with Jetpack Compose for a modern, adaptive layout
- 🧩 **Clean Architecture** — Separation of concerns with MVVM pattern

---

## 🛠 Tech Stack

| Technology | Purpose |
|------------|---------|
| **Kotlin** | Primary programming language |
| **Jetpack Compose** | Modern declarative UI toolkit |
| **Hilt** | Dependency Injection |
| **Retrofit** | Network requests to Free Dictionary API |
| **Room** | Local caching of word definitions |
| **Coroutines & Flow** | Asynchronous operations and reactive streams |
| **Material Design 3** | UI components and theming |

---

## 📸 Screenshots

| Search Screen | Word Details |
|---------------|---------------|
| *(Add your screenshots here)* | *(Add your screenshots here)* |

---

## 🔧 Setup Instructions

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Android SDK with minimum API level 21

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/alimt-5/Dictionary.git
   ```
2. Open the project in Android Studio.
3. Let Gradle sync and download dependencies.
4. Build and run the app on an emulator or physical device.

### Configuration
No API key required — the app uses the public **Free Dictionary API** (rate limits apply).

---

## 📱 Usage

1. Launch the app to see the search screen.
2. Type a word in the search bar.
3. Tap the search icon or press enter.
4. View detailed word information including:
   - Phonetic spelling
   - Part of speech (noun, verb, adjective, etc.)
   - Definition(s)
   - Example sentences
   - Synonyms (where available)

---

## 🏗 Project Structure

```
app/src/main/java/com/example/dictionary/
├── feature_dictionary/
│   ├── data/
│   │   ├── local/          # Room entities & DAO
│   │   ├── remote/         # Retrofit API service & DTOs
│   │   └── repository/     # Implementation of repository pattern
│   ├── domain/
│   │   ├── model/          # Domain models
│   │   └── use_case/       # Business logic use cases
│   └── presentation/
│       ├── viewmodel/      # State hoisting & event handling
│       └── components/     # Reusable Compose UI components
├── core/
│   ├── di/                 # Hilt modules
│   ├── util/               # Utility classes & extensions
│   └── network/            # Network connectivity helper
├── MainActivity.kt         # Entry point with Compose navigation
└── DictionaryApp.kt        # Application class for dependency injection
```

---

## 🔌 API Reference

This app uses the **Free Dictionary API**:
- **Base URL**: `https://api.dictionaryapi.dev/`
- **Endpoint**: `/api/v2/entries/en/{word}`

Example response includes:
- Phonetic transcriptions
- Multiple meanings with definitions & examples
- Audio URLs for pronunciation

*For full API documentation, visit [dictionaryapi.dev](https://dictionaryapi.dev/)*

---

## 🤝 Contributing

Contributions are welcome! If you'd like to improve the app:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

Please ensure your code follows the existing style and includes appropriate tests.

---

## 📄 License

This project is open source and available under the **MIT License**.

---

## 🙏 Acknowledgments

- [Free Dictionary API](https://dictionaryapi.dev/) for providing the word definitions
- Google for Jetpack Compose and Android development tools
- All open-source contributors whose libraries made this project possible

---

## 📬 Contact

**Ali M.T.** — [GitHub](https://github.com/alimt-5)

Project Link: [https://github.com/alimt-5/Dictionary](https://github.com/alimt-5/Dictionary)

---

*Built with ❤️ using Kotlin and Jetpack Compose*
