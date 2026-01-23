# TerminalNotes

The app focuses on simplicity, performance, and maintainability, following recommended Android guidelines and patterns.

## Features

* Create and manage notes
* Fully written in **Jetpack Compose** (no XML)
* Navigation using **Navigation Compose**
* MVVM architecture
* Material 3 UI
* Local persistence (Room / local storage – if applicable)
* Clean state management via ViewModels

---

## Architecture

The project follows **MVVM (Model–View–ViewModel)** with a clear separation of concerns:

UI (Compose Screens)

ViewModel (state + business logic + external API)

This makes the codebase:

* Easy to test
* Easy to scale
* Easy to reason about

---

## Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose + Material 3
* **Architecture:** MVVM
* **Navigation:** Navigation Compose
* **Async:** Kotlin Coroutines / Flow
* **Build:** Gradle (KTS)
* **IDE:** Android Studio


## App Distribution

* Published on **Google Play (internal Testing)**
* Uses **Android App Bundle (AAB)** for releases
https://play.google.com/apps/internaltest/4700775529584102583

## Why This Project Exists

This project was built to:

* Demonstrate modern Android development practices
* Serve as a **portfolio / CV project**
* Show clean architecture and Jetpack Compose proficiency

It intentionally avoids unnecessary complexity and focuses on **clarity, correctness, and scalability**.

## License

This project is licensed under the MIT License. Feel free to explore, learn, and adapt.
