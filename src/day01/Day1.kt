package day01

import java.io.File

class Day1 {

    fun solveA(): Int {
        val (list1, list2) = readFile()
        return list1.sorted().zip(list2.sorted()) { a, b -> kotlin.math.abs(a - b) }.sum()
    }

    fun solveB(): Int {
        val (list1, list2) = readFile()
        val list2Counts = list2.groupingBy { it }.eachCount()
        return list1.sumOf { it * (list2Counts[it] ?: 0) }

    }

    private fun readFile(): Pair<List<Int>, List<Int>> {
        return File("src/day01/input").absoluteFile.useLines { it.toList() }.map {
            val (part1, part2) = it.split("   ")
            part1.toInt() to part2.toInt()
        }.unzip()
    }

}