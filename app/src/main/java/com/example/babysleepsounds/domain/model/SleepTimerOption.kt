package com.example.babysleepsounds.domain.model

enum class SleepTimerOption(val minutes: Int, val label: String) {
    Fifteen(15, "15m"),
    Thirty(30, "30m"),
    Sixty(60, "60m"),
    OneTwenty(120, "120m")
}
