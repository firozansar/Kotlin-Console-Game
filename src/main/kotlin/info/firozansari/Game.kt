package info.firozansari

import info.firozansari.PrintFormat.DANGER
import info.firozansari.PrintFormat.SUCCESS
import info.firozansari.PrintFormat.WARNING
import kotlin.system.exitProcess

class Game {
    var randChar: (Int, Int) -> Int = { min, max -> (min..max).random() }
    private var enemyList =
        listOf("Boss Cyto", "Boss Enzo", "Boss Goblin", "Boss Spider", "Spider", "Cyto", "Goblin", "Enzo")
    private val enemy = enemyList.random()
    private var health = if ("Boss" in enemy) (150..200).random() else 100
    private var healthPacks = (1..4).random()

    fun play(player: Player) {
        val enemy = Player(enemy, health)
        player.health = if (randChar(1, 10) == 5) 125 else 100
        addDelay()

        print("Welcome ${player.name.capitalize()} to this exciting game!")
        addDelay()

        if (player.health == 125) print("\nYou are lucky! you just got 25+ health!")
        addDelay()

        print("\nOh no! a wild ${enemy.name.capitalize()} appears!", DANGER)
        addDelay()

        print("\nGet ready for the fight ${player.name.capitalize()}!", WARNING)
        addDelay()

        var specialAttack = 6

        fun getAttackChoice(): String? {
            print(
                "\n\n${enemy.name}'s health: ${enemy.health}, " +
                        "${player.name}'s health: ${player.health}\n(\"choose " +
                        "from the numbers below\")"
            )

            print(
                "1.Do a basic attack\n" +
                        "2.Do a special attack \"has a (1/$specialAttack) chance of working\"\n" +
                        "3.Heal up (current health packs = $healthPacks)\n: "
            )

            return readLine()
        }

        do {
            addDelay()
            val choice = getAttackChoice()
            addDelay()

            when (choice) {
                "1" -> {
                    enemy.health -= 15
                    if (enemy.health <= 0) {
                        print("\nWell done! You have managed to kill ${enemy.name}", SUCCESS)
                        break
                    }
                    addDivider()
                    print("\nDoing a basic attack of 15! ${enemy.name}'s health is now ${enemy.health}")
                    if (isPlayerKilled(enemy, player)) break
                }
                "2" -> {
                    addDivider()
                    addDelay()
                    print("Trying a special attack!")
                    addDelay()
                    var worked: Boolean
                    if ((1..specialAttack).random() == 1) {
                        worked = true
                        print("Special attack worked!\nDecreasing the enemy's health by 40!")
                        enemy.health -= 40
                        if (enemy.health <= 0) {
                            print("\nWell done! You have managed to kill ${enemy.name}", SUCCESS)
                            break
                        }
                    } else {
                        addDelay()
                        print("Oh no! Attack Failed!", WARNING)
                        worked = false
                        if (isPlayerKilled(enemy, player)) break
                    }
                    when (worked) {
                        true -> specialAttack = 6
                        false -> specialAttack -= 1
                    }
                }
                "3" -> {
                    addDivider()
                    addDelay()
                    if (healthPacks > 0) {
                        print("Healing up by 25!")
                        addDelay()
                        player.health += 25
                        healthPacks -= 1
                    } else {
                        print("You don't have enough health packs!")
                    }
                    addDivider()
                }
                else -> {
                    addDivider()
                    print("Invalid option!")
                    addDelay()
                    getAttackChoice()
                    addDivider()
                }
            }
        } while (enemy.health > 0 && player.health > 0)

        if (player.health <= 0) {
            print("You have lost!", DANGER)
        } else if (enemy.health <= 0) {
            print("Well done! You have won!", SUCCESS)
        } else {
            print("You both have tied!")
        }
        exitProcess(1)
    }

    private fun isPlayerKilled(enemy: Player, player: Player): Boolean {
        println("${enemy.name} attacks!")
        val rand = randChar(1, 6)
        if (rand == 3) {
            enemy.health -= randChar(10, 20)
            print("${enemy.name} missed!", SUCCESS)
        } else {
            player.health -= randChar(10, 20)
            if (player.health <= 0) {
                print("\n${enemy.name} has managed to kill you!", DANGER)
                return true
            }
            print("${enemy.name} managed to hit you!\nyour health is now ${player.health}!", WARNING)
        }
        addDivider()
        return false
    }
}
