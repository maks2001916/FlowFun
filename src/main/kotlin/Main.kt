package org.example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.zip

suspend fun main() {
    var countUsers = 0
    var firstChar = ""
    println("Для скольких пользователей нужно создать пароли?")
    countUsers = readln().toInt()
    println("C какого символа они должны начинаться?")
    firstChar = readln().first().toString()

    val idsFlow = getIdFlow(countUsers)
    val passwordsFlow = getPasswordFlow(firstChar, countUsers)

    // Измерение времени
    val startTime = System.currentTimeMillis()

    idsFlow.zip(passwordsFlow) { id, password -> id to password }
        .collect { (id, password) ->
            println("User ID: $id, Password: $password")
        }

    val endTime = System.currentTimeMillis()
    println("Время выполнения: ${endTime - startTime} мс")

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

fun getListOfPassword(input: String, length: Int): List<String> {
    var passwords = mutableListOf<String>()
    while (passwords.size < length) {
        var password = createPassword()
        if (password.first() == input.first()) {
            passwords.add(password)
        }
    }
    return passwords.toList()
}

fun getListId(length: Int): List<String> {
    var ids = mutableListOf<String>()
    var count = 0
    while (ids.size < length) {
        count++
        var id = count.toString().padStart(6, '0')
        ids.add(id)
    }
    return ids.toList()
}

fun getIdFlow(length: Int): Flow<String> {
    return getListId(length).asFlow()
}

fun getPasswordFlow(input: String, length: Int): Flow<String> {
    return getListOfPassword(input, length).asFlow()
}