package day4

import java.io.File

val input =
    File("src/main/resources/day4/input.txt")
        .readText(Charsets.UTF_8)
        .split('\n')
        .map {
            it.toInt()
        }

fun part1(): Int {
    return 1
}

fun part2(): Int {
    return 5
}

fun main() {
    println("part 1 = ${part1()}")
    println("part 2 = ${part2()}")
}