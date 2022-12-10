package day7

import org.jgrapht.Graph
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import java.io.File


val input: Pair<DefaultDirectedGraph<String, DefaultEdge>, List<String>> =
    File("src/main/resources/day7/input.txt")
        .readText(Charsets.UTF_8)
        .trim()
        .split("\n")
        .fold(
            Pair(
                DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge::class.java),
                emptyList()
            )
        ) { (acc, cwd), line ->
            when {
                line.startsWith("$") -> {
                    when {
                        line.startsWith("$ cd") -> {
                            val (_, _, folder) = line.split(" ")
                            if (folder == "..") {
                                Pair(acc, cwd)
                            } else {
                                Pair(acc, cwd)
                            }
                        }

                        else -> Pair(acc, cwd)
                    }
                }

                line.startsWith("dir") -> Pair(acc, cwd)
                else -> {
                    val (size, file) = line.split(" ")
                    val path = cwd.joinToString("/")

                    Pair(acc, cwd)
                }
            }
        }

fun part1(): Int {
    println(input)
    return 4
}

/**/
fun part2(): Int {
    return 3
}

fun main() {
    println("part 1 = ${part1()}")
    println("part 2 = ${part2()}")
}