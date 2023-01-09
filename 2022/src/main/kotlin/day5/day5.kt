package day5

import java.io.File
import kotlin.streams.toList

val input: List<Triple<Int, Int, Int>> =
    File("src/main/resources/day5/input.txt")
        .readText(Charsets.UTF_8)
        .trim()
        .split("\n\n")
        .last()
        .split("\n")
        .map {
            val components = it.split(" ")
            val amount = components[1].toInt()
            val from = components[3].toInt()
            val to = components[5].toInt()
            Triple(amount, from, to)
        }

fun part1(): String {
    val map: HashMap<Int, List<String>> = hashMapOf(
        1 to listOf<String>("G", "P", "N", "R"),
        2 to listOf<String>("H", "V", "S", "C", "L", "B", "J", "T"),
        3 to listOf<String>("L", "N", "M", "B", "D", "T"),
        4 to listOf<String>("B", "S", "P", "V", "R"),
        5 to listOf<String>("H", "V", "M", "W", "S", "Q", "C", "G"),
        6 to listOf<String>("J", "B", "D", "C", "S", "Q", "W"),
        7 to listOf<String>("L", "Q", "F"),
        8 to listOf<String>("V", "F", "L", "D", "T", "H", "M", "W"),
        9 to listOf<String>("F", "J", "M", "V", "B", "P", "L"),
    )

    return input
        .fold(map) { acc, (amount, from, to) ->
            val fromItems = acc[from] ?: emptyList()
            val toItems = acc[to] ?: emptyList()
            val fromSize = fromItems.size

            val first = fromItems.subList(0, amount)
            val second = fromItems.subList(amount, fromSize)

            acc[from] = second
            acc[to] = first.reversed().plus(toItems)
            acc
        }
        .map { (_, value) ->
            value.first()
        }
        .joinToString("")
}

fun part2(): String {
    val map: HashMap<Int, List<String>> = hashMapOf(
        1 to listOf<String>("G", "P", "N", "R"),
        2 to listOf<String>("H", "V", "S", "C", "L", "B", "J", "T"),
        3 to listOf<String>("L", "N", "M", "B", "D", "T"),
        4 to listOf<String>("B", "S", "P", "V", "R"),
        5 to listOf<String>("H", "V", "M", "W", "S", "Q", "C", "G"),
        6 to listOf<String>("J", "B", "D", "C", "S", "Q", "W"),
        7 to listOf<String>("L", "Q", "F"),
        8 to listOf<String>("V", "F", "L", "D", "T", "H", "M", "W"),
        9 to listOf<String>("F", "J", "M", "V", "B", "P", "L"),
    )

    return input
        .fold(map) { acc, (amount, from, to) ->
            val fromItems = acc[from] ?: emptyList()
            val toItems = acc[to] ?: emptyList()
            val fromSize = fromItems.size

            val first = fromItems.subList(0, amount)
            val second = fromItems.subList(amount, fromSize)

            acc[from] = second
            acc[to] = first.plus(toItems)
            acc
        }
        .map { (_, value) ->
            value.first()
        }
        .joinToString("")
}

fun main() {
    println("part 1 = ${part1()}")
    println("part 2 = ${part2()}")
}