package info.firozansari

fun main() {
    println("_".repeat(25))
    println("Hello there!\nWelcome to this Dungeon Game!\nPlease type your name!: ")
    println("_".repeat(25))
    var name = readLine()
    if(name.isNullOrBlank()){
        name = "Stranger"
    }
    println("\nStarting the game!")
    val player = Player(name, 100)
    Game().playGame(player)
}