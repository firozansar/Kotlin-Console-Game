package info.firozansari

fun main() {
    println("_".repeat(25))
    customPrint("Welcome to this exciting game!\nPlease type your name to start: ")
    println("_".repeat(25))
    var name = readLine()
    if(name.isNullOrBlank()){
        name = "Stranger"
    }
    customPrint("\nStarting the game!")
    val player = Player(name, 100)
    Game().play(player)
}