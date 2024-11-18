package org.example

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

fun main() {
    getListId(10).onEach { i -> println(i) }
    //getListOfPassword("K", 3)?.onEach { i -> println(i) }
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
        var id = ""
        count++
        if (count.toString().length < 6) {
            for (i in 0..6 - count.toString().length) {
                id += 0
            }
        }
        id+=count
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