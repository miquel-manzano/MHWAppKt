package mm.kittensdev.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform