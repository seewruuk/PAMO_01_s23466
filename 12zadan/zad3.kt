/**
 * Task: Print how many numbers there are in total from two lists
 * Page: 79
 */

package zad3;

fun zad3() {
    println("Zadanie 3")
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    val totalCount = greenNumbers.count() + redNumbers.count()
    println("totalCount = $totalCount")
    println("===========================")


}