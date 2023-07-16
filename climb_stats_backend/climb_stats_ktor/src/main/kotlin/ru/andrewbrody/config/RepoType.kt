package ru.andrewbrody.config

enum class RepoType(val type: String) {
    IN_MEMORY("inmemory"), MONGODB("mongodb"), NONE("");

    companion object {
        fun fromString(str: String?) : RepoType {
            return RepoType.values().firstOrNull { it.type == str} ?: NONE
        }
    }
}
