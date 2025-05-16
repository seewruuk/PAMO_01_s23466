/**
 * Task: Check whether the requested protocol is supported
 * Page: 79
 */

package zad4;

fun zad4() {
    println("Zadanie 4")
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested.uppercase() in SUPPORTED
    println("Support for $requested: $isSupported")
    println("===========================")
}