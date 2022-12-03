package day3

import java.io.File
import kotlin.streams.toList

val input: List<List<Int>> =
    File("src/main/resources/day3/input.txt")
        .readText(Charsets.UTF_8)
        .trim()
        .split("\n")
        .map {
            it
                .chars()
                .toList()
                .map { char ->
                    when (char) {
                        in 97..122 -> char - 96
                        else -> char - 38
                    }
                }
        }

fun part1(): Int {
    return input.map { line ->
        val half = line.size / 2
        Pair(line.subList(0, half).toSet(), line.subList(half, line.size).toSet())
    }.flatMap { (l, r) ->
        l.intersect(r)
    }.sum()
}

fun part2(): Int {
    return input
        .map { line ->
            line.toSet()
        }
        .windowed(3, 3)
        .flatMap { group ->
            group.fold(group.first()) { acc, next -> acc.intersect(next) }
        }.sum()
}

fun main() {
    println("part 1 = ${part1()}")
    println("part 2 = ${part2()}")
}