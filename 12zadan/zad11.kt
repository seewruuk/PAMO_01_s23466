/**
 * Task: Write a function circleArea that calculates the area of a circle given radius
 * Page: 89
 */
package zad11;


import kotlin.math.PI

fun circleArea(radius: Int): Double {
    return PI * radius * radius
}

fun zad11() {
    println("Zadanie 11")
    println(circleArea(2)) // 12.566370614359172
    println("===========================")

}
