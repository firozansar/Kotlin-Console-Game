package info.firozansari

import java.util.*
import java.util.concurrent.TimeUnit

data class Player(val name: String?, var health: Int)

class Game {
    var randChar: (Int, Int) -> Int = { min, max -> (min..max).random() }
    private var enemyList = listOf("Boss Cyto", "Boss Enzo", "Boss Goblin", "Boss Spider", "Spider", "Cyto", "Goblin", "Enzo")
    private val enemy = enemyList.random()
    private var hp = if ("Boss" in enemy) (150..200).random() else 100
    private var hpPacks = (1..3).random()

    fun playGame(player: Player) {
        val enemy = Player(enemy, hp)
        player.health = if (randChar(1, 10) == 5) 125 else 100
        TimeUnit.SECONDS.sleep(1L)
        println("Welcome ${player.name} to this very epic dungeon game")
        TimeUnit.SECONDS.sleep(2L)
        if (player.health == 125) println("\nyou're lucky! you just got 25+ hp!")
        TimeUnit.SECONDS.sleep(2L)
        println("\nOH no! a wild ${enemy.name?.uppercase(Locale.getDefault())} appears!")
        TimeUnit.SECONDS.sleep(2L)
        println("\nGet ready for the fight ${player.name?.uppercase(Locale.getDefault())}!")
        TimeUnit.SECONDS.sleep(3L)
        var specialAttack = 6

        fun attack(): String? {
            println("\n\n${enemy.name}'s health: ${enemy.health}, ${player.name}'s health: ${player.health}\n(\"choose from the numbers below\")")
            println("1.Do a basic attack\n2.Do a special attack \"has a (1/$specialAttack) chance of working\"\n3.Heal up (current health packs = $hpPacks)\n: ")
            return readLine()
        }

        do {
            TimeUnit.SECONDS.sleep(2L)
            val choice = attack()
            TimeUnit.SECONDS.sleep(1L)

            when (choice) {
                "1" -> {
                    enemy.health -= 15
                    if (enemy.health <= 0) {
                        println("\nWell done! You have managed to kill ${enemy.name}")
                        break
                    }
                    println("_".repeat(30))
                    println("\nDoing a basic attack of 15! ${enemy.name}'s health is now ${enemy.health}")
                    if (startAttacking(enemy, player)) break
                }
                "2" -> {
                    println("_".repeat(30))
                    TimeUnit.SECONDS.sleep(1L)
                    println("Trying a special attack!")
                    TimeUnit.SECONDS.sleep(1L)
                    var worked: Boolean
                    if ((1..specialAttack).random() == 1) {
                        worked = true
                        println("Special attack worked!\nDecreasing the enemy's health by 40!")
                        enemy.health -= 40
                        if (enemy.health <= 0) {
                            println("\nWell done! You have managed to kill ${enemy.name}")
                            break
                        }
                    } else {
                        TimeUnit.SECONDS.sleep(1L)
                        println("Oh no! Attack Failed!")
                        worked = false
                        if (startAttacking(enemy, player)) break
                    }
                    when (worked) {
                        true -> specialAttack = 6
                        false -> specialAttack -= 1
                    }
                }
                "3" -> {
                    println("_".repeat(30))
                    TimeUnit.SECONDS.sleep(1L)
                    if (hpPacks > 0) {
                        println("Healing up by 25!")
                        TimeUnit.SECONDS.sleep(1L)
                        player.health += 25
                        hpPacks -= 1
                    } else {
                        println("You don't have enough health packs!")
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
        } while (enemy.health > 0 && player.health > 0)

        if (player.health <= 0) {
            println("You have lost!")
        } else if (enemy.health <= 0) {
            println("Well done! You have won!")
        } else {
            println("You both have tied!")
        }
    }

    private fun startAttacking(enemy: Player, player: Player): Boolean {
        println("${enemy.name} attacks!")
        val rand = randChar(1, 6)
        if (rand == 3) {
            println("${enemy.name} missed!")
        } else {
            player.health -= randChar(10, 20)
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
