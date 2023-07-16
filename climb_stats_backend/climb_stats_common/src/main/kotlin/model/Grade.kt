package model

enum class Grade(
    val euroGrade: String
) {
    FIVE_A("5a"),
    FIVE_A_PLUS("5a+"),
    FIVE_B("5b"),
    FIVE_B_PLUS("5b+"),
    FIVE_C("5c"),
    FIVE_C_PLUS("5c+"),
    SIX_A("6a"),
    SIX_A_PLUS("6a+"),
    SIX_B("6b"),
    SIX_B_PLUS("6b+"),
    SIX_C("6c"),
    SIX_C_PLUS("6c+"),
    SEVEN_A("7a"),
    SEVEN_A_PLUS("7a+"),
    SEVEN_B("7b"),
    SEVEN_B_PLUS("7b+"),
    SEVEN_C("7c"),
    SEVEN_C_PLUS("7c+"),
    NONE("none");

    companion object {
        fun fromStr(str: String) : Grade {
            return Grade.values().firstOrNull { it.euroGrade == str } ?: Grade.NONE
        }
    }
}