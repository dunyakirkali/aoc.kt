package day24

import java.io.File
import java.util.LinkedList
import java.util.Queue

data class State(val time: Int, val position: Pair<Int, Int>)

val input: MutableMap<Pair<Int, Int>, String> =
    File("src/main/resources/day24/input.txt")
        .readText(Charsets.UTF_8)
        .trim()
        .split("\n")
        .drop(1)
        .dropLast(1)
        .foldIndexed(mutableMapOf<Pair<Int, Int>, String>()) { r, map_l1, row ->
          row.trim().split("").drop(2).dropLast(2).foldIndexed(map_l1) { c, map_l2, char ->
            map_l2.put(Pair(c, r), char)
            map_l2
          }
        }

fun move(
    blizzards: List<Triple<Int, Int, String>>,
    t: Int,
    width: Int,
    height: Int
): List<Pair<Int, Int>> =
    blizzards.map {
      when (it.third) {
        "^" -> Pair(it.first, (it.second - t + 1000 * height) % height)
        "v" -> Pair(it.first, (it.second + t) % height)
        ">" -> Pair((it.first + t) % width, it.second)
        "<" -> Pair((it.first - t + 1000 * width) % width, it.second)
        else -> throw Exception("Unknown direction")
      }
    }

fun solve(map: MutableMap<Pair<Int, Int>, String>, start: Pair<Int, Int>, end: Pair<Int, Int>, t: Int): Int {
  val width = map.keys.map { it.first }.maxOrNull() ?: 0
  val height = map.keys.map { it.second }.maxOrNull() ?: 0
  val queue: Queue<State> = LinkedList<State>(emptyList())
  val visited: MutableSet<State> = mutableSetOf()
  val blizzards: List<Triple<Int, Int, String>> =
      map.filter { it.component2() != "." }
          .map { Triple(it.component1().first, it.component1().second, it.component2()) }

  queue.add(State(t, start))

  while (queue.isNotEmpty()) {
    val state = queue.remove()

    if (visited.contains(state)) {
      continue
    }
    visited.add(state)

    val newTime = state.time + 1

    if (state.position == end) {
      return state.time
    }

    neighbours(state.position)
        .filter {
          it == start ||
              it == end ||
              (it.first >= 0 && it.second >= 0 && it.first <= width && it.second <= height)
        }
        .filterNot { move(blizzards, newTime, width + 1, height + 1).contains(it) }
        .forEach { queue.add(State(newTime, it)) }
  }

  return -1
}

fun neighbours(point: Pair<Int, Int>): MutableList<Pair<Int, Int>> =
    listOf<Pair<Int, Int>>(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0), Pair(0, 0))
        .map { (dc, dr) -> Pair(point.first + dc, point.second + dr) }
        .toMutableList()

fun part1(): Int {
  val start: Pair<Int, Int> = Pair(0, -1)
  val width = input.keys.map { it.first }.maxOrNull() ?: 0
  val height = input.keys.map { it.second }.maxOrNull() ?: 0
  val end: Pair<Int, Int> = Pair(width, height + 1)
  val t = 0

  return solve(input, start, end, t)
}

fun part2(): Int {
  val width = input.keys.map { it.first }.maxOrNull() ?: 0
  val height = input.keys.map { it.second }.maxOrNull() ?: 0
  val t = 0

  val step1 = solve(input, Pair(0, -1), Pair(width, height + 1), t)
  val step2 = solve(input, Pair(width, height + 1), Pair(0, -1), step1)
  val step3 = solve(input, Pair(0, -1), Pair(width, height + 1), step2)

  return step3
}

fun main() {
  println("part 1 = ${part1()}")
  println("part 2 = ${part2()}")
}
