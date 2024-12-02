package day02

import java.io.File

class Day2 {


    fun solveA(): Int {
        return File("src/day02/input").absoluteFile.useLines { it.toList() }.mapNotNull { report ->
            val levels = report.split(" ").map { it.toInt() }
            val isIncreasing = levels.zipWithNext().all { (a, b) -> b - a in 1..3 }
            val isDecreasing = levels.zipWithNext().all { (a, b) -> a - b in 1..3 }
            if (isDecreasing || isIncreasing) levels else null
        }.count()
    }

    fun solveB(): Int {
        return File("src/day02/input").absoluteFile.useLines { it.toList() }.mapNotNull { report ->
            val levels = report.split(" ").map { it.toInt() }
            if (checkLevels(levels)) levels else {
                if (iterateWithoutEachIndex(levels)) levels else null
            }
        }.count()
    }

    private fun checkLevels(levels: List<Int>): Boolean {
        val isIncreasing = levels.zipWithNext().all { (a, b) -> b - a in 1..3 }
        val isDecreasing = levels.zipWithNext().all { (a, b) -> a - b in 1..3 }
        return isDecreasing || isIncreasing
    }

    private fun iterateWithoutEachIndex(levels: List<Int>): Boolean {
        for (i in levels.indices) {
            val newLevels = levels.toMutableList()
            newLevels.removeAt(i)
            if (checkLevels(newLevels)) return true
        }
        return false

    }

}