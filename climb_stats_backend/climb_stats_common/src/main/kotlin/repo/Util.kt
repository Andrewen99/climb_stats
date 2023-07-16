package repo

fun <T> List<DbRepoResponse<T>>.collapse() = DbRepoResponse<List<T?>>(
        data = this.map { it.data },
        success = this.any { it.success },
        errors = this.flatMap { it.errors }
    )

