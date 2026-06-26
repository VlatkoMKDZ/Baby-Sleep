# Baby Sleep Sounds

A native Android app shell built with Kotlin, Jetpack Compose, and Material 3.

This revision is intentionally **screen-first**: it contains the complete Compose UI using mock data, but it does not implement audio playback yet.

## Current UI features

- Rain, Ocean, Fan, White Noise, Brown Noise, and Heartbeat sound cards
- Mock play/pause state for every sound
- Mock individual volume sliders
- Mock Sleep Timer section with 15, 30, 60, and 120 minute choices
- Simple Material 3 design
- Light and dark theme support
- No backend and no advertisements

## Deferred until the audio phase

- Bundled local audio files
- Audio manager
- Foreground playback service
- Real timer countdown and fade-out
- Persisted volume/settings storage

## Architecture

See [ARCHITECTURE.md](ARCHITECTURE.md) for the complete folder structure and file-by-file explanation.

## Build

Open the project in Android Studio or run:

```bash
JAVA_HOME=$HOME/.local/share/mise/installs/java/17.0.2 gradle :app:assembleDebug
```
