package day2

import java.io.File

enum class Hand {
  ROCK,
  PAPER,
  SCISSORS,
  UNKNOWN
}

val input: List<String> =
    File("src/main/resources/day2/input.txt").readText(Charsets.UTF_8).trim().split("\n")

fun interpret(move: String): Hand {
  return when (move) {
    "A" -> Hand.ROCK
    "B" -> Hand.PAPER
    "C" -> Hand.SCISSORS
    "X" -> Hand.ROCK
    "Y" -> Hand.PAPER
    "Z" -> Hand.SCISSORS
    else -> Hand.UNKNOWN
  }
}

fun interpret2(round: Pair<String, String>): Pair<Hand, Hand> {
  return when (round.second) {
    "X" ->
        return when (round.first) {
          "A" -> Pair(Hand.ROCK, Hand.SCISSORS)
          "B" -> Pair(Hand.PAPER, Hand.ROCK)
          "C" -> Pair(Hand.SCISSORS, Hand.PAPER)
          else -> Pair(Hand.UNKNOWN, Hand.UNKNOWN)
        }
    "Y" ->
        return when (round.first) {
          "A" -> Pair(Hand.ROCK, Hand.ROCK)
          "B" -> Pair(Hand.PAPER, Hand.PAPER)
          "C" -> Pair(Hand.SCISSORS, Hand.SCISSORS)
          else -> Pair(Hand.UNKNOWN, Hand.UNKNOWN)
        }
    "Z" ->
        return when (round.first) {
          "A" -> Pair(Hand.ROCK, Hand.PAPER)
          "B" -> Pair(Hand.PAPER, Hand.SCISSORS)
          "C" -> Pair(Hand.SCISSORS, Hand.ROCK)
          else -> Pair(Hand.UNKNOWN, Hand.UNKNOWN)
        }
    else -> Pair(Hand.UNKNOWN, Hand.UNKNOWN)
  }
}

fun scoreMove(move: Hand): Int {
  return when (move) {
    Hand.ROCK -> 1
    Hand.PAPER -> 2
    Hand.SCISSORS -> 3
    else -> 0
  }
}

fun scoreRound(round: Pair<Hand, Hand>): Int {
  return when (round.first) {
    Hand.ROCK ->
        return when (round.second) {
          Hand.ROCK -> 3
          Hand.PAPER -> 6
          Hand.SCISSORS -> 0
          else -> 0
        }
    Hand.PAPER ->
        return when (round.second) {
          Hand.ROCK -> 0
          Hand.PAPER -> 3
          Hand.SCISSORS -> 6
          else -> 0
        }
    Hand.SCISSORS ->
        return when (round.second) {
          Hand.ROCK -> 6
          Hand.PAPER -> 0
          Hand.SCISSORS -> 3
          else -> 0
        }
    else -> 0
  }
}

fun part1(): Int {
  return input
      .map { round -> round.split(' ').map { interpret(it) }.let { it[0] to it[1] } }
      .sumOf { scoreMove(it.second) + scoreRound(it) }
}

fun part2(): Int {
  return input
      .map { line -> line.split(' ').let { it[0] to it[1] } }
      .map { round: Pair<String, String> -> interpret2(round) }
      .sumOf { scoreMove(it.second) + scoreRound(it) }
}

fun main() {
  println("part 1 = ${part1()}")
  println("part 2 = ${part2()}")
}
