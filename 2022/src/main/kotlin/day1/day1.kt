package day1

import java.io.File

val input =
    File("src/main/resources/day1/input.txt")
        .readText(Charsets.UTF_8)
        .trim()
        .split("\n\n")
        .map { elf ->
            elf
                .split('\n')
                .sumOf {
                    it.toInt()
                }
        }


fun part1(): Int {
    return input.max()
}

fun part2(): Int {
    return input.sorted().reversed().take(3).sum()
}

fun main() {
    println("part 1 = ${part1()}")
    println("part 2 = ${part2()}")
}