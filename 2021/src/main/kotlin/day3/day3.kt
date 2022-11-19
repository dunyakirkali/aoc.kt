package day3

import java.io.File

val input =
    File("src/main/resources/day3/input.txt")
        .readText(Charsets.UTF_8)
        .split('\n')

fun part1(): Int {
    val gamma = input[0].indices.map { index ->
        input.map { it[index] }.groupingBy { it }.eachCount().maxBy { it.value }.key
    }.joinToString("").toInt(2)

    val epsilon = input[0].indices.map { index ->
        input.map { it[index] }.groupingBy { it }.eachCount().minBy { it.value }.key
    }.joinToString("").toInt(2)

    return gamma * epsilon
}

fun <T> T.log(): T { println(this); return this }


fun part2(): Int {
    val oxygenGeneratorRating = input[0].indices
        .fold(input) { acc, i ->
            if (acc.size == 1) return@fold acc

            val counts = acc.map { it[i] }.groupingBy { it }.eachCount()
            val mostCommon = '1'.takeIf { counts['0'] == counts['1'] } ?: counts.maxBy { it.value }.key

            acc.filter { it[i] == mostCommon }
        }
        .first()
        .toInt(2)

    val co2ScrubberRating = input[0].indices
        .fold(input) { acc, i ->
            if (acc.size == 1) return@fold acc

            val counts = acc.map { it[i] }.groupingBy { it }.eachCount()
            val leastCommon = '0'.takeIf { counts['0'] == counts['1'] } ?: counts.minBy { it.value }.key

            acc.filter { it[i] == leastCommon }
        }
        .first()
        .toInt(2)

    return oxygenGeneratorRating * co2ScrubberRating
}

fun main() {
    println("part 1 = ${part1()}")
    println("part 2 = ${part2()}")
}