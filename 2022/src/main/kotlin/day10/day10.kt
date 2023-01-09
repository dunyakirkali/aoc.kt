package day10

import java.io.File

sealed interface Instruction {
    object Noop: Instruction
    data class AddX(val value: Int) : Instruction
}


val cycles = listOf<Int>(20, 60, 100, 140, 180, 220);

val input: List<Instruction> =
    File("src/main/resources/day10/input.txt")
        .readText(Charsets.UTF_8)
        .trim()
        .split("\n")
        .map {
          val pieces = it.split(" ")
          when(pieces.first()) {
            "noop" -> Instruction.Noop
            "addx" -> Instruction.AddX(pieces.last().toInt())
            else -> throw Exception("Bad input")
          }
        }

fun part1(): Int {
  return generateSequence { input }
    .flatten()
    .take(500)
    .fold(Pair(1, mutableListOf<Int>(1))) { (x, collected), instruction ->
      when(instruction) {
        is Instruction.Noop -> {
          collected.add(x)
          Pair(x, collected)
        }
        is Instruction.AddX -> {
          val nx: Int = x + instruction.value
          collected.add(x)
          collected.add(nx)
          Pair(nx, collected)
        }
      }
    }
    .second
    .filterIndexed { index, _ -> cycles.contains(index + 1) }
    .zip(cycles)
    .map { (x, cycle) -> x * cycle }
    .sum()
}

/**/
//fun part2(): Int {
//    return 3
//}

fun main() {
    println("part 1 = ${part1()}")
//    println("part 2 = ${part2()}")
}