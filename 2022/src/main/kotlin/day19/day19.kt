package day19

import java.io.File
import java.util.regex.Pattern
import kotlin.math.max

data class State(
    val ore: Int,
    val clay: Int,
    val obsidian: Int,
    val geode: Int,
    val oreBot: Int,
    val clayBot: Int,
    val obsidianBot: Int,
    val geodeBot: Int,
    val minutes: Int
)

data class Blueprint(
    val id: Int,
    val oreOre: Int,
    val clayOre: Int,
    val obsidianOre: Int,
    val obsidianClay: Int,
    val geodeOre: Int,
    val geodeObsidian: Int
)

val input: List<Blueprint> =
    File("src/main/resources/day19/input.txt").readText(Charsets.UTF_8).trim().split("\n").map {
      val pattern =
          Pattern.compile(
              """Blueprint (?<blueprint>\d+): Each ore robot costs (?<oreore>\d+) ore\. Each clay robot costs (?<clayore>\d+) ore\. Each obsidian robot costs (?<obsidianore>\d+) ore and (?<obsidianclay>\d+) clay. Each geode robot costs (?<geodeore>\d+) ore and (?<geodeobsidian>\d+) obsidian\.""")
      val matcher = pattern.matcher(it)
      matcher.find()

      Blueprint(
          matcher.group("blueprint").toInt(),
          matcher.group("oreore").toInt(),
          matcher.group("clayore").toInt(),
          matcher.group("obsidianore").toInt(),
          matcher.group("obsidianclay").toInt(),
          matcher.group("geodeore").toInt(),
          matcher.group("geodeobsidian").toInt())
    }

fun solve(blueprint: Blueprint, maxMinutes: Int): Int {
  val seen: MutableSet<Int> = mutableSetOf()
  var best = 0
  val queue = ArrayDeque<State>()

  queue.add(State(0, 0, 0, 0, 1, 0, 0, 0, maxMinutes))

  while (queue.isNotEmpty()) {
    val state = queue.removeFirst()

    if (state.geode + state.geodeBot < best) {
      continue
    }

    best = max(best, state.geode)

    if (state.minutes == 0) {
      continue
    }

    if (seen.contains(state.hashCode())) {
      continue
    }
    seen.add(state.hashCode())

    if (state.ore >= blueprint.geodeOre && state.obsidian >= blueprint.geodeObsidian) {
      val newState =
          state.copy(
              ore = state.ore + state.oreBot - blueprint.geodeOre,
              clay = state.clay + state.clayBot,
              obsidian = state.obsidian + state.obsidianBot - blueprint.geodeObsidian,
              geode = state.geode + state.geodeBot,
              geodeBot = state.geodeBot + 1,
              minutes = state.minutes - 1)
      queue.add(newState)
    }

    if (state.ore >= blueprint.obsidianOre && state.clay >= blueprint.obsidianClay) {
      val newState =
          state.copy(
              ore = state.ore + state.oreBot - blueprint.obsidianOre,
              clay = state.clay + state.clayBot - blueprint.obsidianClay,
              obsidian = state.obsidian + state.obsidianBot,
              geode = state.geode + state.geodeBot,
              obsidianBot = state.obsidianBot + 1,
              minutes = state.minutes - 1)

      queue.add(newState)
    }

    if (state.ore >= blueprint.clayOre) {
      val newState =
          state.copy(
              ore = state.ore + state.oreBot - blueprint.clayOre,
              clay = state.clay + state.clayBot,
              obsidian = state.obsidian + state.obsidianBot,
              geode = state.geode + state.geodeBot,
              clayBot = state.clayBot + 1,
              minutes = state.minutes - 1)

      queue.add(newState)
    }

    if (state.ore >= blueprint.oreOre) {
      val newState =
          state.copy(
              ore = state.ore + state.oreBot - blueprint.oreOre,
              clay = state.clay + state.clayBot,
              obsidian = state.obsidian + state.obsidianBot,
              geode = state.geode + state.geodeBot,
              oreBot = state.oreBot + 1,
              minutes = state.minutes - 1)

      queue.add(newState)
    }

    val newState =
        state.copy(
            ore = state.ore + state.oreBot,
            clay = state.clay + state.clayBot,
            obsidian = state.obsidian + state.obsidianBot,
            geode = state.geode + state.geodeBot,
            minutes = state.minutes - 1)
    queue.add(newState)
  }

  if (maxMinutes == 24) {
    return blueprint.id * best
  } else {
    return best
  }
}

fun part1(): Int {
  return input.map { blueprint -> solve(blueprint, 24) }.sum()
}

fun part2(): Int {
  return input.take(3).map { blueprint -> solve(blueprint, 32) }.fold(1) { acc, item -> acc * item }
}

fun main() {
  println("part 1 = ${part1()}")
  println("part 2 = ${part2()}")
}
