/**
 * Task: Use a lambda expression to create a list of URLs from actions, prefix, and ID
 * Page: 93
 */
package zad12;


fun zad12() {
    println("Zadanie 12")
    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com/book-info"
    val id = 5
    val urls = actions.map { action -> "$prefix/$id/$action" }
    println(urls)
    println("===========================")
}
