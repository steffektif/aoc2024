package day05

import java.io.File

class Day5 {
    fun solveA(): Int {
        val (rules, updates) = readAndMapInputs()

        var sum = 0
        updates.forEach { update ->
            val applyingRules = findApplyingRules(rules, update)
            if (applyingRules.all { rule -> update.indexOf(rule.first) < update.indexOf(rule.second) }) {
                val middleIndex = update.size / 2
                sum += update[middleIndex]
            }
        }

        return sum
    }

    fun solveB(): Int {
        val (rules, updates) = readAndMapInputs()

        var sum = 0
        updates.filter { update ->
            val applyingRules = findApplyingRules(rules, update)
            applyingRules.any { rule -> update.indexOf(rule.first) >= update.indexOf(rule.second) }
        }.map { update ->
            val sortedUpdate = update.toMutableList()
            var swapped: Boolean

            do {
                swapped = false
                for (rule in rules) {
                    val index1 = sortedUpdate.indexOf(rule.first)
                    val index2 = sortedUpdate.indexOf(rule.second)
                    if (index1 != -1 && index2 != -1 && index1 > index2) {
                        val temp = sortedUpdate[index1]
                        sortedUpdate[index1] = sortedUpdate[index2]
                        sortedUpdate[index2] = temp
                        swapped = true
                    }
                }
            } while (swapped)
            sortedUpdate
        }.map { update ->
            val middleIndex = update.size / 2
            sum += update[middleIndex]
        }

        return sum
    }

    private fun readAndMapInputs(): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val fileContent = File("src/day05/input").readText()
        val parts = fileContent.split("\n\n")

        val rules = parts[0].lines().map { Pair(it.split("|")[0].toInt(), it.split("|")[1].toInt()) }
        val updates = parts[1].lines().map { line -> line.split(",").map { it.toInt() } }
        return Pair(rules, updates)
    }

    private fun findApplyingRules(rules: List<Pair<Int, Int>>, update: List<Int>): MutableList<Pair<Int, Int>> {
        val result = mutableListOf<Pair<Int, Int>>()
        for (rule in rules) {
            if (update.contains(rule.first) && update.contains(rule.second)) {
                result.add(rule)
            }
        }
        return result
    }

}