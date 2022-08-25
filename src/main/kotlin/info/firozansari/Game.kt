package info.firozansari
import java.util.*
import java.util.concurrent.TimeUnit

data class Player(val name:String?, var health:Int)

class Game{
    // define the game vars
    var randCh: (Int, Int) -> Int = { min, max -> (min..max).random()}
    private var enemyList = listOf<String>("spider", "cyto", "goblin", "enzo", "el0xren", "boss cyto", "boss el0xren", "boss enzo", "boss goblin", "boss spider", "ahmed", "boss ahmed")
    private val enemy = enemyList.random()
    private var hp = if("boss" in enemy) (150..200).random() else 100
    private var hpPacks = (1..3).random()

    //the game function
    fun playGame(player:Player) {
        val enemy = Player(enemy, hp)
        player.health = if(randCh(1,10) == 5) 125 else 100
        TimeUnit.SECONDS.sleep(1L)
        println("Welcome ${player.name} to this very epic dungeon game")
        TimeUnit.SECONDS.sleep(2L)
        if(player.health == 125) println("\nyou're lucky! you just got 25+ hp!")
        TimeUnit.SECONDS.sleep(2L)
        println("\nOH S**T A WILD ${enemy.name?.uppercase(Locale.getDefault())} APPEARS!")
        TimeUnit.SECONDS.sleep(2L)
        println("\nGET READY FOR THE FIGHT ${player.name?.uppercase(Locale.getDefault())}")
        TimeUnit.SECONDS.sleep(3L)
        var specialAttack = 6

        fun attack(): String? {
            println("\n\n${enemy.name}'s health: ${enemy.health}, ${player.name}'s health: ${player.health}\n(\"choose from the numbers below\")")
            println("1.do a basic attack\n2.do a special attack \"has a (1/$specialAttack) chance of working\"\n3.heal up (current health packs = $hpPacks)\n: ")
            return readLine()
        }

        do {
            TimeUnit.SECONDS.sleep(2L)
            val choice = attack()
            TimeUnit.SECONDS.sleep(1L)

            when(choice){
                "1" -> {
                    enemy.health -= 15
                    if(enemy.health <= 0) {
                        println("\nyou've managed to kill ${enemy.name}")
                        break
                    }
                    println("_".repeat(30))
                    println("\ndoing a basic attack of 15! ${enemy.name}'s health is now ${enemy.health}")
                    if (startFighting(enemy, player)) break

                }
                "2" -> {
                    println("_".repeat(30))
                    TimeUnit.SECONDS.sleep(1L)
                    println("trying a special attack!")
                    TimeUnit.SECONDS.sleep(1L)
                    var worked:Boolean = false
                    if ((1..specialAttack).random() == 1) {
                        worked = true
                        println("special attack worked!\ndecreasing the enemy's health by 40!")
                        enemy.health -= 40
                        if(enemy.health <= 0) {
                            println("\nyou've managed to kill ${enemy.name}")
                            break
                        }
                    } else {
                        TimeUnit.SECONDS.sleep(1L)
                        println("Attack Failed!")
                        worked = false
                        if (startFighting(enemy, player)) break
                    }
                    when(worked){
                        true -> specialAttack = 6
                        false -> specialAttack -= 1
                    }
                }
                "3" -> {
                    println("_".repeat(30))
                    TimeUnit.SECONDS.sleep(1L)
                    if(hpPacks > 0){
                        println("Healing up by 25!")
                        TimeUnit.SECONDS.sleep(1L)
                        player.health += 25
                        hpPacks -= 1
                    } else {
                        println("you don't have enough health packs!")
                    }
                    println("_".repeat(30))

                }
                else -> {
                    println("_".repeat(30))
                    println("Invalid option!")
                    TimeUnit.SECONDS.sleep(2L)
                    attack()
                    println("_".repeat(30))
                }
            }
        } while(enemy.health > 0 && player.health > 0)

        if(player.health <= 0){
            println("you have lost!")
        } else if(enemy.health <= 0){
            println("you've won!")
        } else {
            println("you both have tied!")
        }

    }

    private fun startFighting(enemy: Player, player: Player): Boolean {
        println("${enemy.name} Attacks!")
        val rand = randCh(1, 6)
        if (rand == 3) {
            println("${enemy.name} missed!")
        } else {
            player.health -= randCh(10, 20)
            if (player.health <= 0) {
                println("\n${enemy.name} has managed to kill you!")
                return true
            }
            println("${enemy.name} managed to hit you!\nyour health is now ${player.health}!")
        }
        println("_".repeat(30))
        return false
    }
}
