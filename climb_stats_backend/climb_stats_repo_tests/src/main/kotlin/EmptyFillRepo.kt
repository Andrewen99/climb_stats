interface EmptyFillRepo {
    fun emptyRepo()
    fun fillRepo()

    fun emptyAndRefill() {
        emptyRepo()
        fillRepo()
    }
}