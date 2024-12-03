package day03

import java.io.File

class Day3 {
    private val mulRegex = Regex("mul\\(\\d+,\\d+\\)")
    private val doRegex = Regex("do\\(\\)")
    private val dontRegex = Regex("don't\\(\\)")

    fun solveA(): Int {
        val input = readFile()
        val matches = mulRegex.findAll(input)
        return matches.map { match ->
            val (a, b) = match.value.substring(4, match.value.length - 1).split(",").map { it.toInt() }
            a * b
        }.reduce { acc, value -> acc + value }
    }

    fun solveB(): Int {
        val sortedFindings = findAndMatch().toList()
        var shouldMultiply = true
        var result = 0
        for (finding in sortedFindings) {
            when {
                finding.startsWith("mul") && shouldMultiply -> {
                    val (a, b) = finding.substring(4, finding.length - 1).split(",").map { it.toInt() }
                    result += a * b
                }

                finding == "don't()" -> shouldMultiply = false
                finding == "do()" -> shouldMultiply = true
            }
        }
        return result
    }

    private fun findAndMatch(): Sequence<String> {
        val input = readFile()
        val mulMatches = mulRegex.findAll(input).map { it.range to it.value }
        val doMatches = doRegex.findAll(input).map { it.range to it.value }
        val dontMatches = dontRegex.findAll(input).map { it.range to it.value }

        val allMatches = (mulMatches + doMatches + dontMatches).sortedBy { it.first.first }
        return allMatches.map { it.second }
    }

    private fun readFile(): String {
        return File("src/day03/input").readText()
    }
}