package day7

import java.io.File
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge

val input:
    Triple<DefaultDirectedGraph<String, DefaultEdge>, List<String>, MutableMap<String, Int>> =
    File("src/main/resources/day7/input.txt").readText(Charsets.UTF_8).trim().split("\n").fold(
        Triple(
            DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge::class.java),
            emptyList(),
            HashMap())) { (acc, cwd, sizes), line ->
          when {
            line.startsWith("$") -> {
              when {
                line.startsWith("$ cd") -> {
                  val (_, _, folder) = line.trim().split(" ")
                  if (folder == "..") {
                    val newCwd = cwd.drop(1)
                    Triple(acc, newCwd, sizes)
                  } else {
                    val newCwd = listOf(folder) + cwd
                    Triple(acc, newCwd, sizes)
                  }
                }
                else -> Triple(acc, cwd, sizes)
              }
            }
            line.startsWith("dir") -> {
              val (_, folder) = line.trim().split(" ")
              val path = cwd.joinToString("/")
              val toPath = (listOf(folder) + cwd).joinToString("/")

              acc.addVertex(path)
              acc.addVertex(toPath)
              acc.addEdge(path, toPath)

              Triple(acc, cwd, sizes)
            }
            else -> {
              val (size, file) = line.split(" ")
              val path = cwd.joinToString("/")
              val toPath = (listOf(file) + cwd).joinToString("/")
              sizes[toPath] = size.toInt()

              acc.addVertex(toPath)
              acc.addEdge(path, toPath)

              Triple(acc, cwd, sizes)
            }
          }
        }

fun sizes(
    graph: DefaultDirectedGraph<String, DefaultEdge>,
    vertex: String,
    sizes: MutableMap<String, Int>
): Int {
  val outEdges = graph.outgoingEdgesOf(vertex)
  return if (outEdges.isEmpty()) {
    sizes[vertex] ?: 0
  } else {
    outEdges.sumOf { sizes(graph, graph.getEdgeTarget(it), sizes) }
  }
}

fun part1(): Int {
  val (graph, _, sizes) = input
  return graph
      .vertexSet()
      .filter { !sizes.keys.contains(it) }
      .map { sizes(graph, it, sizes) }
      .filter { it < 100_000 }
      .sum()
}

/**/
fun part2(): Int {
  val (graph, _, sizes) = input
  val used = sizes(graph, "/", sizes)

  return graph
      .vertexSet()
      .filter { !sizes.keys.contains(it) }
      .map { sizes(graph, it, sizes) }
      .filter { (used - it) < 40_000_000 }
      .min()
}

fun main() {
  println("part 1 = ${part1()}")
  println("part 2 = ${part2()}")
}
