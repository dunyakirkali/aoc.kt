package day1

import java.io.File

val input =
    File("src/main/resources/day1/input.txt")
        .readText(Charsets.UTF_8)
        .split('\n')
        .map {
            it.toInt()
        }

fun part1(): Int {
    return input
        .windowed(2, 1, false)
        .map {
            it[1] - it[0]
        }.filter {
            it > 0
        }.size
}

fun part2(): Int {
    return input
        .windowed(3, 1, false)
        .map {
            it.sum()
        }
        .windowed(2, 1, false)
        .map {
            it[1] - it[0]
        }.filter {
            it > 0
        }.size
}

fun main() {
    println("part 1 = ${part1()}")
    println("part 2 = ${part2()}")
}