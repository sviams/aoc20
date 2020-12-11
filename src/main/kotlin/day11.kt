object day11 {

    val directions = listOf(
        (0 to 1), (1 to 0), (1 to 1), (0 to -1), (-1 to 0), (-1 to -1), (1 to -1), (-1 to 1)
    )

    tailrec fun isLineOccupied(state: List<String>, row: Int, col: Int, deltaRow: Int, deltaCol: Int, goDeep: Boolean) : Boolean =
        if (row < 0 || row > state.size-1 || col < 0 || col > state[0].length-1) false
        else when (state[row][col]) {
            'L' -> false
            '#' -> true
            else -> if (!goDeep) false else isLineOccupied(state, row + deltaRow, col + deltaCol, deltaRow, deltaCol, goDeep)
        }


    fun countOccupiedInSight(state: List<String>, row: Int, col: Int, goDeep: Boolean): Int =
        directions.map { isLineOccupied(state, row + it.first, col + it.second, it.first, it.second, goDeep) }.count { it }

    tailrec fun evolve(old: List<String>, maxOccupied: Int, goDeep: Boolean): List<String> {
        val new = old.mapIndexed { row, line ->
            line.mapIndexed { col, space ->
                when (space) {
                    'L' -> if (countOccupiedInSight(old, row, col, goDeep) == 0) '#' else 'L'
                    '#' -> if (countOccupiedInSight(old, row, col, goDeep) >= maxOccupied) 'L' else '#'
                    else -> space
                }
            }.joinToString("")
        }
        return if (new == old) new else evolve(new, maxOccupied, goDeep)
    }

    fun pt1(input: List<String>): Int = evolve(input, 4, false).sumBy { line -> line.count { it == '#' } }

    fun pt2(input: List<String>): Int = evolve(input, 5, true).sumBy { line -> line.count { it == '#' } }
}
