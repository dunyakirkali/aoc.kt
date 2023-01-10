package day6

import java.io.File

val input: String = File("src/main/resources/day6/input.txt").readText(Charsets.UTF_8)

fun part1(): Int? {
  return input
      .chunked(1)
      .windowed(4, 1)
      .withIndex()
      .map { (ind, grp) -> grp.distinct().count().let { cnt -> Triple(grp, cnt, ind + 4) } }
      .find { (_, cnt, _) -> cnt == 4 }
      ?.third
}

fun part2(): Int? {
  return input
      .chunked(1)
      .windowed(14, 1)
      .withIndex()
      .map { (ind, grp) -> grp.distinct().count().let { cnt -> Triple(grp, cnt, ind + 14) } }
      .find { (_, cnt, _) -> cnt == 14 }
      ?.third
}

fun main() {
  println("part 1 = ${part1()}")
  println("part 2 = ${part2()}")
}
