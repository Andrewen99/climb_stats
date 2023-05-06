package error

class RepoConcurrencyException(
    expectedLock: String,
    actualLock: String?
) : RuntimeException("Expected lock is $expectedLock while actual lock in db is ${actualLock ?: ""}")