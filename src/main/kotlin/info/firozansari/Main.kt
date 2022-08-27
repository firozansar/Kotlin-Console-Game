package info.firozansari

fun main() {
    addDivider()
    print("Welcome to this exciting game!\nPlease type your name to start: ")
    addDivider()

    var name = readLine()
    if (name.isNullOrBlank()) {
        name = "Stranger"
    }

    print("\nStarting the game!")

    val player = Player(name, 100)
    Game().play(player)
}
