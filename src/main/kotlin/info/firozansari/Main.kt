package info.firozansari

fun main() {
    println("_".repeat(25))
    println("Hello there!\nWelcome to this Dungeon Game!\nPlease enter your name!: ")
    println("_".repeat(25))
    val name = readLine()
    if(!name.isNullOrBlank()){
        println("\nStarting the game!")
    } else {
        println("Please provide a name to start")
        main()
    }
    val player = Player(name, 100)
    Game().playGame(player)
}