package day8

import java.io.File
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.api.ndarray
import org.jetbrains.kotlinx.multik.ndarray.data.D2Array

val input: D2Array<Int> =
    File("src/main/resources/day8/example.txt")
        .readText(Charsets.UTF_8)
        .trim()
        .split("\n")
        .map { line -> line.map { char -> Integer.parseInt(char.toString()) } }
        .let { mk.ndarray(it) }

fun part1(): Int {
  println(input)
  //    println(mk.math.maxD3(c, axis = 0))
  return 4
}

/**/
// fun part2(): Int {
//    return 3
// }

fun main() {
  println("part 1 = ${part1()}")
  //    println("part 2 = ${part2()}")
}
