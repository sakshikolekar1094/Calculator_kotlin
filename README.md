# 🧮 Calculator App (Android | Kotlin | Jetpack Compose | MVVM)

A modern Android calculator application built using **Kotlin** and **Jetpack Compose**, following **MVVM architecture**.  
The app provides a clean, responsive UI with real-time calculations and smooth user interaction.

---

## 🚀 Features

- ➕ Basic arithmetic operations (Addition, Subtraction, Multiplication, Division)
- 🔢 Decimal number support
- ⌫ Backspace functionality
- ❌ Clear (C) button to reset input
- ⚡ Real-time calculation updates
- 🎯 Error handling for invalid expressions
- 📱 Clean and responsive UI using Jetpack Compose

---

## 🏗 Architecture

This project follows **MVVM (Model-View-ViewModel)** architecture:

- **Model** → Holds UI state (`CalculatorState`)
- **ViewModel** → Contains all business logic and calculation handling
- **View (UI)** → Built using Jetpack Compose, observes state from ViewModel

State management is handled using **StateFlow** for reactive UI updates.

---

## 🛠 Tech Stack

- Kotlin
- Jetpack Compose
- MVVM Architecture
- StateFlow (Kotlin Coroutines)
- Android SDK

---

## 📂 Project Structure
com.example.ss
│
├── ui
│ └── CalculatorScreen.kt
│
├── viewmodel
│ └── CalculatorViewModel.kt
│
├── model
│ └── CalculatorState.kt
│
└── MainActivity.kt

## 🧠 What I Learned

- Implementing MVVM architecture in Android
- Using Jetpack Compose for modern UI development
- Managing UI state using StateFlow
- Separating UI and business logic for clean code structure
- Handling user input and error cases in real-time
