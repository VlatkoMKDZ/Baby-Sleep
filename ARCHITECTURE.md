# Baby Sleep Sounds Project Architecture

This iteration is intentionally **Compose-first**. It generates the app screens with mock state only and does **not** implement audio playback, media services, timer countdowns, or settings persistence yet.

## Folder structure

```text
Baby-Sleep/
├── app/
│   ├── build.gradle.kts
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/example/babysleepsounds/
│       │   ├── MainActivity.kt
│       │   ├── data/mock/
│       │   │   └── MockSleepSoundsData.kt
│       │   ├── domain/model/
│       │   │   ├── SleepSound.kt
│       │   │   ├── SleepSoundsUiState.kt
│       │   │   ├── SleepTimerOption.kt
│       │   │   └── SoundPlaybackState.kt
│       │   ├── ui/components/
│       │   │   ├── SoundCard.kt
│       │   │   └── TimerSection.kt
│       │   ├── ui/screens/
│       │   │   └── SleepSoundsScreen.kt
│       │   ├── ui/theme/
│       │   │   └── Theme.kt
│       │   └── viewmodel/
│       │       └── SleepSoundsViewModel.kt
│       └── res/
│           ├── drawable/ic_launcher_foreground.xml
│           └── values/*.xml
├── build.gradle.kts
├── gradle.properties
├── settings.gradle.kts
└── README.md
```

## Screen-first data flow

1. `MockSleepSoundsData` provides hard-coded sound card and timer preview state.
2. `SleepSoundsViewModel` owns an in-memory `StateFlow<SleepSoundsUiState>`.
3. `MainActivity` collects that state and renders `SleepSoundsScreen`.
4. Compose components emit mock UI events back to the ViewModel.
5. The ViewModel updates mock state so reviewers can interact with the UI before audio exists.

## File-by-file explanation

### Gradle and project files

- `.gitignore` excludes build outputs, IDE metadata, Gradle caches, and local machine files.
- `settings.gradle.kts` names the project, includes the `:app` module, and defines plugin/dependency repositories.
- `build.gradle.kts` declares Android, Kotlin, and Compose plugin versions at the root.
- `gradle.properties` configures AndroidX, Kotlin style, non-transitive R classes, and Gradle JVM memory.
- `app/build.gradle.kts` configures the Android app namespace, SDK levels, Compose support, Java/Kotlin compatibility, and UI dependencies.
- `README.md` gives a product-level overview and explains that this revision is UI-only with mock data.
- `ARCHITECTURE.md` documents the current screen-first structure and responsibilities.

### Android entry point

- `app/src/main/AndroidManifest.xml` declares only the launch activity and app metadata. It intentionally has no audio, foreground-service, notification, wake-lock, or storage permissions in this UI-only phase.
- `app/src/main/java/com/example/babysleepsounds/MainActivity.kt` creates the ViewModel, collects mock UI state, applies the app theme, and renders `SleepSoundsScreen`.

### Mock data and state management

- `data/mock/MockSleepSoundsData.kt` provides the mock list of six sounds, initial play/pause states, volumes, and a selected timer preview.
- `domain/model/SleepSound.kt` defines the six sound types and their display metadata without audio resource IDs.
- `domain/model/SleepTimerOption.kt` defines the visible timer choices: 15, 30, 60, and 120 minutes.
- `domain/model/SoundPlaybackState.kt` represents one sound card's mock UI state.
- `domain/model/SleepSoundsUiState.kt` represents the complete screen state for the sound grid and timer preview.
- `viewmodel/SleepSoundsViewModel.kt` updates mock state for play/pause toggles, slider changes, timer selection, and timer clearing.

### Compose UI

- `ui/screens/SleepSoundsScreen.kt` lays out the top app bar, helper copy, adaptive sound-card grid, and timer section.
- `ui/components/SoundCard.kt` renders one sound's icon, name, mock play/pause button, and volume slider.
- `ui/components/TimerSection.kt` renders timer choice chips and mock timer preview actions.
- `ui/theme/Theme.kt` defines Material 3 light/dark color schemes and applies system dark mode support.

### Resources

- `res/drawable/ic_launcher_foreground.xml` is the vector launcher icon.
- `res/values/strings.xml` stores the app name.
- `res/values/colors.xml` stores the app accent color.
- `res/values/styles.xml` provides the no-action-bar Android theme used before Compose draws content.

## Deferred implementation

The following features are intentionally deferred until after screen review:

- Local audio assets and playback.
- Audio manager and foreground media service.
- Real sleep timer countdown and fade-out.
- Persistent settings storage.
- Notification permission and wake-lock handling.
