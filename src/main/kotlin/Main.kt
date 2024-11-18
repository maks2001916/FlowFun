package org.example

fun main() {
    getListOfPassword("K", 3)?.onEach { i -> println(i) }
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

fun getListOfPassword(input: String, length: Int): List<String>? {
    var passwords = mutableListOf<String>()
    while (passwords.size < length) {
        var password = createPassword()
        if (password.first() == input.first()) {
            passwords.add(password)
        }
    }
    return passwords.toList()
}