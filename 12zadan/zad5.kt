/**
 * Task: Define a map from numbers to words and use it to spell a number
 * Page: 79
 */

package zad5;

fun zad5() {
    println("Zadanie 5")
    val number2word = mapOf(1 to "one", 2 to "two", 3 to "three")
    val n = 2
    println("$n is spelt as '${number2word[n]}'")
    println("===========================")
}