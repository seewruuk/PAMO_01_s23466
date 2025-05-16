/**
 * Task: Simulate the FizzBuzz game, printing numbers 1 to 100 with fizz/buzz rules
 * Page: 85
 */

package zad10;

fun zad10() {
    println("Zadanie 10")

    for (i in 1..100) {
        println(
            when {
                i % 15 == 0 -> "fizzbuzz"
                i % 3 == 0 -> "fizz"
                i % 5 == 0 -> "buzz"
                else -> i.toString()
            }
        )
    }
    println("===========================")

}
