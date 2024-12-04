package day04

import java.io.File

class Day4 {
    fun solveA(): Int {
        val grid = File("src/day04/input").absoluteFile.readLines()
        return searchWord(grid, "XMAS")
    }

    fun solveB(): Int {
        val grid = File("src/day04/input").absoluteFile.readLines()
        return searchDiagonalX(grid, "MAS")
    }

    private fun searchWord(grid: List<String>, word: String): Int {
        return searchHorizontalAndVertical(grid, word) + searchDiagonal(grid, word)
    }

    // returns count found horizontal
    private fun searchHorizontalAndVertical(grid: List<String>, word: String): Int {
        var count = 0
        val numRows = grid.size
        val numCols = grid[0].length

        for (i in 0 until numRows) {
            for (j in 0 until numCols) {
                if (j <= numCols - word.length) {
                    if (grid[i].substring(j, j + word.length) == word) count++
                    if (grid[i].substring(j, j + word.length).reversed() == word) count++
                }
                if (i <= numRows - word.length) {
                    var verticalWord = ""
                    for (k in word.indices) {
                        verticalWord += grid[i + k][j]
                    }
                    if (verticalWord == word) count++
                    if (verticalWord.reversed() == word) count++
                }
            }
        }
        return count
    }

    // returns count found diagonal
    private fun searchDiagonal(grid: List<String>, word: String): Int {
        var count = 0
        val numRows = grid.size
        val numCols = grid[0].length
        for (i in 0 until numRows - word.length + 1) {
            for (j in 0 until numCols - word.length + 1) {
                var diagonalWord1 = ""
                var diagonalWord2 = ""
                for (k in word.indices) {
                    diagonalWord1 += grid[i + k][j + k]
                    diagonalWord2 += grid[i + k][j + word.length - 1 - k]
                }
                if (diagonalWord1 == word) count++
                if (diagonalWord1.reversed() == word) count++
                if (diagonalWord2 == word) count++
                if (diagonalWord2.reversed() == word) count++
            }
        }
        return count
    }

    // returns count found diagonal X
    private fun searchDiagonalX(grid: List<String>, word: String): Int {
        var count = 0
        val numRows = grid.size
        val numCols = grid[0].length
        val wordLength = word.length

        for (i in 0 until numRows - wordLength + 1) {
            for (j in 0 until numCols - wordLength + 1) {
                var diagonalWord1 = ""
                var diagonalWord2 = ""
                for (k in word.indices) {
                    diagonalWord1 += grid[i + k][j + k]
                    diagonalWord2 += grid[i + k][j + wordLength - 1 - k]
                }
                if (diagonalWord1 == word && diagonalWord2 == word) count++
                if (diagonalWord1.reversed() == word && diagonalWord2 == word) count++
                if (diagonalWord1 == word && diagonalWord2.reversed() == word) count++
                if (diagonalWord1.reversed() == word && diagonalWord2.reversed() == word) count++
            }
        }
        return count
    }
}