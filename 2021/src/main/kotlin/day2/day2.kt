package day2

import java.io.File

val input =
    File("src/main/resources/day2/input.txt")
        .readText(Charsets.UTF_8)
        .split('\n')
        .map {
            val (direction, amount) = it.split(' ')
            Pair(direction, amount.toInt())
        }

fun part1(): Int {
    val result = input.fold(Pair(0, 0)) { acc,  (direction, amount) ->
        when (direction) {
            "forward" -> Pair(acc.first + amount, acc.second)
            "down" -> Pair(acc.first, acc.second + amount)
            "up" -> Pair(acc.first, acc.second - amount)
            else -> acc
        }
    }

    return result.first * result.second
}

fun part2(): Int {
    val result = input.fold(Triple(0, 0, 0)) { acc,  (direction, amount) ->
        when (direction) {
            "forward" -> Triple(acc.first + amount, acc.second + (acc.third * amount), acc.third)
            "down" -> Triple(acc.first, acc.second, acc.third + amount)
            "up" -> Triple(acc.first, acc.second, acc.third - amount)
            else -> acc
        }
    }

    return result.first * result.second
}

fun main() {
    println("part 1 = ${part1()}")
    println("part 2 = ${part2()}")
}