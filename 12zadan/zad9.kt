/**
 * Task: Refactor pizza slices program using while and do-while loops
 * Page: 85
 */
package zad9;

fun zad9() {
    println("Zadanie 9")
    // while loop version
    var pizzaSlices = 0
    while (pizzaSlices < 7) {
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
    }
    pizzaSlices++
    println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")

    // do-while loop version
    pizzaSlices = 0
    pizzaSlices++
    do {
        println("There's only $pizzaSlices slice/s of pizza :(")
        pizzaSlices++
    } while (pizzaSlices < 8)
    println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")
    println("===========================")

}
