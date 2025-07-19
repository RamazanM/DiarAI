package com.ramazanmutlu.diarai.data.entities

enum class JournalStyle(val displayName: String, val promptTag: String) {
    FRIENDLY("Kanka", "Write like a close friend, using casual slang."),
    FORMAL("Resmi", "Write in a professional tone suitable for business records."),
    THERAPEUTIC("Terapi", "Write with compassion, like a therapist reflecting on emotions."),
    POETIC("Şairane", "Write in a poetic and artistic way, full of metaphors."),
    MINIMAL("Minimal", "Write using simple and minimal language.")
}