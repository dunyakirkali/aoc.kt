package day4

import java.io.File

val input: List<List<IntRange>> =
    File("src/main/resources/day4/input.txt").readText(Charsets.UTF_8).trim().split("\n").map { line
      ->
      line.trim().split(",").map { elf ->
        val pieces = elf.split("-").map { it.toInt() }
        val start = pieces.first()
        val end = pieces.last()
        start..end
      }
    }

fun part1(): Int {
  return input.count { elves ->
    val first = elves.first().toSet()
    val second = elves.last().toSet()

    first.containsAll(second) || second.containsAll(first)
  }
}

fun part2(): Int {
  return input.count { elves ->
    val first = elves.first().toSet()
    val second = elves.last().toSet()

    first.intersect(second).isNotEmpty()
  }
}

fun main() {
  println("part 1 = ${part1()}")
  println("part 2 = ${part2()}")
}
