/**
 * Task: Create a simple dice game printing win if two dice match, lose otherwise
 * Page: 82
 */
package zad6;

import kotlin.random.Random

fun zad6() {
    println("Zadanie 6")

    val firstResult = Random.nextInt(6)
    val secondResult = Random.nextInt(6)
    if (firstResult == secondResult)
        println("You win :)")
    else
        println("You lose :(")
    println("===========================")

}
