# Dictionary App

A modern Android dictionary app built with **Kotlin, Jetpack Compose, Material 3, MVVM, and Clean Architecture**.

The app searches English words using the **Free Dictionary API**, displays meanings and definitions, and caches results locally with **Room** for faster access and offline reuse.

## Features

- Search English words with debounced input
- Display phonetics, pronunciation audio, meanings, definitions, examples, synonyms, and antonyms
- Local caching with Room
- Remote data loading with Retrofit
- Reactive UI state using Kotlin Coroutines and Flow
- Centralized error handling
- Dependency injection with Hilt

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- MVVM
- Clean Architecture
- Hilt
- Retrofit + Gson
- Room
- Coroutines + Flow
- JUnit

## Architecture

The project uses a **feature-based Clean Architecture** structure:

```text
feature_dictionary/
├── data/
│   ├── local/
│   ├── remote/
│   ├── mapper/
│   ├── repository/
│   └── util/
│
├── domain/
│   ├── model/
│   ├── repository/
│   └── use_case/
│
└── presentation/
    ├── audio/
    ├── WordInfoScreen.kt
    ├── WordInfoViewModel.kt
    ├── WordInfoState.kt
    └── WordInfoEvent.kt
```

Dependency direction:

```text
Presentation → Domain ← Data
```

The **Domain** layer contains business logic and repository contracts, while **Data** handles Retrofit, Room, DTOs, entities, and mapping.

## Data Flow

```text
User Input
    ↓
ViewModel
    ↓
SearchWordUseCase
    ↓
Repository
    ↓
Room Cache + Remote API
    ↓
Mapper
    ↓
Domain Model
    ↓
ViewModel State
    ↓
Compose UI
```

## API

This project uses the **Free Dictionary API**:

```text
https://dictionaryapi.dev/
```

Example:

```text
GET https://api.dictionaryapi.dev/api/v2/entries/en/hello
```

## Local Cache

Dictionary results are stored in a local **Room** database. Cached data can be shown immediately while fresh data is requested from the API.

## Testing

The project includes unit tests for `SearchWordUseCase`, including query normalization and blank-query handling.

## Getting Started

Clone the repository and open it in **Android Studio**.

```bash
git clone https://github.com/alimt-5/Dictionary.git
```

Then sync Gradle and run the `app` module on an Android device or emulator.

## Author

**Alireza Motlagh**

GitHub: https://github.com/alimt-5