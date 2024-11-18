package org.example

fun main() {
    for (i in 1..10) {
        println(createPassword())
    }
}

fun createPassword(): String {
    val characters = ('a'..'z') + ('0'..'9')
    var password = ""
    for (i in 1..6) {
        val char = characters.random()
        if (!char.isDigit() && i%2 == 1) {
            password += char.uppercaseChar()
        } else {
            password += char
        }
    }
    return password
}