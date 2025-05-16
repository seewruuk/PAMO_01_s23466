/**
 * Task: Print corresponding actions for game console buttons using when
 * Page: 82
 */
package zad8;


fun zad8() {
    println("Zadanie 8")

    val button = "A"
    println(
        when (button) {
            "A" -> "Yes"
            "B" -> "No"
            "X" -> "Menu"
            "Y" -> "Nothing"
            else -> "There is no such button"
        }
    )
    println("===========================")

}
