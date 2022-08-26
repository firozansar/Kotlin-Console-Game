package info.firozansari

import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

class Game {
    var randChar: (Int, Int) -> Int = { min, max -> (min..max).random() }
    private var enemyList = listOf("Boss Cyto", "Boss Enzo", "Boss Goblin", "Boss Spider", "Spider", "Cyto", "Goblin", "Enzo")
    private val enemy = enemyList.random()
    private var health = if ("Boss" in enemy) (150..200).random() else 100
    private var healthPacks = (1..5).random()

    fun play(player: Player) {
        val enemy = Player(enemy, health)
        player.health = if (randChar(1, 10) == 5) 125 else 100
        TimeUnit.SECONDS.sleep(1L)
        customPrint("Welcome ${player.name.capitalize()} to this exciting game!")
        TimeUnit.SECONDS.sleep(2L)
        if (player.health == 125) customPrint("\nYou are lucky! you just got 25+ health!")
        TimeUnit.SECONDS.sleep(2L)
        customPrint("\nOh no! a wild ${enemy.name.capitalize()} appears!")
        TimeUnit.SECONDS.sleep(2L)
        customPrint("\nGet ready for the fight ${player.name.capitalize()}!")
        TimeUnit.SECONDS.sleep(3L)
        var specialAttack = 6

        fun getAttackChoice(): String? {
            customPrint("\n\n${enemy.name}'s health: ${enemy.health}, ${player.name}'s health: ${player.health}\n(\"choose from the numbers below\")")
            customPrint("1.Do a basic attack\n2.Do a special attack \"has a (1/$specialAttack) chance of working\"\n3.Heal up (current health packs = $healthPacks)\n: ")
            return readLine()
        }

        do {
            TimeUnit.SECONDS.sleep(2L)
            val choice = getAttackChoice()
            TimeUnit.SECONDS.sleep(1L)

            when (choice) {
                "1" -> {
                    enemy.health -= 15
                    if (enemy.health <= 0) {
                        customPrint("\nWell done! You have managed to kill ${enemy.name}")
                        break
                    }
                    println("_".repeat(30))
                    customPrint("\nDoing a basic attack of 15! ${enemy.name}'s health is now ${enemy.health}")
                    if (isPlayerKilled(enemy, player)) break
                }
                "2" -> {
                    println("_".repeat(30))
                    TimeUnit.SECONDS.sleep(1L)
                    customPrint("Trying a special attack!")
                    TimeUnit.SECONDS.sleep(1L)
                    var worked: Boolean
                    if ((1..specialAttack).random() == 1) {
                        worked = true
                        customPrint("Special attack worked!\nDecreasing the enemy's health by 40!")
                        enemy.health -= 40
                        if (enemy.health <= 0) {
                            customPrint("\nWell done! You have managed to kill ${enemy.name}")
                            break
                        }
                    } else {
                        TimeUnit.SECONDS.sleep(1L)
                        customPrint("Oh no! Attack Failed!")
                        worked = false
                        if (isPlayerKilled(enemy, player)) break
                    }
                    when (worked) {
                        true -> specialAttack = 6
                        false -> specialAttack -= 1
                    }
                }
                "3" -> {
                    println("_".repeat(30))
                    TimeUnit.SECONDS.sleep(1L)
                    if (healthPacks > 0) {
                        customPrint("Healing up by 25!")
                        TimeUnit.SECONDS.sleep(1L)
                        player.health += 25
                        healthPacks -= 1
                    } else {
                        customPrint("You don't have enough health packs!")
                    }
                    println("_".repeat(30))

                }
                else -> {
                    println("_".repeat(30))
                    customPrint("Invalid option!")
                    TimeUnit.SECONDS.sleep(2L)
                    getAttackChoice()
                    println("_".repeat(30))
                }
            }
        } while (enemy.health > 0 && player.health > 0)

        if (player.health <= 0) {
            customPrint("You have lost!")
        } else if (enemy.health <= 0) {
            customPrint("Well done! You have won!")
        } else {
            customPrint("You both have tied!")
        }
        exitProcess(1)
    }

    private fun isPlayerKilled(enemy: Player, player: Player): Boolean {
        println("${enemy.name} attacks!")
        val rand = randChar(1, 6)
        if (rand == 3) {
            enemy.health -= randChar(10, 20)
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
